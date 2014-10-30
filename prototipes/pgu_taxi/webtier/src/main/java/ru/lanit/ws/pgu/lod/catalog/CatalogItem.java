package ru.lanit.ws.pgu.lod.catalog;

/**
 * Created by user06 on 03.09.2014.
 */
public class CatalogItem {

    private String code;

    private String name;

    private Boolean optional;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

}
