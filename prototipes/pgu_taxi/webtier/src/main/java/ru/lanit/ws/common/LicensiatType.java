package ru.lanit.ws.common;

/**
 * Created with IntelliJ IDEA.
 * User: Roman Orlov
 * Date: 16.08.12
 * Time: 15:15
 */

public enum LicensiatType {

    /** Юридическое лицо */
    FIRM(1),

    /** Индивидуальный предприниматель */
    INDIVIDUAL(2);

    private int code;

    private LicensiatType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static LicensiatType fromCode(int code) {
        for(LicensiatType value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        return FIRM;
    }

}
