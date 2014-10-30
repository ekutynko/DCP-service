package ru.lanit.ws.pgu;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.lanit.ws.pgu.beans.ws.*;
import ru.lanit.ws.pgu.beans.ws.Dictionary;
import ru.lanit.ws.pgu.lod.LodRequestSender;
import ru.lanit.ws.pgu.lod.catalog.Activity;
import ru.lanit.ws.pgu.lod.catalog.Catalog;
import ru.lanit.ws.pgu.lod.catalog.CatalogItem;
import ru.lanit.ws.pgu.requests.AttachmentDesc;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;
import ru.lanit.ws.security.SecurityService;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBElement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Endpoint("pguRequest")
public class PGURequestWS {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private Validator validator;

    @Autowired
    private LodRequestSender lodRequestSender;

    @Resource(name = "applicationMarshaller")
    private Unmarshaller unmarshaller;

    @Resource(name = "pgu.sender.code")
    private String senderCode;

    @Resource(name = "pgu.sender.name")
    private String senderName;

    @Resource(name = "pgu.originator.code")
    private String originatorCode;

    @Resource(name = "pgu.originator.name")
    private String originatorName;

    @Resource(name = "pgu.typeCode")
    private String typeCode;

    @Resource(name = "pgu.serviceCode")
    private String serviceCode;

    private final ObjectFactory factory = new ObjectFactory();

    private final Logger logger = Logger.getLogger(PGURequestWS.class.getName());

    @ResponsePayload
    @PayloadRoot(namespace = "http://lanit.ru/ws/pgu", localPart = "RegisterRequest")
    public JAXBElement<RegisterResponseType> registerPguRequest(@RequestPayload JAXBElement<RegisterRequestType> request,
            MessageContext messageContext) throws PGUExceptions.BaseException {
        JAXBElement<RegisterResponseType> result = null;
        MessageType srcMessage = null;
        try {
            try {
                srcMessage = request.getValue().getMessage().getValue();
                MessageInfo messageInfo = getMessageInfo(messageContext, srcMessage);
                BaseRequestComposite pguRequest = fromRequest(request, messageInfo);

                Set<ConstraintViolation<BaseRequestComposite>> errors = validator.validate(pguRequest);
                if (!errors.isEmpty()) {
                    ConstraintViolation<BaseRequestComposite> error = errors.iterator().next();
                    String errorMsg = error.getPropertyPath().toString() + " " + error.getMessage();
                    throw new PGUExceptions.ApplicationError(errorMsg);
                }

                Long licenseRequestId = lodRequestSender.sendLicenseRequest(
                        String.format("ws/pgu/request/tax/%s", pguRequest.getLicRequestKind().getUriName()),
                        pguRequest);

                result = buildRequestResponseProxied(licenseRequestId, messageInfo, null);
            } catch (PGUExceptions.BaseException e) {
                logger.severe(e.getDescription());
                throw e;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Internal error", e);
                throw new PGUExceptions.InternalError();
            }
        } catch (PGUExceptions.BaseException e) {
            if (srcMessage != null) {
                try {
                    MessageInfo messageInfo = getMessageInfo(messageContext, srcMessage);
                    result = buildRequestResponseProxied(null, messageInfo, buildErrorBlock(e));
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Ошибка при формировании ошибки", ex);
                    throw e;
                }
            } else {
                throw e;
            }
        }
        return result;
    }

    /**
     * Пробуем вызвать через прокси, чтобы можно было перехватить вызов функции
     */
    public JAXBElement<RegisterResponseType> buildRequestResponseProxied(Long licenseRequestId,
            MessageInfo messageInfo, ServiceErrorInfoType errorInfo) {
        Object proxy = AopContext.currentProxy();
        if (proxy != null) {
            return ((PGURequestWS) proxy).buildRequestResponse(licenseRequestId, messageInfo, errorInfo);
        } else {
            return buildRequestResponse(licenseRequestId, messageInfo, errorInfo);
        }
    }

