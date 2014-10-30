package ru.lanit.ws.pgu.beans.app.drug;

import ru.lanit.ws.pgu.beans.common.DictionaryItem;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Данные о лицензиате (только для процесса получение выписки из реестра)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LicensiatInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class LicensiatInfo {

    /**
     * Полное наименование
     */
    @XmlElement(name = "FullName", required = false)
    private String fullName;

    /**
     * Сокращенное наименование (если имеется)
     */
    @Size(max = 250)
    @XmlElement(name = "ShortName", required = false)
    private String shortName;

    /**
     * Фирменное наименование (если имеется)
     */
    @XmlElement(name = "FirmName", required = false)
    private String firmName;


    /**
     * Организационно-правовая форма
     */
    @XmlElement(name = "OrgForm", required = false)
    private DictionaryItem orgForm;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public DictionaryItem getOrgForm() {
        return orgForm;
    }

    public void setOrgForm(DictionaryItem orgForm) {
        this.orgForm = orgForm;
    }
}
