package ru.laint.ws.pgu.beans.app.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Сведения о наличии заключения о соответствии требованиям объектов и помещений — привязывается к месту деятельности (возможно множественное добавление)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceObjectInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class ComplianceObjectInfo {

    /**
     * Номер документа
     */
    @XmlElement(name = "DocNo", required = true)
    private String docNo;

    /**
     * Дата документа
     */
    @XmlElement(name = "DocDate", required = true)
    private Date docDate;

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
}