    public JAXBElement<RegisterResponseType> buildRequestResponse(Long licenseRequestId,
            MessageInfo messageInfo, ServiceErrorInfoType errorInfo) {
        MessageType message = buildMessageType(messageInfo);
        ServiceResponseAppDataType appData = new ServiceResponseAppDataType();

        ServiceResponseInfoType.OrderId orderId = new ServiceResponseInfoType.OrderId();
        orderId.setOrgId(String.valueOf(0));
        ServiceResponseInfoType.StatusCode statusCode = new ServiceResponseInfoType.StatusCode();
        statusCode.setTechCode(String.valueOf(2));
        // make changeOrderInfo
        ServiceResponseInfoType changeOrderInfo = new ServiceResponseInfoType();
        changeOrderInfo.setCancelAllowed(false);
        //changeOrderInfo.setComment("test comment");
        changeOrderInfo.setAuthToken("");
        changeOrderInfo.setOrderId(orderId);
        changeOrderInfo.setStatusCode(statusCode);

        if (errorInfo != null) {
            changeOrderInfo.getStatusCode().setTechCode(String.valueOf(4));
            changeOrderInfo.setComment(errorInfo.getErrorMessage());
            message.setStatus(StatusType.INVALID);
        }

        // make AppData
        appData.setChangeOrderInfo(changeOrderInfo);

        // make MessageData
        ServiceMessageDataResponseType rmd = new ServiceMessageDataResponseType();
        rmd.setAppData(appData);
        // make RegisterResponseType
        RegisterResponseType value = new RegisterResponseType();
        JAXBElement<MessageType> mt = factory.createRegisterResponseTypeMessage(message);
        JAXBElement<ServiceMessageDataResponseType> md = factory.createRegisterResponseTypeMessageData(rmd);
        value.setMessage(mt);
        value.setMessageData(md);
        return factory.createRegisterResponse(value);
    }

    private MessageInfo getMessageInfo(MessageContext messageContext, MessageType srcMessage) throws Exception {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setSrcMessage(srcMessage);
        messageInfo.setMessageId(String.valueOf(0));

        SaajSoapMessage msg = (SaajSoapMessage) messageContext.getRequest();
        NodeList headers = msg.getSaajMessage().getSOAPHeader().getChildNodes(); // FIXME уж больно на низком уровне

        for (int i = 0; i < headers.getLength(); i++) {
            Node headerNode = headers.item(i);
            if (headerNode.getNodeType() == Node.ELEMENT_NODE) {
                Element header = (Element) headerNode;
                if (header.getLocalName().equals("Header")) {
                    Node childNode = header.getFirstChild();
                    while (childNode != null) {
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element child = (Element) childNode;
                            if (child.getLocalName().equals("MessageId")) {
                                messageInfo.setMessageId(child.getFirstChild().getNodeValue());
                                return messageInfo;
                            }
                        }
                        childNode = childNode.getNextSibling();
                    }
                }
            }
        }
        return messageInfo;
    }

//	@ResponsePayload
//    @PayloadRoot(namespace = "http://lanit.ru/ws/pgu", localPart = "CancelRequest")
//    public JAXBElement<CancelResponseType> cancelRegisteredRequest(@RequestPayload JAXBElement<CancelRequestType> request,
//            MessageContext messageContext) throws PGUExceptions.BaseException {
//        JAXBElement<CancelResponseType> cr = null;
//        MessageType srcMessage = null;
//        try {
//            try {
//                srcMessage = request.getValue().getMessage().getValue();
//                CancelPGURequestComposite cancelRequest = fromRequest(request);
//                CancelPGUResponseComposite response = pguService.cancelRequest(cancelRequest);
//                cr = buildCancelResponse(response, getMessageInfo(messageContext, srcMessage), null);
//            } catch (Exception e) {
//                logger.log(Level.SEVERE, "Internal error", e);
//                throw new PGUExceptions.InternalError();
//            }
//        } catch (PGUExceptions.BaseException e) {
//            if (srcMessage != null) {
//                try {
//                    MessageInfo messageInfo = getMessageInfo(messageContext, srcMessage);
//                    cr = buildCancelResponse(null, messageInfo, buildErrorBlock(e));
//                } catch (Exception ex) {
//                    logger.log(Level.SEVERE, "Ошибка при формировании ошибки", ex);
//                    throw e;
//                }
//            } else {
//                throw e;
//            }
//        }
//        return cr;
//    }

