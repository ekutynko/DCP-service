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
 * <p>Java class for CatalogResponseMessageDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogResponseMessageDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AppData" type="{http://lanit.ru/ws/pgu/types}CatalogResponseAppDataType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogResponseMessageDataType", namespace = "http://smev.gosuslugi.ru/rev120315", propOrder = {
    "appData"
})
public class CatalogResponseMessageDataType {

    @XmlElement(name = "AppData", namespace = "http://smev.gosuslugi.ru/rev120315")
    protected CatalogResponseAppDataType appData;

    /**
     * Gets the value of the appData property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogResponseAppDataType }
     *     
     */
    public CatalogResponseAppDataType getAppData() {
        return appData;
    }

    /**
     * Sets the value of the appData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogResponseAppDataType }
     *     
     */
    public void setAppData(CatalogResponseAppDataType value) {
        this.appData = value;
    }

}