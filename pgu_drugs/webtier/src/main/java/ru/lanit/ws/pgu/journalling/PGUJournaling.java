package ru.lanit.ws.pgu.journalling;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lanit.ws.pgu.beans.ws.CancelRequestType;
import ru.lanit.ws.pgu.beans.ws.CatalogRequestType;
import ru.lanit.ws.pgu.beans.ws.MessageType;
import ru.lanit.ws.pgu.beans.ws.RegisterRequestType;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author Roman Orlov
 */
@Aspect
@Component
public class PGUJournaling extends BaseJournaling {    
    
    @Autowired
    private JournalWriter journalWriter;
    
    @Resource(name="pgu.sender.code")
    private String senderCode;
           
    @Before("execution(* ru.lanit.ws.pgu.PGURequestWS.registerPguRequest(..)) "
            + "&& args(request,..)")
    public void journalRegisterPguRequest(JAXBElement<RegisterRequestType> request) {
        MessageType srcMessage = request.getValue().getMessage().getValue();
        journalWriter.setRequestActor(srcMessage.getOriginator().getValue().getCode());
        journalWriter.setResponseActor(senderCode);
        journalWriter.setRequestKind(RequestKind.PGU);
        journalWriter.setRequestName(RequestName.LICENSE_REQUEST);
    }
    
    @Before("execution(* ru.lanit.ws.pgu.PGURequestWS.buildRequestResponse(..)) "
            + "&& args(licenseRequestId,..)")
    public void journalBuildRequestResponse(Long licenseRequestId) {
        if (licenseRequestId != null) {
            journalWriter.setLicenseRequestId(licenseRequestId);
        }
    }
    
//    @Before("execution(* ru.lanit.ws.pgu.PGURequestWS.cancelRegisteredRequest(..)) "
//            + "&& args(request,..)")
    public void journalCancelRegisteredRequest(JAXBElement<CancelRequestType> request) {
        MessageType srcMessage = request.getValue().getMessage().getValue();
        journalWriter.setRequestActor(srcMessage.getOriginator().getValue().getCode());
        journalWriter.setResponseActor(senderCode);
        journalWriter.setRequestKind(RequestKind.PGU);
        journalWriter.setRequestName(RequestName.CANCEL_LICENSE_REQUEST);
    }

    @Before("execution(* ru.lanit.ws.pgu.PGURequestWS.catalogRequest(..)) "
            + "&& args(request,..)")
    public void journalCatalogRequest(JAXBElement<CatalogRequestType> request) {
        MessageType srcMessage = request.getValue().getMessage().getValue();
        journalWriter.setRequestActor(srcMessage.getOriginator().getValue().getCode());
        journalWriter.setResponseActor(senderCode);
        journalWriter.setRequestKind(RequestKind.PGU);
        journalWriter.setRequestName(RequestName.GET_DICTIONARY);
    }
    
}
