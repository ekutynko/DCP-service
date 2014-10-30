package ru.laint.ws.pgu.beans.app.drug.requests.get_license;

import ru.laint.ws.pgu.beans.app.drug.*;
import ru.laint.ws.pgu.beans.common.DictionaryItem;

import javax.xml.bind.annotation.*;

/**
 * Предоставление лицензии на осуществление деятельности, связанной с оборотом наркотических средств
 * и психотропных веществ и их прекурсоров, по культивированию наркосодержащих растений
 * (в ЛОД — Предоставление лицензии)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetLicenseRequest", namespace = "http://lanit.ru/ws/pgu/drug/requests")
public class GetLicenseRequest {

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

    @XmlElement(name = "StateFeePayment", required = true)
    private StateFeePayment stateFeePayment;

    @XmlElement(name = "ActivityAddress", required = true)
    private ActivityAddress activityAddress;

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

    public StateFeePayment getStateFeePayment() {
        return stateFeePayment;
    }

    public void setStateFeePayment(StateFeePayment stateFeePayment) {
        this.stateFeePayment = stateFeePayment;
    }

    public ActivityAddress getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(ActivityAddress activityAddress) {
        this.activityAddress = activityAddress;
    }


}
