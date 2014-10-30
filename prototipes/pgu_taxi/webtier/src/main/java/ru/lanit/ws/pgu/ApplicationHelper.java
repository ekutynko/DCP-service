package ru.lanit.ws.pgu;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.stream.StreamSource;
import org.apache.commons.io.FileUtils;

import org.springframework.oxm.Unmarshaller;

import org.springframework.oxm.UnmarshallingFailureException;
import ru.lanit.ws.common.LicensiatType;
import ru.lanit.ws.pgu.beans.application.gosuslugi.smev.request.req.rev120528.Data;
import ru.lanit.ws.pgu.beans.application.gosuslugi.smev.request.req.rev120528.RequestDataType;
import ru.lanit.ws.pgu.beans.application.gosuslugi.smev.request.rev111111.AppliedDocumentType;
import ru.lanit.ws.pgu.beans.application.gosuslugi.smev.request.rev111111.AppliedDocumentsType;
import ru.lanit.ws.pgu.requests.AttachmentDesc;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;
import ru.lanit.ws.pgu.requests.BaseRequestHelper;
import ru.lanit.ws.pgu.lod.LicRequest;
import ru.lanit.ws.security.SecurityService;

public class ApplicationHelper {



    private final static Logger logger = Logger.getLogger(ApplicationHelper.class.getName());

    public static final int BUFFER_SIZE = 1024;

    public static byte[] extractAttachmentZip(byte[] encodedApplication, Unmarshaller unmarshaller) {
        byte[] applicationXML = encodedApplication;//Base64.decodeBase64(encodedApplication);
        return applicationXML;
    }

