package ru.lanit.ws.pgu.requests;

import org.codehaus.jackson.annotate.JsonIgnore;

public class AttachmentDesc {

	private Long id;
	
	private String name;
	
	private String code;
	
	private String type;

    private String number;

    @JsonIgnore
	private byte[] digestValue;

	private byte[] data;
	
	public AttachmentDesc(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public AttachmentDesc(Long id, String name, byte[] data) {
		this(id, name);
		this.data = data;
	}

	public AttachmentDesc(Long id, String name, String code, String type, String number,
			byte[] digestValue, byte[] data) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.type = type;
        this.number = number;
		this.digestValue = digestValue;
		this.data = data;
	}

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
