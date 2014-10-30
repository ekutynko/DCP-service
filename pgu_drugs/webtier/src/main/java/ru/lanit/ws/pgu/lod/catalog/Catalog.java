package ru.lanit.ws.pgu.lod.catalog;


import ru.lanit.ws.pgu.PGUExceptions;

/**
 * Коды справочников
 *
 * @author Roman Orlov
 */
public enum Catalog {

    /**
     * Справочник "Виды деятельности"
     */
    ACTIVITIES (1),
    /**
     * Справочник "Виды услуг"
     */
    PROCESS_TYPES(2),
    /**
     * Справочник "Документы, предоставляемые ПГУ"
     */
    PGU_DOCUMENTS(3),
    /**
     * Справочник "Причины переоформления"
     */
    REFORM_REASONS(4),
    /**
     * Справочник "Тип заявителей"
     */
    CLIENT_TYPES(5),
    /**
     * Справочник "Организационные формы"
     */
    ORG_FORMS(6),
    /**
     * Справочник "Территориальные отделы"
     */
    OBJECT_TYPES (7),
    /**
     * Справочник "Виды права"
     */
    RIGHT_TYPES (8),
    /**
     * Справочник "Составы работ"
     */
    ACTION_TYPES (9);


    private int code;

    private Catalog(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Catalog fromText(String text) throws PGUExceptions.ApplicationError {
        for (Catalog value : values()) {
            if (value.name().equals(text)) {
                return value;
            }
        }
        throw new PGUExceptions.ApplicationError(String.format("Не верный код справочника '%s'", text));
    }

}