    public static byte[] parseZip(byte[] zip, Map<String, AttachmentDesc> list)
            throws Exception {
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(zip));
        String path = "";
        ZipEntry entry = null;
        byte[] result = null;
        while ((entry = zis.getNextEntry()) != null) {
            if (!entry.isDirectory()) {
                String name = entry.getName();
                boolean isApplication = name.endsWith(".xml")
                        && name.startsWith("req_");
                byte[] data = getEntryContent(zis);
                if (isApplication) {
                    result = data;
                    AttachmentDesc ad = new AttachmentDesc(null, path + "/"
                            + entry.getName(), data);
                    list.put(entry.getName(), ad);
                } else {
                    AttachmentDesc ad = new AttachmentDesc(null, path + "/"
                            + entry.getName(), data);
                    list.put(entry.getName(), ad);
                }
            } else {
                path += "/" + entry.getName();
            }
            zis.closeEntry();
        }
        return result;
    }

    public static BaseRequestComposite fillRequest(byte[] applicationXml, String requestCode,
            Map<String, AttachmentDesc> list,
            Unmarshaller unmarshaller, SecurityService securityService) throws Exception {
        Data obj_v;// = extractApplication(applicationXml, unmarshaller);
        try {
            obj_v = extractApplication(applicationXml, unmarshaller);
        } catch (UnmarshallingFailureException ufe) {
            throw new PGUExceptions.ApplicationError(ufe.getRootCause().getMessage());
        }
        RequestDataType requestData = obj_v.getRequestData();

        BaseRequestComposite requestComposite = null;
        BaseRequestHelper requestHelper = null;

        if (requestData.getGetLicenseRequest() != null) {
            LicensiatType clientType = LicensiatType.fromCode(Integer.parseInt(
                    requestData.getGetLicenseRequest().getClientType()));
            if (clientType == LicensiatType.FIRM) {
                requestHelper = new ru.lanit.ws.pgu.requests.get.RequestULHelper(
                        requestData.getGetLicenseRequest().getRequestUL());
            } else {
                requestHelper = new ru.lanit.ws.pgu.requests.get.RequestIPHelper(
                        requestData.getGetLicenseRequest().getRequestIP());
            }
            requestComposite = requestHelper.fillRequestComposite();
            requestComposite.setLicRequestKind(LicRequest.GET_LICENSE);

        } else if (requestData.getReformClientRequest() != null) {
            LicensiatType clientType = LicensiatType.fromCode(Integer.parseInt(
                    requestData.getReformClientRequest().getClientType()));
            if (clientType == LicensiatType.FIRM) {
                requestHelper = new ru.lanit.ws.pgu.requests.reform.client.RequestULHelper(
                        requestData.getReformClientRequest().getRequestUL());
            } else {
                requestHelper = new ru.lanit.ws.pgu.requests.reform.client.RequestIPHelper(
                        requestData.getReformClientRequest().getRequestIP());
            }
            requestComposite = requestHelper.fillRequestComposite();
            requestComposite.setLicRequestKind(LicRequest.REFORM_CLIENT);

        } else if (requestData.getReformLicenseRequest() != null) {
            LicensiatType clientType = LicensiatType.fromCode(Integer.parseInt(
                    requestData.getReformLicenseRequest().getClientType()));
            if (clientType == LicensiatType.FIRM) {
                requestHelper = new ru.lanit.ws.pgu.requests.reform.license.RequestULHelper(
                        requestData.getReformLicenseRequest().getRequestUL());
            } else {
                requestHelper = new ru.lanit.ws.pgu.requests.reform.license.RequestIPHelper(
                        requestData.getReformLicenseRequest().getRequestIP());
            }
            requestComposite = requestHelper.fillRequestComposite();
            requestComposite.setLicRequestKind(LicRequest.REFORM_LICENSE);

        } else if (requestData.getDuplicLicenseRequest() != null) {
            LicensiatType clientType = LicensiatType.fromCode(Integer.parseInt(
                    requestData.getDuplicLicenseRequest().getClientType()));
            if (clientType == LicensiatType.FIRM) {
                requestHelper = new ru.lanit.ws.pgu.requests.duplic.RequestULHelper(
                        requestData.getDuplicLicenseRequest().getRequestUL());
            } else {
                requestHelper = new ru.lanit.ws.pgu.requests.duplic.RequestIPHelper(
                        requestData.getDuplicLicenseRequest().getRequestIP());
            }
            requestComposite = requestHelper.fillRequestComposite();
            requestComposite.setLicRequestKind(LicRequest.DUPLIC_LICENSE);
        }

        if (requestComposite == null) {
            throw new PGUExceptions.ApplicationError("В запросе указан не верно или отсутствует метод сервиса");
        }
        requestComposite.setAttachments(new ArrayList<AttachmentDesc>());

        AppliedDocumentsType appldocs = obj_v.getAppliedDocuments();
        if (appldocs != null && appldocs.getAppliedDocument() != null) {
            String mainAppDocName = requestCode + ".xml";
            for (AppliedDocumentType adoc : appldocs.getAppliedDocument()) {
                AttachmentDesc a = list.get(adoc.getName());
                if (a == null) {
                    throw new PGUExceptions.ApplicationError(String.format("Attachment '%1$s' not found in zip", adoc.getName()));
                }
                //retrieve signature
				/*String sigName = adoc.getName() + ".sig";
                 AttachmentDesc as = list.get(sigName);
                 if(as == null) {
                 throw new PGUExceptions.ApplicationError(String.format("Signature '%1$s' not found in zip", sigName));
                 }
                 if(!securityService.verify(a.getData(), as.getData())) {
                 throw new PGUExceptions.ApplicationError(String.format("Invalid signature for '%1$s'", adoc.getName()));
                 };*/

                if (!adoc.getName().equals(mainAppDocName)) {
                    AttachmentDesc ads = new AttachmentDesc(null, adoc.getName(), adoc.getCodeDocument(), adoc.getType(), adoc.getNumber(), adoc.getDigestValue(), a.getData());
                    adoc.getNumber();
                    requestComposite.getAttachments().add(ads);
                }

                logger.info(String.format("Attachment '%1$s' is valid", adoc.getName()));
            }
        }
        return requestComposite;
    }

    private static byte[] getEntryContent(ZipInputStream zis)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zis.read(bytesIn)) != -1) {
            baos.write(bytesIn, 0, read);
        }
        baos.close();
        byte[] data = baos.toByteArray();
        return data;
    }

    public static Data extractApplication(byte[] applicationXML, Unmarshaller unmarshaller) throws Exception {
        Object applicationObject = null;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(applicationXML);
        /*
         String debugStr = new String(applicationXML, "UTF-8");
         logger.info(debugStr);
         */
        StreamSource source = new StreamSource(inputStream);
        applicationObject = unmarshaller.unmarshal(source);

        return (Data) applicationObject;
    }

    /**
     * Помещает в архив файлы с именами относительно заданного каталога
     *
     * @param root каталог
     * @param fileNames относительные имена файлов
     */
    public static byte[] toZipArchive(File root, Set<String> fileNames, SecurityService securityService) throws Exception {
        if (fileNames.isEmpty()) {
            return null;
        }
        ByteArrayOutputStream zipData = new ByteArrayOutputStream();
        ZipOutputStream zipOutput = new ZipOutputStream(zipData);
        try {
            for (String fileName : fileNames) {
                File file = new File(root, fileName);
                if (file.isFile() && !file.isHidden()) {
                    byte[] data = FileUtils.readFileToByteArray(file);

                    zipOutput.putNextEntry(new ZipEntry(file.getName()));
                    zipOutput.write(data);
                    zipOutput.closeEntry();

                    zipOutput.putNextEntry(new ZipEntry(file.getName() + ".sig"));
                    List<byte[]> signature = securityService.signByteArray(data);
                    zipOutput.write(signature.get(1));
                    zipOutput.closeEntry();
                }
            }
            zipOutput.close();
        } catch (ZipException e) {
            return null;
        }
        return zipData.toByteArray();
    }
}
