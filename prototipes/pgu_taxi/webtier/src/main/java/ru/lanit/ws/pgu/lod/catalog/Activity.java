package ru.lanit.ws.pgu.lod.catalog;

import ru.lanit.ws.pgu.PGUExceptions;

/**
 * Created by user06 on 08.09.2014.
 */
public enum Activity {

    /**
     * Вид деятельности "Лицензирование алкогольной продукции"
     */
    ALCOHOL(1),

    /**
     * Вид деятельности "Лицензирование разрешительной деятельности по перевозке людей"
     */
    TAXI(2);

    private int code;

    private Activity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Activity fromText(String text) throws PGUExceptions.ApplicationError {
        for (Activity value : values()) {
            if (value.name().equals(text)) {
                return value;
            }
        }
        throw new PGUExceptions.ApplicationError(String.format("Не верный код вида деятельности '%s'", text));
    }
}
