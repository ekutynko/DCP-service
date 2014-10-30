package ru.lanit.ws.pgu.beans.app.drug;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Данные о заявителе (только для процесса получение выписки из реестра)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DemanderExtractInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class DemanderExtractInfo {

    /**
     *  Фамилия
     */
    @Size(max = 50)
    @XmlElement(name = "Surname", required = false)
    private String surname;

    /**
     *  Имя
     */
    @Size(max = 50)
    @XmlElement(name = "FirstName", required = false)
    private String firstName;

    /**
     *  Отчество
     */
    @Size(max = 50)
    @XmlElement(name = "FartherName", required = false)
    private String fatherName;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}
