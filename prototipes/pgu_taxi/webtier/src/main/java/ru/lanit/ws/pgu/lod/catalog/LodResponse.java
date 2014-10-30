package ru.lanit.ws.pgu.lod.catalog;

import ru.lanit.ws.pgu.lod.LodBaseResponse;

import java.util.List;

/**
 * Created by user06 on 03.09.2014.
 */
public class LodResponse extends LodBaseResponse {

    private List<CatalogItem> catalogData;

    public List<CatalogItem> getCatalogData() {
        return catalogData;
    }

    public void setCatalogData(List<CatalogItem> catalogData) {
        this.catalogData = catalogData;
    }
}
