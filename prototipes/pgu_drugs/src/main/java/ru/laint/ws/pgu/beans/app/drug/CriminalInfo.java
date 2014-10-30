package ru.laint.ws.pgu.beans.app.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Сведения о наличии заключения об отсутствии у работников судимости — привязывается к месту деятельности (возможно множественное добавление)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CriminalInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class CriminalInfo {
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

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }
}
