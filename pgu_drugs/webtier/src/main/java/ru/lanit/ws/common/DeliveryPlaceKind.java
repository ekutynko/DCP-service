package ru.lanit.ws.common;

/**
 * Created by user06 on 18.09.2014.
 */
public enum DeliveryPlaceKind {

    /** Территориальный отдел */
    TERRIT_AUTH(1),

    /** Муниципальное образование */
    MUNICIPALITY(2);

    private int code;

    private DeliveryPlaceKind(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static DeliveryPlaceKind fromCode(int code) {
        for(DeliveryPlaceKind value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        return TERRIT_AUTH;
    }
}
