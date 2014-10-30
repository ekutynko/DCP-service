package ru.lanit.ws.pgu.beans.app.drug.requests.reform_license_other;


import ru.lanit.ws.pgu.beans.app.drug.*;
import ru.lanit.ws.pgu.beans.app.drug.requests.BaseRequest;
import ru.lanit.ws.pgu.beans.common.DictionaryItem;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;


/**
 *  Переоформление лицензии на осуществление деятельности по обороту наркотических средств, психотропных веществ
 *  и их прекурсоров, культивированию наркосодержащих растений в других случаях
 *  (в ЛОД — Переоформление лицензии 10 дней)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReformLicenseOtherRequest", namespace = "http://lanit.ru/ws/pgu/drug/requests")
public class ReformLicenseOtherRequest extends BaseRequestComposite implements BaseRequest {

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
    @XmlSchemaType(name="date")
    private Date licenseDate;

    /**
     * 1.6 Наименование лицензирующего органа, предоставившего лицензию
     */
    @XmlElement(name = "LicenseOrgName", required = true)
    private String licenseOrgName;

    /**
     * 1.9 Причины переоформления
     */
    @XmlElement(name = "ReformReason", required = true)
    private List<DictionaryItem> ReformReason;

    /**
     * 1.10 Дополнительное разъяснение причин переоформления
     */
    @XmlElement(name = "ReasonExplanation", required = false)
    private String reasonExplanation;


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

    public List<DictionaryItem> getReformReason() {
        return ReformReason;
    }

    public void setReformReason(List<DictionaryItem> reformReason) {
        ReformReason = reformReason;
    }

    public String getReasonExplanation() {
        return reasonExplanation;
    }

    public void setReasonExplanation(String reasonExplanation) {
        this.reasonExplanation = reasonExplanation;
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
