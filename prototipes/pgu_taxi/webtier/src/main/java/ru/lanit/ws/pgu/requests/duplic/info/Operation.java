package ru.lanit.ws.pgu.requests.duplic.info;

/**
 * Created by user06 on 28.01.14.
 */
public enum Operation {

    /** Добавить */
    INSERT(1),

    /** Обновить */
    UPDATE(2),

    /** Удалить */
    DELETE(3);

    private int code;

    private Operation(int code) {
        this.code = code;
    }

    public static Operation fromCode(String code) {
        int c = Integer.parseInt(code);
        for (Operation value : values()) {
            if (value.code == c) {
                return value;
            }
        }
        return INSERT;
    }


}
