package ru.lanit.ws.pgu.requests;

import com.sun.istack.NotNull;
import ru.lanit.ws.pgu.lod.LicRequest;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user06 on 25.08.2014.
 */
public class BaseRequestComposite {

    /*
    Базовый класс, который реализуют все композиты
    TODO: добавить валидацию полей
     */
    private LicRequest licRequestKind;

    @NotNull
    private String pguId;

    @NotNull
    private String serviceOrgCode;

    @NotNull
    private String eServiceId;

    @NotNull
    private String reestrId;

    @NotNull
    private String serviceType;

    @NotNull
    private String requestCode;

    @NotNull
    private Integer clientType;

//    @NotNull
    private String smevMassageId;

    private List<AttachmentDesc> attachments = new LinkedList<AttachmentDesc>();

    public String getPguId() {
        return pguId;
    }

    public void setPguId(String pguId) {
        this.pguId = pguId;
    }

    public String getServiceOrgCode() {
        return serviceOrgCode;
    }

    public void setServiceOrgCode(String serviceOrgCode) {
        this.serviceOrgCode = serviceOrgCode;
    }

    public String geteServiceId() {
        return eServiceId;
    }

    public void seteServiceId(String eServiceId) {
        this.eServiceId = eServiceId;
    }

    public String getReestrId() {
        return reestrId;
    }

    public void setReestrId(String reestrId) {
        this.reestrId = reestrId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public String getSmevMassageId() {
        return smevMassageId;
    }

    public void setSmevMassageId(String smevMassageId) {
        this.smevMassageId = smevMassageId;
    }

    public List<AttachmentDesc> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDesc> attachments) {
        this.attachments = attachments;
    }

    public LicRequest getLicRequestKind() {
        return licRequestKind;
    }

    public void setLicRequestKind(LicRequest licRequestKind) {
        this.licRequestKind = licRequestKind;
    }

}
