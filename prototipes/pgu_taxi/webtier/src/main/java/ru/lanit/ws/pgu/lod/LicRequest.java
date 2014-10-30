package ru.lanit.ws.pgu.lod;

/**
 * Created by user06 on 25.08.2014.
 */
public enum LicRequest {

    GET_LICENSE("get_lic"),

    REFORM_LICENSE("reform_lic"),

    REFORM_CLIENT("reform_client"),

    DUPLIC_LICENSE("duplic_lic");

    private String uriName;

    private LicRequest(String uriName) {
        this.uriName = uriName;
    }

    public String getUriName() {
        return uriName;
    }
}
