//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.29 at 11:39:31 AM MSK 
//


package ru.lanit.ws.pgu.beans.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CatalogResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Message" type="{http://smev.gosuslugi.ru/rev120315}MessageType" minOccurs="0"/>
 *         &lt;element name="MessageData" type="{http://smev.gosuslugi.ru/rev120315}CatalogResponseMessageDataType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogResponseType", namespace = "http://smev.gosuslugi.ru/rev120315", propOrder = {
    "message",
    "messageData"
})
public class CatalogResponseType {

    @XmlElementRef(name = "Message", namespace = "http://smev.gosuslugi.ru/rev120315", type = JAXBElement.class)
    protected JAXBElement<MessageType> message;
    @XmlElementRef(name = "MessageData", namespace = "http://smev.gosuslugi.ru/rev120315", type = JAXBElement.class)
    protected JAXBElement<CatalogResponseMessageDataType> messageData;

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MessageType }{@code >}
     *     
     */
    public JAXBElement<MessageType> getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MessageType }{@code >}
     *     
     */
    public void setMessage(JAXBElement<MessageType> value) {
        this.message = ((JAXBElement<MessageType> ) value);
    }

    /**
     * Gets the value of the messageData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CatalogResponseMessageDataType }{@code >}
     *     
     */
    public JAXBElement<CatalogResponseMessageDataType> getMessageData() {
        return messageData;
    }

    /**
     * Sets the value of the messageData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CatalogResponseMessageDataType }{@code >}
     *     
     */
    public void setMessageData(JAXBElement<CatalogResponseMessageDataType> value) {
        this.messageData = ((JAXBElement<CatalogResponseMessageDataType> ) value);
    }

}
