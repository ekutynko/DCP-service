//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.29 at 11:31:15 AM MSK 
//


package ru.lanit.ws.pgu.beans.application.lanit.ws.pgu.reform_client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestULType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestULType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://lanit.ru/ws/pgu/reform_client}RequestBaseType">
 *       &lt;sequence>
 *         &lt;element name="LicensiatUL" type="{http://lanit.ru/ws/pgu/reform_client}LicensiatULType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestULType", namespace = "http://lanit.ru/ws/pgu/reform_client", propOrder = {
    "licensiatUL"
})
public class RequestULType
    extends RequestBaseType
{

    @XmlElement(name = "LicensiatUL", namespace = "http://lanit.ru/ws/pgu/reform_client", required = true)
    protected LicensiatULType licensiatUL;

    /**
     * Gets the value of the licensiatUL property.
     * 
     * @return
     *     possible object is
     *     {@link LicensiatULType }
     *     
     */
    public LicensiatULType getLicensiatUL() {
        return licensiatUL;
    }

    /**
     * Sets the value of the licensiatUL property.
     * 
     * @param value
     *     allowed object is
     *     {@link LicensiatULType }
     *     
     */
    public void setLicensiatUL(LicensiatULType value) {
        this.licensiatUL = value;
    }

}