//    private JAXBElement<CancelResponseType> buildCancelResponse(
//            CancelPGUResponseComposite response, MessageInfo messageInfo, ServiceErrorInfoType errorInfo) {
//        // make AppData
//        ServiceCancelRequestResponseAppDataType appData = new ServiceCancelRequestResponseAppDataType();
//        if (errorInfo != null) {
//            appData.setError(errorInfo);
//        } else {
//            ServiceCancelResponseInfoType responseInfo = new ServiceCancelResponseInfoType();
//            ResponseCodeType rct = ResponseCodeType.valueOf(response.getResponseCode());
//            responseInfo.setResponseCode(factory.createServiceCancelResponseInfoTypeResponseCode(rct));
//            responseInfo.setComment(factory.createServiceCancelResponseInfoTypeComment(response.getComment()));
//            appData.setCancelResponseInfo(responseInfo);
//        }
//        // make MessageData
//        ServiceCancelRequestMessageDataResponseType cmd = new ServiceCancelRequestMessageDataResponseType();
//        cmd.setAppData(appData);
//        // make CancelResponseType
//        CancelResponseType value = new CancelResponseType();
//        JAXBElement<MessageType> mt = factory.createCancelResponseTypeMessage(buildMessageType(messageInfo));
//        JAXBElement<ServiceCancelRequestMessageDataResponseType> md = factory.createCancelResponseTypeMessageData(cmd);
//        value.setMessage(mt);
//        value.setMessageData(md);
//        return factory.createCancelResponse(value);
//    }

    @ResponsePayload
    @PayloadRoot(namespace = "http://lanit.ru/ws/pgu", localPart = "CatalogRequest")
    public JAXBElement<CatalogResponseType> catalogRequest(@RequestPayload JAXBElement<CatalogRequestType> request,
            MessageContext messageContext) throws PGUExceptions.BaseException {
        JAXBElement<CatalogResponseType> result = null;
        MessageType srcMessage = null;
        try {
            try {
                srcMessage = request.getValue().getMessage().getValue();
                CatalogRequestMessageDataType messageData = request.getValue().getMessageData().getValue();
                CatalogRequestInfoType catalogInfo = messageData.getAppData().getCatalogInfo();

                ru.lanit.ws.pgu.lod.catalog.LodRequest lodRequest = new ru.lanit.ws.pgu.lod.catalog.LodRequest();
                lodRequest.setCatalogCode(Catalog.fromText(catalogInfo.getCode()).getCode());
                lodRequest.setActivityCode(Activity.TAXI.getCode());
                lodRequest.setProcessCode(catalogInfo.getProcess());
                lodRequest.setParent(catalogInfo.getParent());

                ru.lanit.ws.pgu.lod.catalog.LodResponse lodResponse =
                        lodRequestSender.sendCatalogRequest("ws/pgu/tax/catalog", lodRequest);

                result = buildCatalogResponse(lodResponse, getMessageInfo(messageContext, srcMessage), null);
            } catch (PGUExceptions.BaseException e) {
                logger.severe(e.getDescription());
                throw e;
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Internal error", e);
                throw new PGUExceptions.InternalError();
            }
        } catch (PGUExceptions.BaseException e) {
            if (srcMessage != null) {
                try {
                    MessageInfo messageInfo = getMessageInfo(messageContext, srcMessage);
                    result = buildCatalogResponse(null, messageInfo, buildErrorBlock(e));
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Ошибка при формировании ошибки", ex);
                    throw e;
                }
            } else {
                throw e;
            }
        }
        return result;
    }

    JAXBElement<CatalogResponseType> buildCatalogResponse(ru.lanit.ws.pgu.lod.catalog.LodResponse catalog,
                                                          MessageInfo messageInfo, ServiceErrorInfoType errorInfo) {
        MessageType message = buildMessageType(messageInfo);
        CatalogResponseAppDataType appData = new CatalogResponseAppDataType();
        if (errorInfo != null) {
            appData.setError(errorInfo);
            message.setStatus(StatusType.INVALID);
        } else {
            // make catalogData
            Dictionary catalogData = new Dictionary();
            List<DictionaryItem> items = new ArrayList<DictionaryItem>();
            for (CatalogItem catalogItem : catalog.getCatalogData()) {
                DictionaryItem item = new DictionaryItem(catalogItem.getCode(), catalogItem.getName());
                item.setOptional(catalogItem.getOptional());
                items.add(item);
            }
            catalogData.setItem(items);
            // make AppData
            appData.setCatalogData(catalogData);
        }
        // make MessageData
        CatalogResponseMessageDataType crmd = new CatalogResponseMessageDataType();
        crmd.setAppData(appData);
        // make CatalogResponseType
        JAXBElement<MessageType> mt = factory.createCatalogResponseTypeMessage(message);
        JAXBElement<CatalogResponseMessageDataType> md = factory.createCatalogResponseTypeMessageData(crmd);
        CatalogResponseType response = new CatalogResponseType();
        response.setMessage(mt);
        response.setMessageData(md);
        return factory.createCatalogResponse(response);
    }

    private ServiceErrorInfoType buildErrorBlock(PGUExceptions.BaseException e) {
        ServiceErrorInfoType errorInfo = new ServiceErrorInfoType();
        errorInfo.setErrorCode(e.getCode());
        errorInfo.setErrorMessage(e.getDescription());
        return errorInfo;
    }

