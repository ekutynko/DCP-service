package ru.lanit.ws.pgu.requests.reform.license.info;

import ru.lanit.ws.pgu.requests.DictionaryInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by user06 on 18.09.2014.
 */
public class CarDocInfo {

    @NotNull
    private DictionaryInfo carDocType;

    private String carDocSeries;

    @NotNull
    private String carDocNumber;

    @NotNull
    @Past
    private Date carDocDate;

    @Past
    private Date carDocDateExp;


    public DictionaryInfo getCarDocType() {
        return carDocType;
    }

    public void setCarDocType(DictionaryInfo carDocType) {
        this.carDocType = carDocType;
    }

    public String getCarDocSeries() {
        return carDocSeries;
    }

    public void setCarDocSeries(String carDocSeries) {
        this.carDocSeries = carDocSeries;
    }

    public String getCarDocNumber() {
        return carDocNumber;
    }

    public void setCarDocNumber(String carDocNumber) {
        this.carDocNumber = carDocNumber;
    }

    public Date getCarDocDate() {
        return carDocDate;
    }

    public void setCarDocDate(Date carDocDate) {
        this.carDocDate = carDocDate;
    }

    public Date getCarDocDateExp() {
        return carDocDateExp;
    }

    public void setCarDocDateExp(Date carDocDateExp) {
        this.carDocDateExp = carDocDateExp;
    }

}
