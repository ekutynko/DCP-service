package ru.lanit.ws.common;

/**
 * Created with IntelliJ IDEA.
 * User: Roman Orlov
 * Date: 05.09.12
 * Time: 15:46
 */

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Класс содержит вспомогательные и часто используемые функции
 */
public final class Util {

    /**
     * Преобразует время в формат XML
     * @param date время в формате java.util.Date
     * @return возвращает null в случае ошибки
     */
    public static XMLGregorianCalendar createXMLGregorianCalendar(Date date) {
        XMLGregorianCalendar xmlCalendar = null;
        GregorianCalendar cal = new GregorianCalendar();
        try {
            cal.setTime(date);
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            xmlCalendar = datatypeFactory.newXMLGregorianCalendar(cal);
        } catch (Exception e) {
            // nothing to do :)
        }
        return xmlCalendar;
    }

    /**
     * Создает JAXBElement для соответствующего поля JAXB-аннотированного класса
     * @param clazz JAXB-аннотированный класс
     * @param name имя поля
     * @param value значение
     * @return null если не удается найти поле или аннотацию
     */
    public static JAXBElement createElement(Class clazz, String name, Class valueClass, Object value) {
        JAXBElement element = null;
        try {
            Field field = clazz.getDeclaredField(name);
            XmlElementRef annotation = field.getAnnotation(XmlElementRef.class);
            element = new JAXBElement(new QName(annotation.namespace(), annotation.name()),
                    valueClass, value);
        } catch (Exception e) {
            // nothing to do :)
        }
        return element;
    }

    /**
     * Создает экземпляр Date для заданной даты (т.к. аналогичный конструктор
     * для Date объявлен как deprecated)
     */
    public static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

}
