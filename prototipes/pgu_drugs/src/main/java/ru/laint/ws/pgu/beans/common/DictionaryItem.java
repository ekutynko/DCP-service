//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.29 at 11:39:31 AM MSK 
//


package ru.laint.ws.pgu.beans.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DictionaryItem", namespace = "http://lanit.ru/ws/commons")

public class DictionaryItem {

    @XmlElement(namespace = "http://lanit.ru/ws/commons", required = true)
    protected String code;
    @XmlElement(namespace = "http://lanit.ru/ws/commons", required = true)
    protected String name;
    @XmlElement(namespace = "http://lanit.ru/ws/commons")
    protected Boolean optional;

    public DictionaryItem() {
    }

    public DictionaryItem(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Boolean isOptional() {
        return optional;
    }

    public void setOptional(Boolean value) {
        this.optional = value;
    }

}
