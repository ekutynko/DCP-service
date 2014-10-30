package ru.laint.ws.pgu.beans.app.drug;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
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
    @XmlElement(name = "Inn", required = true)
    private String inn;

    /**
     * Наименование документа
     */
    @XmlElement(name = "InnDocName", required = true)
    private String innDocName;

    /**
     * Дата выдачи документа
     */
    @XmlElement(name = "InnDate", required = true)
    private Date innDate;

    /**
     *  Серия документа
     */
    @XmlElement(name = "InnSeries", required = true)
    private Date innSeries;

    /**
     *  Орган, выдавший документ (наименование и код подразделения)
     */
    @XmlElement(name = "InnWho", required = true)
    private Date innWho;

    /**
     * Дата постановки на учет
     */
    @XmlElement(name = "InnRegDate", required = true)
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

    public Date getInnSeries() {
        return innSeries;
    }

    public void setInnSeries(Date innSeries) {
        this.innSeries = innSeries;
    }

    public Date getInnWho() {
        return innWho;
    }

    public void setInnWho(Date innWho) {
        this.innWho = innWho;
    }

    public Date getInnRegDate() {
        return innRegDate;
    }

    public void setInnRegDate(Date innRegDate) {
        this.innRegDate = innRegDate;
    }
}