//    private CancelPGURequestComposite fromRequest(JAXBElement<CancelRequestType> request) {
//        CancelPGURequestComposite cancelRequest = new CancelPGURequestComposite();
//        ServiceCancelRequestAppDataType inAppData = request.getValue().getMessageData().getValue().getAppData().getValue();
//        ServiceCancelRequestInfoType info = inAppData.getCancelRequestInfo();
//        cancelRequest.setRequestId(info.getRequestID());
//        cancelRequest.setReason(info.getReason());
//        return cancelRequest;
//    }

    private BaseRequestComposite fromRequest(JAXBElement<RegisterRequestType> request, MessageInfo messageInfo)
            throws Exception {
        AppDocumentType appDocument = request.getValue().getMessageData().getValue().getAppDocument().getValue();
        String requestCode = appDocument.getRequestCode().getValue();
        byte[] encodedApplication = appDocument.getBinaryData().getValue();
        byte[] decodedZip = ApplicationHelper.extractAttachmentZip(encodedApplication, unmarshaller);  // по идее не нужно, потому что в/из Base64 автоматом
        Map<String, AttachmentDesc> descs = new HashMap<String, AttachmentDesc>();

        byte[] applicationXml = ApplicationHelper.parseZip(decodedZip, descs);

        BaseRequestComposite requestComposite = ApplicationHelper.fillRequest(
                applicationXml, requestCode, descs, unmarshaller, securityService);

        ServiceRequestAppDataType appData = request.getValue().getMessageData().getValue().getAppData().getValue();
//        requestComposite.setPguId(appData.getOrderRequest().getPguId());
        requestComposite.setPguId(messageInfo.getSrcMessage().getCaseNumber());
        requestComposite.seteServiceId(appData.getOrderRequest().getServiceInfo().getEServiceId());
        requestComposite.setReestrId(appData.getOrderRequest().getServiceInfo().getReestrId());
        requestComposite.setServiceOrgCode(appData.getOrderRequest().getServiceInfo().getServiceOrgCode());
        requestComposite.setSmevMassageId(messageInfo.getMessageId());
        requestComposite.setRequestCode(appDocument.getRequestCode().getValue());

        return requestComposite;
    }

