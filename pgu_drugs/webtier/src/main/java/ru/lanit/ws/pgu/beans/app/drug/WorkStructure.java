package ru.lanit.ws.pgu.beans.app.drug;


import ru.lanit.ws.pgu.beans.common.DictionaryItem;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Сведения о составе работ (услуг) — привязывается к месту деятельности
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkStructure", namespace = "http://lanit.ru/ws/pgu/drug")
public class WorkStructure {

    /**
     * Состав работ
     */
    @NotNull
    @Size(min = 1)
    @XmlElementWrapper(name="Works")
    @XmlElement(name = "Work", required = true)
    private List<DictionaryItem> works;

    /**
     * Примечание
     */
    @XmlElement(name = "Comment", required = false)
    private String comment;

    public List<DictionaryItem> getWorks() {
        return works;
    }

    public void setWorks(List<DictionaryItem> works) {
        this.works = works;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
