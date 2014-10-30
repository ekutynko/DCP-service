package ru.lanit.ws.pgu.journalling;

import org.springframework.beans.factory.annotation.Autowired;
import ru.lanit.ws.pgu.lod.LodRequestSender;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roman Orlov
 */
public class JournalWriter {

    @Autowired
    private LodRequestSender lodRequestSender;

    private long licenseRequestId;

    private JournalEntry journalEntry;

    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    /**
     * Сбрасывает состояние текущей записи и создает новую
     */
    public void beginNewEntry() {
        licenseRequestId = -1;
        journalEntry = new JournalEntry();
    }

    /**
     * Записывает текущую запись в журнал
     */
    public void send() {
        try {
            journalEntry.setLicenseRequestId(licenseRequestId);
            lodRequestSender.sendJournalEntry("ws/pgu/journal", journalEntry);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка при отправке записи журнала", e);
        }
    }

    public void setRequestKind(RequestKind requestKind) {
        journalEntry.setRequestKind(requestKind);
    }

    public void setRequestName(RequestName requestName) {
        journalEntry.setRequestName(requestName);
    }

    public void setLicenseRequestId(long licenseRequestId) {
        this.licenseRequestId = licenseRequestId;
    }

    public void setStatus(int status) {
        journalEntry.setStatus(status);
    }

    public void setRequestActor(String actor) {
        journalEntry.setRequestActor(actor);
    }

    public void setRequestDate(Date date) {
        journalEntry.setRequestDate(date);
    }

    public void setRequestText(String text) {
        journalEntry.setRequestText(text);
    }

    public void setResponseActor(String actor) {
        journalEntry.setResponseActor(actor);
    }

    public void setResponseDate(Date date) {
        journalEntry.setResponseDate(date);
    }

    public void setResponseText(String text) {
        journalEntry.setResponseText(text);
    }
}
