package ru.lanit.ws.pgu;

import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.UnmarshallingFailureException;
import ru.lanit.ws.pgu.beans.app.drug.requests.AppliedDocumentType;
import ru.lanit.ws.pgu.beans.app.drug.requests.AppliedDocumentsType;
import ru.lanit.ws.pgu.beans.app.drug.requests.Data;
import ru.lanit.ws.pgu.beans.app.drug.requests.RequestDataType;
import ru.lanit.ws.pgu.beans.app.drug.requests.cancel_license.CancelLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.duplicate_license.DuplicateLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.get_license.GetLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.license_info.LicenseInfoRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.reform_license.ReformLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.reform_license_other.ReformLicenseOtherRequest;
import ru.lanit.ws.pgu.lod.LicRequest;
import ru.lanit.ws.pgu.requests.AttachmentDesc;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;
import ru.lanit.ws.security.SecurityService;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ApplicationHelper {



    private final static Logger logger = Logger.getLogger(ApplicationHelper.class.getName());

    public static final int BUFFER_SIZE = 1024;

    public static byte[] parseZip(byte[] zip, Map<String, AttachmentDesc> list) throws Exception {
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
                    AttachmentDesc ad = new AttachmentDesc();
                        ad.setId(null);
                        ad.setName(path + "/" + entry.getName());
                        ad.setData(data);
                    list.put(entry.getName(), ad);
                } else {
                    AttachmentDesc ad = new AttachmentDesc();
                        ad.setId(null);
                        ad.setName(path + "/" + entry.getName());
                        ad.setData(data);
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
        BaseRequestComposite baseRequestComposite = null;

        if(requestData.getRequest() instanceof GetLicenseRequest){
            baseRequestComposite = (GetLicenseRequest) requestData.getRequest();
            baseRequestComposite.setLicRequestKind(LicRequest.GET_LICENSE);
        }else if(requestData.getRequest() instanceof DuplicateLicenseRequest){
            baseRequestComposite = (DuplicateLicenseRequest) requestData.getRequest();
            baseRequestComposite.setLicRequestKind(LicRequest.DUPLIC_LICENSE);
        }else if(requestData.getRequest() instanceof CancelLicenseRequest){
            baseRequestComposite = (CancelLicenseRequest) requestData.getRequest();
            baseRequestComposite.setLicRequestKind(LicRequest.CANCEL_LICENSE);
        }else if(requestData.getRequest() instanceof ReformLicenseRequest){
            baseRequestComposite = (ReformLicenseRequest) requestData.getRequest();
            baseRequestComposite.setLicRequestKind(LicRequest.REFORM_LICENSE);
        }else if(requestData.getRequest() instanceof ReformLicenseOtherRequest){
            baseRequestComposite = (ReformLicenseOtherRequest) requestData.getRequest();
            baseRequestComposite.setLicRequestKind(LicRequest.REFORM_OTHER_LICENSE);
        }else if(requestData.getRequest() instanceof LicenseInfoRequest){
            baseRequestComposite = (LicenseInfoRequest) requestData.getRequest();
            baseRequestComposite.setLicRequestKind(LicRequest.LICENSE_INFO);
        }
        if(baseRequestComposite!=null) baseRequestComposite.getAttachments().addAll(extractAttachments(requestCode, list, obj_v));

        return baseRequestComposite;
    }

    private static List<AttachmentDesc> extractAttachments(String requestCode, Map<String, AttachmentDesc> list, Data obj_v) throws PGUExceptions.ApplicationError {
        AppliedDocumentsType appldocs = obj_v.getAppliedDocuments();
        List<AttachmentDesc> attachmentDescs = new ArrayList<AttachmentDesc>();
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
                    AttachmentDesc ad = new AttachmentDesc();
                        ad.setId(null);
                        ad.setName(adoc.getName());
                        ad.setCode(adoc.getCodeDocument());
                        ad.setType(adoc.getType());
                        ad.setNumber(adoc.getNumber());
                        ad.setDigestValue(adoc.getDigestValue());
                        ad.setData(a.getData());
                    attachmentDescs.add(ad);
                }

                logger.info(String.format("Attachment '%1$s' is valid", adoc.getName()));
            }
        }
        return attachmentDescs;
    }

    private static byte[] getEntryContent(ZipInputStream zis) throws IOException {
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
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(applicationXML);
        /*
         String debugStr = new String(applicationXML, "UTF-8");
         logger.info(debugStr);
         */
            StreamSource source = new StreamSource(inputStream);
            applicationObject = unmarshaller.unmarshal(source);
        }
        catch (Exception e) {
            throw new PGUExceptions.ApplicationError("Ошибка при распаковке вложения. Возможно, некорректные архив.");
        }
        return (Data) applicationObject;
    }

    /**
     * Помещает в архив файлы с именами относительно заданного каталога
     *
     * @param root каталог
     * @param fileNames относительные имена файлов
     */
//    public static byte[] toZipArchive(File root, Set<String> fileNames, SecurityService securityService) throws Exception {
//        if (fileNames.isEmpty()) {
//            return null;
//        }
//        ByteArrayOutputStream zipData = new ByteArrayOutputStream();
//        ZipOutputStream zipOutput = new ZipOutputStream(zipData);
//        try {
//            for (String fileName : fileNames) {
//                File file = new File(root, fileName);
//                if (file.isFile() && !file.isHidden()) {
//                    byte[] data = FileUtils.readFileToByteArray(file);
//
//                    zipOutput.putNextEntry(new ZipEntry(file.getName()));
//                    zipOutput.write(data);
//                    zipOutput.closeEntry();
//
//                    zipOutput.putNextEntry(new ZipEntry(file.getName() + ".sig"));
//                    List<byte[]> signature = securityService.signByteArray(data);
//                    zipOutput.write(signature.get(1));
//                    zipOutput.closeEntry();
//                }
//            }
//            zipOutput.close();
//        } catch (ZipException e) {
//            return null;
//        }
//        return zipData.toByteArray();
//    }
}
