package ru.laint.ws.pgu.beans.app.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
 * Подтверждение наличия у соискателя/лицензиата принадлежащих ему зданий, сооружений и (или) помещений
 * — привязывается к месту деятельности (возможно множественное добавление)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LicensiatBuilding", namespace = "http://lanit.ru/ws/pgu/drug")
public class LicensiatBuilding {

    /**
     * Наименование документа
     */
    @XmlElement(name = "DocName", required = true)
    private String docName;

    /**
     * Номер регистрации права
     */
    @XmlElement(name = "DocNo", required = true)
    private String docNo;

    /**
     * Дата регистрации права
     */
    @XmlElement(name = "DocDate", required = true)
    private Date docDate;

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
}
