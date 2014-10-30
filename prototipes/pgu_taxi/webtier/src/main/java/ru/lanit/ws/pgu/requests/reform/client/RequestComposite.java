package ru.lanit.ws.pgu.requests.reform.client;

import ru.lanit.ws.common.DeliveryPlaceKind;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;
import ru.lanit.ws.pgu.requests.DictionaryInfo;
import ru.lanit.ws.pgu.requests.reform.client.info.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestComposite extends BaseRequestComposite {

    @NotNull
    @Size(min = 1)
    private String authority;

    @Valid
    @NotNull
    private LicensiatInfo licensiatInfo;

    @Valid
    @NotNull
    private DictionaryInfo licenseKind;

    @Valid
    @NotNull
    private RegistrationInfo registrationInfo;

    @Valid
    @NotNull
    private AddressInfo regAddressInfo;

    @Valid
    @NotNull
    private DictionaryInfo processType;

    @Valid
    @NotNull
    private LicenseRequestInfo licenseRequestInfo;

    @NotNull
    private DeliveryPlaceKind deliveryPlaceKind;

    @NotNull
    private DictionaryInfo deliveryPlace;


    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public DictionaryInfo getLicenseKind() {
        return licenseKind;
    }

    public void setLicenseKind(DictionaryInfo licenseKind) {
        this.licenseKind = licenseKind;
    }

    public DictionaryInfo getProcessType() {
        return processType;
    }

    public void setProcessType(DictionaryInfo processType) {
        this.processType = processType;
    }

    public AddressInfo getRegAddressInfo() {
        return regAddressInfo;
    }

    public void setRegAddressInfo(AddressInfo regAddressInfo) {
        this.regAddressInfo = regAddressInfo;
    }

    public LicensiatInfo getLicensiatInfo() {
        return licensiatInfo;
    }

    public void setLicensiatInfo(LicensiatInfo licensiatInfo) {
        this.licensiatInfo = licensiatInfo;
    }

    public RegistrationInfo getRegistrationInfo() {
        return registrationInfo;
    }

    public void setRegistrationInfo(RegistrationInfo registrationInfo) {
        this.registrationInfo = registrationInfo;
    }

    public LicenseRequestInfo getLicenseRequestInfo() {
        return licenseRequestInfo;
    }

    public void setLicenseRequestInfo(LicenseRequestInfo licenseRequestInfo) {
        this.licenseRequestInfo = licenseRequestInfo;
    }

    public DeliveryPlaceKind getDeliveryPlaceKind() {
        return deliveryPlaceKind;
    }

    public void setDeliveryPlaceKind(DeliveryPlaceKind deliveryPlaceKind) {
        this.deliveryPlaceKind = deliveryPlaceKind;
    }

    public DictionaryInfo getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(DictionaryInfo deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }
}
