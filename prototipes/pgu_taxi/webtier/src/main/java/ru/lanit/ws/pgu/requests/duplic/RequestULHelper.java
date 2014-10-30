/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lanit.ws.pgu.requests.duplic;

import ru.lanit.ws.common.DeliveryPlaceKind;
import ru.lanit.ws.common.LicensiatType;
import ru.lanit.ws.pgu.beans.application.lanit.ws.pgu.duplic_license.*;
import ru.lanit.ws.pgu.requests.BaseRequestComposite;
import ru.lanit.ws.pgu.requests.duplic.info.*;

/**
 *
 * @author user06
 */
public final class RequestULHelper extends RequestHelper {

    public RequestULHelper(RequestBaseType request) {
        super(request);
    }

    public LicensiatInfo getLicensiatInfo() {
        LicensiatInfo licensiatInfo = new LicensiatInfo();
        RequestULType requestUL = (RequestULType) getRequest();
        LicensiatULType licensiatUL = requestUL.getLicensiatUL();
        LicensiatInfo.LicensiatInfoUL infoUL = new LicensiatInfo.LicensiatInfoUL();
        infoUL.setFullName(licensiatUL.getFullName());
        infoUL.setFirmName(getValue(licensiatUL.getFirmName()));
        infoUL.setOrgForm(toDictInfo(licensiatUL.getOrgForm()));
        String shortName = getValue(licensiatUL.getShortName());
        if (shortName != null) {
            infoUL.setShortName(shortName);
        } else {
            infoUL.setShortName("");
        }
        licensiatInfo.setLicensiatInfoUL(infoUL);

        return licensiatInfo;
    }

    @Override
    public BaseRequestComposite fillRequestComposite() {
        RequestComposite requestComposite = new RequestComposite();

        requestComposite.setAuthority(getRequest().getAuthority());
        requestComposite.setLicenseKind(toDictInfo(getRequest().getActivityKind()));
        requestComposite.setLicensiatInfo(getLicensiatInfo());
        requestComposite.setLicenseRequestInfo(getLicenseRequestInfo());
        requestComposite.setProcessType(toDictInfo(getRequest().getProcessType()));
        requestComposite.setRegAddressInfo(getAddressInfo());
        requestComposite.setRegistrationInfo(getRegistrationInfo());
        requestComposite.setClientType(LicensiatType.FIRM.getCode());
        requestComposite.setDeliveryPlaceKind(
                DeliveryPlaceKind.fromCode(Integer.parseInt(getRequest().getDeliveryPlaceKind())));
        requestComposite.setDeliveryPlace(toDictInfo(getRequest().getDeliveryPlace()));

        return requestComposite;
    }
}
