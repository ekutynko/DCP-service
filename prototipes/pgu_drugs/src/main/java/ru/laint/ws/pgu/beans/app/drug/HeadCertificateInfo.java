package ru.laint.ws.pgu.beans.app.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Сведения об оплате государственной пошлины
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeadCertificateInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class HeadCertificateInfo {
    /**
     * Наименование документа
     */
    @XmlElement(name = "DocName", required = true)
    private String docName;

    /**
     * Регистрационный номер
     */
    @XmlElement(name = "DocNo", required = true)
    private String docNo;

    /**
     * Дата выдачи документа
     */
    @XmlElement(name = "DocDate", required = true)
    private Date docDate;

    /**
     * Специальность
     */
    @XmlElement(name = "Speciality", required = true)
    private String speciality;

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
