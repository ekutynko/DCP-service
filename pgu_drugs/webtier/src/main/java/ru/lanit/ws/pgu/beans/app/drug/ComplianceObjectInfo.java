package ru.lanit.ws.pgu.beans.app.drug;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
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
    @Size(max = 50)
    @XmlElement(name = "DocNo", required = true)
    private String docNo;

    /**
     * Дата документа
     */
    @XmlElement(name = "DocDate", required = true)
    @XmlSchemaType(name="date")
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
