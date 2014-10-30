package ru.lanit.ws.pgu.beans.app.drug;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 *   Данные документа о государственной регистрации соискателя/лицензиата
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrationInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class RegistrationInfo {

    /**
     * ОГРН
     */
    @Pattern(regexp = "\\b(\\d{13}|\\d{15})\\b")
    @XmlElement(name = "Ogrn", required = true)
    private String ogrn;

    /**
     * Наименование документа
     */
    @Size(min =1, max = 250)
    @XmlElement(name = "OgrnName", required = true)
    private String ogrnName;

    /**
     * Дата выдачи документа
     */
    @XmlElement(name = "OgrnDate", required = true)
    @XmlSchemaType(name="date")
    private Date ogrnDate;

    /**
     *  Серия документа
     */
    @Size(min =1, max = 50)
    @XmlElement(name = "OgrnSeries", required = true)
    private String ogrnSeries;

    /**
     *  Номер документа
     */
    @Size(min =1, max = 50)
    @XmlElement(name = "OgrnNo", required = true)
    private String ogrnNo;

    /**
     *  Орган, выдавший документ
     */
    @Size(min =1, max = 250)
    @XmlElement(name = "OgrnWho", required = true)
    private String ogrnWho;

    /**
     *  Адрес места нахождения органа, осуществившего государственную регистрацию
     */
    @XmlElement(name = "OgrnWhoAddress", required = true)
    private String ogrnWhoAddress;

    /**
     * Дата государственной регистрации
     */
    @XmlElement(name = "OgrnRegDate", required = true)
    @XmlSchemaType(name="date")
    private Date ogrnReqDate;

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getOgrnName() {
        return ogrnName;
    }

    public void setOgrnName(String ogrnName) {
        this.ogrnName = ogrnName;
    }

    public Date getOgrnDate() {
        return ogrnDate;
    }

    public void setOgrnDate(Date ogrnDate) {
        this.ogrnDate = ogrnDate;
    }

    public String getOgrnSeries() {
        return ogrnSeries;
    }

    public void setOgrnSeries(String ogrnSeries) {
        this.ogrnSeries = ogrnSeries;
    }

    public String getOgrnNo() {
        return ogrnNo;
    }

    public void setOgrnNo(String ogrnNo) {
        this.ogrnNo = ogrnNo;
    }

    public String getOgrnWho() {
        return ogrnWho;
    }

    public void setOgrnWho(String ogrnWho) {
        this.ogrnWho = ogrnWho;
    }

    public String getOgrnWhoAddress() {
        return ogrnWhoAddress;
    }

    public void setOgrnWhoAddress(String ogrnWhoAddress) {
        this.ogrnWhoAddress = ogrnWhoAddress;
    }

    public Date getOgrnReqDate() {
        return ogrnReqDate;
    }

    public void setOgrnReqDate(Date ogrnReqDate) {
        this.ogrnReqDate = ogrnReqDate;
    }
}
