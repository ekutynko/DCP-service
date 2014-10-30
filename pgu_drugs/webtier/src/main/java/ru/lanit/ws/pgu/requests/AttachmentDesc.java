package ru.lanit.ws.pgu.requests;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttachmentDesc", namespace = "http://lanit.ru/ws/commons/drug")
public class AttachmentDesc {

	private Long id;
	
	private String name;
	
	private String code;
	
	private String type;

    private String number;

    @JsonIgnore
	private byte[] digestValue;

	private byte[] data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

	public byte[] getDigestValue() {
		return digestValue;
	}

	public void setDigestValue(byte[] digestValue) {
		this.digestValue = digestValue;
	}
	
}
