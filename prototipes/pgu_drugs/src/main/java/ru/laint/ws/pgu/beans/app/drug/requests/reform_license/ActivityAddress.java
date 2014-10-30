package ru.laint.ws.pgu.beans.app.drug.requests.reform_license;

import ru.laint.ws.pgu.beans.app.drug.*;
import ru.laint.ws.pgu.beans.common.DictionaryItem;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * 12. Адрес места осуществления деятельности
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActivityAddress", namespace = "http://lanit.ru/ws/pgu/drug/reform_license")
public class ActivityAddress {

    /**
     * Кадастровый номер
     */
    @XmlElement(name = "CadastralNo", required = false)
    private String CadastralNo;

    /**
     * Условный кадастровый номер
     */
    @XmlElement(name = "ConditionalCadastralNo", required = false)
    private String conditionalCadastralNo;

    /**
     * Почтовый индекс
     */
    @Pattern(regexp = "^$|\\d{6}|null")
    @XmlElement(name = "PostIndex", required = true)
    private String postIndex;

    /**
     * Субъект РФ
     */
    @NotNull
    @Size(min = 1, max = 100)
    @XmlElement(name = "Region", required = true)
    private String region;

    /**
     * Район
     */
    @Size(max = 100)
    @XmlElement(name = "District", required = true)
    private String district;

    /**
     * Поселение
     */
    @Size(max = 50)
    @XmlElement(name = "Settlement", required = true)
    private String settlement;

    /**
     * Тип населенного пункта
     */
    @Size(max = 50)
    @XmlElement(name = "VillageType", required = false)
    private String villageType;

    /**
     * Населенный пункт
     */
    @Size(max = 50)
    @XmlElement(name = "Village", required = false)
    private String village;

    /**
     * Улица, строение и т. д.:
     */
    @Size(max = 20)
    @XmlElement(name = "Location", required = false)
    private String location;

    /**
     * Вид объекта
     */
    @XmlElement(name = "objectType", required = false)
    private DictionaryItem objectType;

    /**
     * Вид права
     */
    @XmlElement(name = "rightType", required = false)
    private DictionaryItem rightType;

    /**
     * Места деятельности (файл)
     */
    @XmlElement(name = "DocRef", required = false)
    private String docRef;

    /**
     * 13. Сведения о составе работ (услуг) — привязывается к месту деятельности
     */
    @XmlElement(name = "WorkStructure", required = false)
    private WorkStructure workStructure;

    /**
     * . 15. Подтверждение наличия у соискателя/лицензиата принадлежащих ему зданий, сооружений и (или) помещений
     * — привязывается к месту деятельности (возможно множественное добавление)
     */
    @XmlElementWrapper(name="LicensiatBuildingList")
    @XmlElement(name = "LicensiatBuilding", required = false)
    private List<LicensiatBuilding> licensiatBuildingList;

    /**
     * 16. Сведения о наличии заключения о соответствии требованиям объектов и помещений
     * — привязывается к месту деятельности (возможно множественное добавление)
     */
    @XmlElementWrapper(name="ComplianceObjectInfoList")
    @XmlElement(name = "ComplianceObjectInfo", required = false)
    private List<ComplianceObjectInfo> complianceObjectInfoList;

    /**
     * 17. Сведения о наличии заключения об отсутствии у работников судимости
     * — привязывается к месту деятельности (возможно множественное добавление)
     */
    @XmlElementWrapper(name="CriminalInfoList")
    @XmlElement(name = "CriminalInfo", required = false)
    private List<CriminalInfo> criminalInfoList;

    public String getCadastralNo() {
        return CadastralNo;
    }

    public void setCadastralNo(String cadastralNo) {
        CadastralNo = cadastralNo;
    }

    public String getConditionalCadastralNo() {
        return conditionalCadastralNo;
    }

    public void setConditionalCadastralNo(String conditionalCadastralNo) {
        this.conditionalCadastralNo = conditionalCadastralNo;
    }

    public String getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(String postIndex) {
        this.postIndex = postIndex;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getVillageType() {
        return villageType;
    }

    public void setVillageType(String villageType) {
        this.villageType = villageType;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DictionaryItem getObjectType() {
        return objectType;
    }

    public void setObjectType(DictionaryItem objectType) {
        this.objectType = objectType;
    }

    public DictionaryItem getRightType() {
        return rightType;
    }

    public void setRightType(DictionaryItem rightType) {
        this.rightType = rightType;
    }

    public String getDocRef() {
        return docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public WorkStructure getWorkStructure() {
        return workStructure;
    }

    public void setWorkStructure(WorkStructure workStructure) {
        this.workStructure = workStructure;
    }

    public List<LicensiatBuilding> getLicensiatBuildingList() {
        return licensiatBuildingList;
    }

    public void setLicensiatBuildingList(List<LicensiatBuilding> licensiatBuildingList) {
        this.licensiatBuildingList = licensiatBuildingList;
    }

    public List<ComplianceObjectInfo> getComplianceObjectInfoList() {
        return complianceObjectInfoList;
    }

    public void setComplianceObjectInfoList(List<ComplianceObjectInfo> complianceObjectInfoList) {
        this.complianceObjectInfoList = complianceObjectInfoList;
    }

    public List<CriminalInfo> getCriminalInfoList() {
        return criminalInfoList;
    }

    public void setCriminalInfoList(List<CriminalInfo> criminalInfoList) {
        this.criminalInfoList = criminalInfoList;
    }
}
