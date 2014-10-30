package ru.laint.ws.pgu.beans.app.drug;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Прочие данные
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class OtherInfo {

    /**
     * Телефон (моб)
     */
    @Size(max = 20)
    @XmlElement(name = "Phone", required = true)
    private String phone;

    /**
     * Телефон (моб) 2
     */
    @Size(max = 20)
    @XmlElement(name = "Phone2", required = false)
    private String phone2;

    /**
     * Телефон (моб) 3
     */
    @Size(max = 20)
    @XmlElement(name = "Phone3", required = false)
    private String phone3;

    /**
     * Факс
     */
    @Size(max = 20)
    @XmlElement(name = "Fax", required = false)
    private String fax;

    /**
     * E-mail
     */
    @Size(max = 100)
    @XmlElement(name = "Email", required = false)
    private String email;

    /**
     * Ф.И.О. руководителя
     */
    @XmlElement(name = "HeadFIO", required = false)
    private String headFIO;

    /**
     * Должность руководителя
     */
    @XmlElement(name = "HeadPosition", required = false)
    private String headPosition;

    /**
     * Ф.И.О. представителя
     */
    @XmlElement(name = "RepresentativeFIO", required = false)
    private String representativeFIO;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadFIO() {
        return headFIO;
    }

    public void setHeadFIO(String headFIO) {
        this.headFIO = headFIO;
    }

    public String getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(String headPosition) {
        this.headPosition = headPosition;
    }

    public String getRepresentativeFIO() {
        return representativeFIO;
    }

    public void setRepresentativeFIO(String representativeFIO) {
        this.representativeFIO = representativeFIO;
    }
}
