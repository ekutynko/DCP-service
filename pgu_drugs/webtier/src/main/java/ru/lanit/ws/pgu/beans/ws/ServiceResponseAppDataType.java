//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.29 at 11:39:31 AM MSK 
//


package ru.lanit.ws.pgu.beans.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceResponseAppDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceResponseAppDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://lanit.ru/ws/pgu/changeorderinfo}changeOrderInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceResponseAppDataType", namespace = "http://lanit.ru/ws/pgu/types", propOrder = {
    "changeOrderInfo"
})
public class ServiceResponseAppDataType {

    @XmlElement(namespace = "http://lanit.ru/ws/pgu/changeorderinfo", required = true)
    protected ServiceResponseInfoType changeOrderInfo;

    /**
     * Gets the value of the changeOrderInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceResponseInfoType }
     *     
     */
    public ServiceResponseInfoType getChangeOrderInfo() {
        return changeOrderInfo;
    }

    /**
     * Sets the value of the changeOrderInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceResponseInfoType }
     *     
     */
    public void setChangeOrderInfo(ServiceResponseInfoType value) {
        this.changeOrderInfo = value;
    }

}
