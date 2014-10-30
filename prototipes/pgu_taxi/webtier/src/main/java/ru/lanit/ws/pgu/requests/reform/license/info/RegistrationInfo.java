package ru.lanit.ws.pgu.requests.reform.license.info;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import ru.lanit.ws.pgu.lod.serializers.JsonDateSerializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Регистрационные данные
 *
 * @author Roman Orlov
 */
public class RegistrationInfo {

    // Если нужно провалидировать конкретно, то можно вынести в информацию о заявителе
    /** ИНН */
    @NotNull
    @Pattern(regexp = "\\d{10,12}")
    private String inn;

    @Size(max = 255)
    private String innWho;

    @Past
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date innDate;

    @Size(max = 50)
    private String innSeries;

    @Size(max = 50)
    private String innNo;

    private String innName;

    private String innForm;

    /** Номер телефона */
    @Size(max = 20)
    private String phone;

    /** Факс */
    @Size(max = 20)
    private String fax;

    /** Эл. почта */
    @Size(max = 100)
    private String email;

    /** ОГРН */
    @NotNull
    @Pattern(regexp = "\\d{13,15}")
    private String ogrn;

    @Size(max = 50)
    private String ogrnNo;

    @Size(max = 50)
    private String ogrnSeries;

    @NotNull
    @Past
    @JsonSerialize(using = JsonDateSerializer.class)
    private Date ogrnDate;

    private String ogrnName;

    private String ogrnForm;

    private String ogrnDepartAddress;

    private RepresentativeInfo representativeInfo;



    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getInnWho() {
        return innWho;
    }

    public void setInnWho(String innWho) {
        this.innWho = innWho;
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

    public String getInnNo() {
        return innNo;
    }

    public void setInnNo(String innNo) {
        this.innNo = innNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getOgrnNo() {
        return ogrnNo;
    }

    public void setOgrnNo(String ogrnNo) {
        this.ogrnNo = ogrnNo;
    }

    public String getOgrnSeries() {
        return ogrnSeries;
    }

    public void setOgrnSeries(String ogrnSeries) {
        this.ogrnSeries = ogrnSeries;
    }

    public Date getOgrnDate() {
        return ogrnDate;
    }

    public void setOgrnDate(Date ogrnDate) {
        this.ogrnDate = ogrnDate;
    }

    public String getOgrnDepartAddress() {
        return ogrnDepartAddress;
    }

    public void setOgrnDepartAddress(String ogrnDepartAddress) {
        this.ogrnDepartAddress = ogrnDepartAddress;
    }

    public String getInnName() {
        return innName;
    }

    public void setInnName(String innName) {
        this.innName = innName;
    }

    public String getInnForm() {
        return innForm;
    }

    public void setInnForm(String innForm) {
        this.innForm = innForm;
    }

    public String getOgrnName() {
        return ogrnName;
    }

    public void setOgrnName(String ogrnName) {
        this.ogrnName = ogrnName;
    }

    public String getOgrnForm() {
        return ogrnForm;
    }

    public void setOgrnForm(String ogrnForm) {
        this.ogrnForm = ogrnForm;
    }

    public RepresentativeInfo getRepresentativeInfo() {
        return representativeInfo;
    }

    public void setRepresentativeInfo(RepresentativeInfo representativeInfo) {
        this.representativeInfo = representativeInfo;
    }
}
