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
 * <p>Java class for StatusResponseAppDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusResponseAppDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="statusResponseInfo" type="{http://lanit.ru/ws/pgu/types}StatusResponseInfoType"/>
 *         &lt;element ref="{http://lanit.ru/ws/pgu/types}error"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusResponseAppDataType", namespace = "http://lanit.ru/ws/pgu/types", propOrder = {
    "statusResponseInfo",
    "error"
})
public class StatusResponseAppDataType {

    @XmlElement(namespace = "http://lanit.ru/ws/pgu/types")
    protected StatusResponseInfoType statusResponseInfo;
    @XmlElement(namespace = "http://lanit.ru/ws/pgu/types")
    protected ServiceErrorInfoType error;

    /**
     * Gets the value of the statusResponseInfo property.
     * 
     * @return
     *     possible object is
     *     {@link StatusResponseInfoType }
     *     
     */
    public StatusResponseInfoType getStatusResponseInfo() {
        return statusResponseInfo;
    }

    /**
     * Sets the value of the statusResponseInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusResponseInfoType }
     *     
     */
    public void setStatusResponseInfo(StatusResponseInfoType value) {
        this.statusResponseInfo = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceErrorInfoType }
     *     
     */
    public ServiceErrorInfoType getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceErrorInfoType }
     *     
     */
    public void setError(ServiceErrorInfoType value) {
        this.error = value;
    }

}
