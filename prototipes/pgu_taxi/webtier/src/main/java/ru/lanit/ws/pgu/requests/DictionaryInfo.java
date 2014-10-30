package ru.lanit.ws.pgu.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Элемент абстрактного словаря
 *
 * @author Roman Orlov
 */
public class DictionaryInfo {

    @NotNull
    @Size(min = 1)
    private String code;

    private String name;

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

}
