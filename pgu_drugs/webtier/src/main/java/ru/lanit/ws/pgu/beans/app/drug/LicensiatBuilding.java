package ru.lanit.ws.pgu.beans.app.drug;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
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
    @Size(max = 250)
    @XmlElement(name = "DocName", required = true)
    private String docName;

    /**
     * Номер регистрации права
     */
    @Size(max = 250)
    @XmlElement(name = "DocNo", required = true)
    private String docNo;

    /**
     * Дата регистрации права
     */
    @XmlElement(name = "DocDate", required = true)
    @XmlSchemaType(name="date")
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
