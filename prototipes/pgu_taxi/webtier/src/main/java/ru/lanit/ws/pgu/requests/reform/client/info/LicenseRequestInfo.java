package ru.lanit.ws.pgu.requests.reform.client.info;

import ru.lanit.ws.pgu.requests.DictionaryInfo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Данные запрашиваемой лицензии
 * 
 * @author Roman Orlov
 */
public class LicenseRequestInfo {

    /** Причина переоформления */
    @NotNull
    @Size(min = 1)
    private List<DictionaryInfo> reasons;

    public List<DictionaryInfo> getReasons() {
        return reasons;
    }

    public void setReasons(List<DictionaryInfo> reasons) {
        this.reasons = reasons;
    }
    
}
