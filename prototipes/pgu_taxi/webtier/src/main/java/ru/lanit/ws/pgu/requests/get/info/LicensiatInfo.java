package ru.lanit.ws.pgu.requests.get.info;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import ru.lanit.ws.pgu.lod.serializers.JsonDateSerializer;
import ru.lanit.ws.pgu.lod.serializers.JsonDateTimeSerializer;
import ru.lanit.ws.pgu.requests.DictionaryInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Данные о заявителе
 *
 * @author Roman Orlov
 */
public class LicensiatInfo {

    public static class LicensiatInfoUL {

        /**
         * Оргагнизационная форма
         */
        @NotNull
        private DictionaryInfo orgForm;

        /**
         * Полное наименование организации
         */
        @NotNull
        @Size(min = 1)
        private String fullName;

        /**
         * Сокращенное наименование организации (если есть)
         */
        @Size(max = 255)
        private String shortName;

        /** Фирменное наименование */
        private String firmName;

        //----------------------------------------------------------------------
        public DictionaryInfo getOrgForm() {
            return orgForm;
        }

        public void setOrgForm(DictionaryInfo orgForm) {
            this.orgForm = orgForm;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getFirmName() {
            return firmName;
        }

        public void setFirmName(String firmName) {
            this.firmName = firmName;
        }
    }

    public static class LicensiatInfoIP {

        @NotNull
        @Size(min = 1, max = 50)
        private String lastName;

        @NotNull
        @Size(min = 1, max = 50)
        private String firstName;

        @Size(max = 50)
        private String secondName;

        @NotNull
        private DictionaryInfo passportType;

        @NotNull
        @Size(min = 1, max = 20)
        private String passportSeries;

        @NotNull
        @Size(min = 1, max = 20)
        private String passportNo;

        @NotNull
        @Size(min = 1)
        private String passportWho;

        @NotNull
        @Past
        @JsonSerialize(using = JsonDateSerializer.class)
        private Date passportDate;

        private DictionaryInfo countryCode;

        //----------------------------------------------------------------------
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getSecondName() {
            return secondName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        public DictionaryInfo getPassportType() {
            return passportType;
        }

        public void setPassportType(DictionaryInfo passportType) {
            this.passportType = passportType;
        }

        public String getPassportSeries() {
            return passportSeries;
        }

        public void setPassportSeries(String passportSeries) {
            this.passportSeries = passportSeries;
        }

        public String getPassportNo() {
            return passportNo;
        }

        public void setPassportNo(String passportNo) {
            this.passportNo = passportNo;
        }

        public String getPassportWho() {
            return passportWho;
        }

        public void setPassportWho(String passportWho) {
            this.passportWho = passportWho;
        }

        public Date getPassportDate() {
            return passportDate;
        }

        public void setPassportDate(Date passportDate) {
            this.passportDate = passportDate;
        }

        public DictionaryInfo getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(DictionaryInfo countryCode) {
            this.countryCode = countryCode;
        }
    }

    private LicensiatInfoIP licensiatInfoIP = null;

    private LicensiatInfoUL licensiatInfoUL = null;

    //--------------------------------------------------------------------------
    public LicensiatInfoIP getLicensiatInfoIP() {
        return licensiatInfoIP;
    }

    public void setLicensiatInfoIP(LicensiatInfoIP licensiatInfoIP) {
        this.licensiatInfoIP = licensiatInfoIP;
    }

    public LicensiatInfoUL getLicensiatInfoUL() {
        return licensiatInfoUL;
    }

    public void setLicensiatInfoUL(LicensiatInfoUL licensiatInfoUL) {
        this.licensiatInfoUL = licensiatInfoUL;
    }
}
