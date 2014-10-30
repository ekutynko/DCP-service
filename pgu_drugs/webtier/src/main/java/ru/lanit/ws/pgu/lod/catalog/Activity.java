package ru.lanit.ws.pgu.lod.catalog;

import ru.lanit.ws.pgu.PGUExceptions;

/**
 * Created by user06 on 08.09.2014.
 */
public enum Activity {

    /**  Вид деятельности "Лицензирование деятельности, связанной с оборотом наркотических средств
     * и психотропных веществ и их прекурсоров, по культивированию наркосодержащих растений"
     */
    NARCO(6);

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