//    @ResponsePayload
//    @PayloadRoot(namespace = "http://lanit.ru/ws/pgu", localPart = "StatusRequest")
//    public JAXBElement<StatusResponseType> statusRequest(@RequestPayload JAXBElement<StatusRequestType> request,
//            MessageContext messageContext) throws PGUExceptions.BaseException {
//        JAXBElement<StatusResponseType> result = null;
//        MessageType srcMessage = null;
//        try {
//            try {
//                srcMessage = request.getValue().getMessage().getValue();
//                StatusRequestAppDataType appData = request.getValue().getMessageData().getValue().getAppData();
//                String pguId = appData.getStatusRequestInfo().getPguId();
//                StatusPGUResponseComposite response = pguService.statusRequest(pguId);
//                result = buildStatusResponse(response, getMessageInfo(messageContext, srcMessage), null);
//            } catch (PGUExceptions.BaseException e) {
//                logger.severe(e.getDescription());
//                throw e;
//            } catch (Exception e) {
//                logger.log(Level.SEVERE, "Internal error", e);
//                throw new PGUExceptions.InternalError();
//            }
//        } catch (PGUExceptions.BaseException e) {
//            if (srcMessage != null) {
//                try {
//                    MessageInfo messageInfo = getMessageInfo(messageContext, srcMessage);
//                    result = buildStatusResponse(null, messageInfo, buildErrorBlock(e));
//                } catch (Exception ex) {
//                    logger.log(Level.SEVERE, "Ошибка при формировании ошибки", ex);
//                    throw e;
//                }
//            } else {
//                throw e;
//            }
//        }
//        return result;
//    }

//    JAXBElement<StatusResponseType> buildStatusResponse(StatusPGUResponseComposite response, MessageInfo messageInfo, ServiceErrorInfoType errorInfo)
//            throws Exception {
//        // make AppData
//        StatusResponseAppDataType appDataType = new StatusResponseAppDataType();
//        if (errorInfo != null) {
//            appDataType.setError(errorInfo);
//        } else {
//            // make ResponseInfoType
//            StatusResponseInfoType responseInfoType = new StatusResponseInfoType();
//            responseInfoType.setPguId(response.getPguId());
//            //responseInfoType.setStatus(response.getStatus());
//            responseInfoType.setStatusCode(response.getStatusCode());
//            responseInfoType.setStatusText(response.getStatusText());
//            responseInfoType.setDocuments(ApplicationHelper.toZipArchive(new File(response.getMediaRoot()),
//                    response.getFileNames(), securityService));
//            appDataType.setStatusResponseInfo(responseInfoType);
//        }
//        // make MessageData
//        StatusResponseMessageDataType messageDataType = new StatusResponseMessageDataType();
//        messageDataType.setAppData(appDataType);
//        // make StatusResponseType
//        StatusResponseType responseType = new StatusResponseType();
//        responseType.setMessage(factory.createStatusResponseTypeMessage(buildMessageType(messageInfo)));
//        responseType.setMessageData(factory.createStatusResponseTypeMessageData(messageDataType));
//        return factory.createStatusResponse(responseType);
//    }

    private MessageType buildMessageType(MessageInfo messageInfo) {
        MessageType srcMessage = messageInfo.getSrcMessage();
        MessageType type = new MessageType();

        OrgExternalType senderValue = new OrgExternalType();
        senderValue.setCode(senderCode);
        senderValue.setName(senderName);
        type.setSender(senderValue);

        OrgExternalType recipientValue = new OrgExternalType();
        recipientValue.setCode(srcMessage.getSender().getCode());
        recipientValue.setName(srcMessage.getSender().getName());
        type.setRecipient(recipientValue);

        OrgExternalType srcOriginatorValue = srcMessage.getOriginator().getValue();
        OrgExternalType originatorValue = new OrgExternalType();
        originatorValue.setCode(srcOriginatorValue.getCode());
        originatorValue.setName(srcOriginatorValue.getName());
        type.setOriginator(factory.createMessageTypeOriginator(originatorValue));

        type.setTypeCode(TypeCodeType.valueOf(typeCode));
        type.setStatus(StatusType.ACCEPT);
        type.setDate(srcMessage.getDate());
        type.setExchangeType(String.valueOf(1));
        type.setRequestIdRef(messageInfo.getMessageId());
        type.setOriginRequestIdRef(messageInfo.getMessageId());
        type.setServiceCode(serviceCode);
        type.setCaseNumber(srcMessage.getCaseNumber());

        return type;
    }

}
