package ru.lanit.ws.pgu.journalling;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import ru.lanit.ws.pgu.lod.serializers.JsonDateTimeSerializer;

import java.util.Date;

/**
 * Created by Sergey on 31.08.2014.
 */
public class JournalEntry {

    private long licenseRequestId;

    private String requestText;

    private String responseText;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private Date requestDate;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private Date responseDate;

    private int status;

    private String requestActor;

    private String responseActor;

    /** Тип запроса */
    private Integer requestKind;

    /** Наименование запроса */
    private Integer requestName;

    public long getLicenseRequestId() {
        return licenseRequestId;
    }

    public void setLicenseRequestId(long licenseRequestId) {
        this.licenseRequestId = licenseRequestId;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRequestActor() {
        return requestActor;
    }

    public void setRequestActor(String requestActor) {
        this.requestActor = requestActor;
    }

    public String getResponseActor() {
        return responseActor;
    }

    public void setResponseActor(String responseActor) {
        this.responseActor = responseActor;
    }

    public Integer getRequestKind() {
        return requestKind;
    }

    public void setRequestKind(RequestKind requestKind) {
        this.requestKind = requestKind.getCode();
    }

    public Integer getRequestName() {
        return requestName;
    }

    public void setRequestName(RequestName requestName) {
        this.requestName = requestName.getCode();
    }
}
