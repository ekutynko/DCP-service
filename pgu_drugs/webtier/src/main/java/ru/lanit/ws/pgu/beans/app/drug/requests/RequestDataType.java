//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.29 at 11:31:15 AM MSK 
//

package ru.lanit.ws.pgu.beans.app.drug.requests;

import ru.lanit.ws.pgu.beans.app.drug.requests.cancel_license.CancelLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.duplicate_license.DuplicateLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.get_license.GetLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.license_info.LicenseInfoRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.reform_license.ReformLicenseRequest;
import ru.lanit.ws.pgu.beans.app.drug.requests.reform_license_other.ReformLicenseOtherRequest;

import javax.xml.bind.annotation.*;

/**
 * Используется для передачи данных заявки. Данные заявки могут быть переданы только внутри
 *                 RequestData
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestDataType", namespace = "http://lanit.ru/ws/pgu/drug/requests")
public class RequestDataType {


    @XmlElements({
            @XmlElement(name = "CancelLicenseRequest", type = CancelLicenseRequest.class, required = true),
            @XmlElement(name = "DuplicateLicenseRequest", type = DuplicateLicenseRequest.class, required = true),
            @XmlElement(name = "GetLicenseRequest", type = GetLicenseRequest.class, required = true),
            @XmlElement(name = "LicenseInfoRequest", type = LicenseInfoRequest.class, required = true),
            @XmlElement(name = "ReformLicenseRequest", type = ReformLicenseRequest.class, required = true),
            @XmlElement(name = "ReformLicenseOtherRequest", type = ReformLicenseOtherRequest.class, required = true)
    })
    protected BaseRequest request;

    public BaseRequest getRequest() {
        return request;
    }

    public void setRequest(BaseRequest request) {
        this.request = request;
    }

}