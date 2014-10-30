/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lanit.ws.pgu.requests.reform.license;

import ru.lanit.ws.common.DeliveryPlaceKind;
import ru.lanit.ws.common.LicensiatType;
import ru.lanit.ws.pgu.beans.application.lanit.ws.pgu.reform_license.*;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;
import ru.lanit.ws.pgu.requests.reform.license.info.*;

/**
 *
 * @author user06
 */
public final class RequestIPHelper extends RequestHelper {

    public RequestIPHelper(RequestBaseType request) {
        super(request);
    }

    public LicensiatInfo getLicensiatInfo() {
        LicensiatInfo licensiatInfo = new LicensiatInfo();
        RequestIPType requestIP = (RequestIPType) getRequest();
        LicensiatIPType licensiatIP = requestIP.getLicensiatIP();
        LicensiatInfo.LicensiatInfoIP infoIP = new LicensiatInfo.LicensiatInfoIP();
        infoIP.setFirstName(licensiatIP.getFirstName());
        infoIP.setLastName(licensiatIP.getLastName());
        infoIP.setPassportType(toDictInfo(licensiatIP.getPassportType()));
        infoIP.setPassportDate(XMLCalendarToDate(licensiatIP.getPassportDate()));
        infoIP.setPassportNo(licensiatIP.getPassportNo());
        infoIP.setPassportSeries(licensiatIP.getPassportSeries());
        infoIP.setPassportWho(licensiatIP.getPassportWho());
        infoIP.setCountryCode(toDictInfo(getValue(licensiatIP.getCountryCode())));
        String secondName = getValue(licensiatIP.getSecondName());
        if (secondName != null) {
            infoIP.setSecondName(secondName);
        } else {
            infoIP.setSecondName("");
        }
        licensiatInfo.setLicensiatInfoIP(infoIP);

        return licensiatInfo;
    }

    @Override
    public BaseRequestComposite fillRequestComposite() {
        RequestComposite requestComposite = new RequestComposite();

        requestComposite.setAuthority(getRequest().getAuthority());
        requestComposite.setCarInfo(getCarInfo());
        requestComposite.setLicenseKind(toDictInfo(getRequest().getActivityKind()));
        requestComposite.setLicensiatInfo(getLicensiatInfo());
        requestComposite.setLicenseRequestInfo(getLicenseRequestInfo());
        requestComposite.setProcessType(toDictInfo(getRequest().getProcessType()));
        requestComposite.setRegAddressInfo(getAddressInfo());
        requestComposite.setRegistrationInfo(getRegistrationInfo());
        requestComposite.setClientType(LicensiatType.INDIVIDUAL.getCode());
        requestComposite.setDeliveryPlaceKind(
                DeliveryPlaceKind.fromCode(Integer.parseInt(getRequest().getDeliveryPlaceKind())));
        requestComposite.setDeliveryPlace(toDictInfo(getRequest().getDeliveryPlace()));

        return requestComposite;
    }

}
