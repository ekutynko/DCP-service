package ru.lanit.ws.pgu.lod.request;

/**
 * Created by Sergey on 31.08.2014.
 */
public enum LodResponseStatus {

    ACCEPT(0), ERROR(1);

    private int code;

    private LodResponseStatus(int code) {
        this.code = code;
    }

    public static LodResponseStatus fromCode(Integer code) {
        if (code != null) {
            for (LodResponseStatus status : LodResponseStatus.values()) {
                if (status.code == code) {
                    return status;
                }
            }
        }
        throw new IllegalStateException("Статус обработки заявки не указан или не верен");
    }
}
