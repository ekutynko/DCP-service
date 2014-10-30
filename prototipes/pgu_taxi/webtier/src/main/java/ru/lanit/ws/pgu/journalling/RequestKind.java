package ru.lanit.ws.pgu.journalling;

/**
 *
 * @author Roman Orlov
 */
public enum RequestKind {

    /** Межведомственное взаимодействие */
    INTERDEP(1),
    
    /** Запрос с ПГУ */
    PGU(2),
    
    /** Запрос выписки из реестра лицензий */
    SMEV(3);
    
    private int code;

    private RequestKind(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static RequestKind fromCode(int code) {
        for (RequestKind value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.valueOf(code));
    }
    
}
