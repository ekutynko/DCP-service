package ru.lanit.ws.pgu.lod.request;

import ru.lanit.ws.pgu.lod.LodBaseResponse;

/**
 * Created by Sergey on 31.08.2014.
 */
public class LodResponse extends LodBaseResponse {

    private Long licenseRequestId;

    public Long getLicenseRequestId() {
        return licenseRequestId;
    }

    public void setLicenseRequestId(Long licenseRequestId) {
        this.licenseRequestId = licenseRequestId;
    }
}
