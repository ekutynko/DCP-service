package ru.lanit.ws.pgu.beans.app.drug;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Данные документа о постановке соискателя/лицензиата на учет в налоговом органе
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InnInfo", namespace = "http://lanit.ru/ws/pgu/drug")
public class InnInfo {
    /**
     * ИНН налогоплательщика
     */
    @Pattern(regexp = "\\b(\\d{10}|\\d{12})\\b")
    @XmlElement(name = "Inn", required = true)
    private String inn;

    /**
     * Наименование документа
     */
    @Size(min =1, max = 250)
    @XmlElement(name = "InnDocName", required = true)
    private String innDocName;

    /**
     * Дата выдачи документа
     */
    @XmlElement(name = "InnDate", required = true)
    @XmlSchemaType(name="date")
    private Date innDate;

    /**
     *  Серия документа
     */
    @Size(min =1, max = 50)
    @XmlElement(name = "InnSeries", required = true)
    private String innSeries;

    /**
     *  Номер документа
     */
    @Size(min =1, max = 50)
    @XmlElement(name = "InnNo", required = true)
    private String innNo;

    /**
     *  Орган, выдавший документ (наименование и код подразделения)
     */
    @Size(min =1, max = 250)
    @XmlElement(name = "InnWho", required = true)
    private String innWho;

    /**
     * Дата постановки на учет
     */
    @XmlElement(name = "InnRegDate", required = true)
    @XmlSchemaType(name="date")
    private Date innRegDate;

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getInnDocName() {
        return innDocName;
    }

    public void setInnDocName(String innDocName) {
        this.innDocName = innDocName;
    }

    public Date getInnDate() {
        return innDate;
    }

    public void setInnDate(Date innDate) {
        this.innDate = innDate;
    }

    public String getInnSeries() {
        return innSeries;
    }

    public void setInnSeries(String innSeries) {
        this.innSeries = innSeries;
    }

    public String getInnWho() {
        return innWho;
    }

    public void setInnWho(String innWho) {
        this.innWho = innWho;
    }

    public Date getInnRegDate() {
        return innRegDate;
    }

    public void setInnRegDate(Date innRegDate) {
        this.innRegDate = innRegDate;
    }
}
