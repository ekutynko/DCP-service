//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.29 at 11:31:15 AM MSK 
//


package ru.lanit.ws.pgu.beans.application.lanit.ws.pgu.duplic_license;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestIPType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestIPType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://lanit.ru/ws/pgu/duplic_license}RequestBaseType">
 *       &lt;sequence>
 *         &lt;element name="LicensiatIP" type="{http://lanit.ru/ws/pgu/duplic_license}LicensiatIPType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestIPType", namespace = "http://lanit.ru/ws/pgu/duplic_license", propOrder = {
    "licensiatIP"
})
public class RequestIPType
    extends RequestBaseType
{

    @XmlElement(name = "LicensiatIP", namespace = "http://lanit.ru/ws/pgu/duplic_license", required = true)
    protected LicensiatIPType licensiatIP;

    /**
     * Gets the value of the licensiatIP property.
     * 
     * @return
     *     possible object is
     *     {@link LicensiatIPType }
     *     
     */
    public LicensiatIPType getLicensiatIP() {
        return licensiatIP;
    }

    /**
     * Sets the value of the licensiatIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link LicensiatIPType }
     *     
     */
    public void setLicensiatIP(LicensiatIPType value) {
        this.licensiatIP = value;
    }

}