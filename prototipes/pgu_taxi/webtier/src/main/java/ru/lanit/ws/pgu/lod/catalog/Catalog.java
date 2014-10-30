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
    ACTIVITIES(1),
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
     * Справочник "Организационные формы"
     */
    ORG_FORMS(5),
    /**
     * Справочник "Территориальные отделы"
     */
    TERRIT_AUTHS(6),
    /**
     * Справочник "Муниципальные образования"
     */
    MUNICIPALITIES(7),
    /**
     * Справочник "Документы, удостоверяющие личность заявителя"
     */
    PASSPORT_TYPES(8),
    /**
     * Справочник "Код страны"
     */
    COUNTRY_CODES(9),
    /**
     * Справочник "Документы, устанавливающие право владения ТС"
     */
    CAR_DOCS(10),
    /**
     * Справочник "Цвета ТС"
     */
    CAR_COLORS(11),
    /**
     * Справочник "Экологические классы ТС"
     */
    CAR_ECO_TYPES(12),
    /**
     * Справочник "Марки ТС"
     */
    CAR_BRANDS(13),
    /**
     * Справочник "Модели ТС"
     */
    CAR_MODELS(14);


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
