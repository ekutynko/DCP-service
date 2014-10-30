package ru.lanit.ws.pgu.requests.duplic.info;

import ru.lanit.ws.pgu.requests.DictionaryInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Данные запрашиваемой лицензии
 * 
 * @author Roman Orlov
 */
public class LicenseRequestInfo {

    /** Номер лицензии */
    @NotNull
    @Size(min = 1, max = 100)
    private String licenseNo;

    @Size(max = 50)
    private String blankNo;

    @Size(max = 10)
    private String blankSeries;

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getBlankNo() {
        return blankNo;
    }

    public void setBlankNo(String blankNo) {
        this.blankNo = blankNo;
    }

    public String getBlankSeries() {
        return blankSeries;
    }

    public void setBlankSeries(String blankSeries) {
        this.blankSeries = blankSeries;
    }
}
