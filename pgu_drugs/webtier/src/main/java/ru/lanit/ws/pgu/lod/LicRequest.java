package ru.lanit.ws.pgu.lod;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by user06 on 25.08.2014.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LicRequest", namespace = "http://lanit.ru/ws/commons/drug")
public enum LicRequest {

    GET_LICENSE("get_lic"),

    REFORM_LICENSE("reform_lic"),

    //REFORM_CLIENT("reform_client"),

    DUPLIC_LICENSE("duplic_lic"),

    CANCEL_LICENSE("cancel_lic"),

    REFORM_OTHER_LICENSE("reform_othe_lic"),

    LICENSE_INFO("lic_info");

    private String uriName;

    private LicRequest(String uriName) {
        this.uriName = uriName;
    }

    public String getUriName() {
        return uriName;
    }
}
