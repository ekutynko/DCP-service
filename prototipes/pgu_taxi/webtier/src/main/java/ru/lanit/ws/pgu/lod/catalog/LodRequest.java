package ru.lanit.ws.pgu.lod.catalog;

import javax.validation.constraints.NotNull;

/**
 * Created by user06 on 03.09.2014.
 */
public class LodRequest {

    private int catalogCode;

    private int activityCode;

    private String processCode;

    private String parent;

    public int getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(int catalogCode) {
        this.catalogCode = catalogCode;
    }

    public int getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(int activityCode) {
        this.activityCode = activityCode;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
