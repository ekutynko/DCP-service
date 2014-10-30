package ru.lanit.ws.pgu.requests;

/**
 *
 * @author Compmaniak
 */
public enum PGUStatus {

    IN_QUEUE("Промежуточные результаты от ведомства", 7),
    
    ACCEPTED("Принято от заявителя", 1),
    
    REJECTED("Отказ", 4),
    
    READY("Исполнено", 3);
    
    //DENIED("Отказано");

    private String text;

    private int code;

    private PGUStatus(String text, int code) {
        this.text = text;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }
}
