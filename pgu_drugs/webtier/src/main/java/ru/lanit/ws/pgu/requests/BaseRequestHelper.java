package ru.lanit.ws.pgu.requests;

import ru.lanit.ws.pgu.beans.common.IDictType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

public abstract class BaseRequestHelper {

    /**
     * Возвращает значение необязательного XML-элемента (чтоб избежать NullPointerException)
     */
    protected <T> T getValue(JAXBElement<T> element) {
        if (element != null) {
            return element.getValue();
        } else {
            return null;
        }
    }

    /**
     * Преобразует XMLGregorianCalendar в Date (чтоб избежать NullPointerException)
     */
    protected Date XMLCalendarToDate(XMLGregorianCalendar calendar) {
        if (calendar != null) {
            return calendar.toGregorianCalendar().getTime();
        } else {
            return null;
        }
    }

    public abstract BaseRequestComposite fillRequestComposite();

    protected DictionaryInfo toDictInfo(IDictType dictType) {
        if (dictType == null) {
            return null;
        }
        DictionaryInfo dictionaryInfo = new DictionaryInfo();
        dictionaryInfo.setCode(dictType.getCode());
        dictionaryInfo.setName(getValue(dictType.getName()));
        return dictionaryInfo;
    }

    protected List<DictionaryInfo> toDictList(List<? extends IDictType> dictTypeList) {
        if (dictTypeList == null) {
            return null;
        }
        List<DictionaryInfo> result = new ArrayList<DictionaryInfo>();
        for (IDictType dictType : dictTypeList) {
            DictionaryInfo dictionaryInfo = new DictionaryInfo();
            dictionaryInfo.setCode(dictType.getCode());
            dictionaryInfo.setName(getValue(dictType.getName()));
            result.add(dictionaryInfo);
        }
        return result;
    }

}
