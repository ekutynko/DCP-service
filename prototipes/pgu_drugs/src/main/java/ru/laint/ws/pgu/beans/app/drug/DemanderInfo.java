package ru.laint.ws.pgu.beans.app.drug;

import ru.laint.ws.pgu.beans.common.DictionaryItem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Данные о заявителе
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DemanderInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class DemanderInfo {

    /**
     * Тип заявителя
     */
    @XmlElement(name = "DemanderType", required = true)
    private DictionaryItem demanderType;

    /**
     * Полное наименование
     */
    @XmlElement(name = "FullName", required = true)
    private String fullName;

    /**
     * Сокращенное наименование (если имеется)
     */
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
    @XmlElement(name = "OrgForm", required = true)
    private DictionaryItem orgForm;

    public DictionaryItem getDemanderType() {
        return demanderType;
    }

    public void setDemanderType(DictionaryItem demanderType) {
        this.demanderType = demanderType;
    }

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
