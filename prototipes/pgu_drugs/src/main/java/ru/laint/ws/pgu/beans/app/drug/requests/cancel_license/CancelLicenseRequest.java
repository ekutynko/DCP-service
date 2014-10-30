package ru.laint.ws.pgu.beans.app.drug.requests.cancel_license;


import ru.laint.ws.pgu.beans.app.drug.*;
import ru.laint.ws.pgu.beans.common.DictionaryItem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


/**
 * Прекращение действия лицензии на осуществление деятельности, связанной с оборотом наркотических средств
 * и психотропных веществ и их прекурсоров, по культивированию наркосодержащих растений
 * (в ЛОД — Прекращение действия лицензии по заявлению)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelLicenseRequest", namespace = "http://lanit.ru/ws/pgu/drug/requests")
public class CancelLicenseRequest {

    /**
     * Вид деятельности
     */
    @XmlElement(name = "OperationType", required = true)
    private DictionaryItem operationType;

    /**
     * Лицензирующий орган
     */
    @XmlElement(name = "LicenseOrg", required = true)
    private String licenseOrg;

    /**
     * Вид услуги
     */
    @XmlElement(name = "ServiceType", required = true)
    private DictionaryItem serviceType;

    /**
     * Номер лицензии
     */
    @XmlElement(name = "LicenseNo", required = true)
    private String licenseNo;

    /**
     * Дата лицензии
     */
    @XmlElement(name = "LicenseDate", required = false)
    private Date licenseDate;

    /**
     * Наименование лицензирующего органа, предоставившего лицензию
     */
    @XmlElement(name = "LicenseOrgName", required = true)
    private String licenseOrgName;

    /**
     * Дата фактического прекращения лицензируемого вида деятельности
     */
    @XmlElement(name = "CancelDate", required = true)
    private Date cancelDate;


    @XmlElement(name = "InnInfo", required = true)
    private InnInfo innInfo;

    @XmlElement(name = "RegistrationInfo", required = true)
    private RegistrationInfo registrationInfo;

    @XmlElement(name = "DemanderInfo", required = true)
    private DemanderInfo demanderInfo;

    @XmlElement(name = "LegalAddress", required = true)
    private LegalAddress legalAddress;

    @XmlElement(name = "PostalAddress", required = true)
    private PostalAddress postalAddress;

    @XmlElement(name = "OtherInfo", required = true)
    private OtherInfo otherInfo;

    public DictionaryItem getOperationType() {
        return operationType;
    }

    public void setOperationType(DictionaryItem operationType) {
        this.operationType = operationType;
    }

    public String getLicenseOrg() {
        return licenseOrg;
    }

    public void setLicenseOrg(String licenseOrg) {
        this.licenseOrg = licenseOrg;
    }

    public DictionaryItem getServiceType() {
        return serviceType;
    }

    public void setServiceType(DictionaryItem serviceType) {
        this.serviceType = serviceType;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getLicenseOrgName() {
        return licenseOrgName;
    }

    public void setLicenseOrgName(String licenseOrgName) {
        this.licenseOrgName = licenseOrgName;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public InnInfo getInnInfo() {
        return innInfo;
    }

    public void setInnInfo(InnInfo innInfo) {
        this.innInfo = innInfo;
    }

    public RegistrationInfo getRegistrationInfo() {
        return registrationInfo;
    }

    public void setRegistrationInfo(RegistrationInfo registrationInfo) {
        this.registrationInfo = registrationInfo;
    }

    public DemanderInfo getDemanderInfo() {
        return demanderInfo;
    }

    public void setDemanderInfo(DemanderInfo demanderInfo) {
        this.demanderInfo = demanderInfo;
    }

    public LegalAddress getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(LegalAddress legalAddress) {
        this.legalAddress = legalAddress;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }
}
