package ru.laint.ws.pgu.beans.app.drug;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 10. Адрес места нахождения лицензиата (только для процесса получения выписки из реестра)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LicensiatAddress", namespace = "http://lanit.ru/ws/pgu/drug")
public class LicensiatAddress {

    /**
     * Почтовый индекс
     */
    @Pattern(regexp = "^$|\\d{6}|null")
    @XmlElement(name = "PostIndex", required = false)
    private String postIndex;

    /**
     * Субъект РФ
     */
    @NotNull
    @Size(min = 1, max = 100)
    @XmlElement(name = "Region", required = false)
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
}
