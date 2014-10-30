package ru.lanit.ws.pgu.journalling;

/**
 *
 * @author Roman Orlov
 */
public enum RequestName {
    
    /** Запрос в ФНС */
    FNS_REQUEST(1),
    
    /** Запрос в Росреестр */
    REGISTRY_REQUEST(2),
    
    /** Запрос в Казначейство */
    TREASURY_REQUEST(3),
    
    /** Запрос на оказание услуги */
    LICENSE_REQUEST(4),
    
    /** Отмена заявки на оказание услуги */
    CANCEL_LICENSE_REQUEST(5),
    
    /** Запрос справочника */
    GET_DICTIONARY(6),
    
    /** Запрос выписки из реестра лицензий для ИП */
    GET_LICENSE_IP(7),
    
    /** Запрос выписки из реестра лицензий для ЮЛ */
    GET_LICENSE_UL(8);
    
    private int code;

    private RequestName(int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    public static RequestName fromCode(int code) {
        for (RequestName value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.valueOf(code));
    }
    
}
