package ru.lanit.ws.pgu;

/**
 * Created with IntelliJ IDEA.
 * User: Roman Orlov
 * Date: 28.09.12
 * Time: 12:49
 */

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 * Содержит классы возможных исключительных ситуаций для ПГУ
 */
public class PGUExceptions {

    /**
     * Базовый класс для всех "пользовательских" эксепшнов
     */
    public static class BaseException extends Exception {

        private String code;

        private String description;

        public BaseException(String code, String message, String description) {
            super(message);
            this.description = description;
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public String getCode() {
            return code;
        }

    }

    @SoapFault(faultCode= FaultCode.CUSTOM, customFaultCode="{http://lanit.ru/ws/error}applicationError")
    public static class ApplicationError extends BaseException {

        public ApplicationError(String description) {
            super("applicationError", "Данные заявления некорректны" + ": " + description, description);
        }

    }

    @SoapFault(faultCode= FaultCode.CUSTOM, customFaultCode="{http://lanit.ru/ws/error}internalError")
    public static class InternalError extends BaseException {

        public InternalError() {
            super("internalError", "Внутрення ошибка", "");
        }

        public InternalError(String description) {
            super("internalError", "Внутрення ошибка" + ": " + description, description);
        }

    }

    @SoapFault(faultCode= FaultCode.CUSTOM, customFaultCode="{http://lanit.ru/ws/error}signatureError")
    public static class SignatureError extends BaseException {

        public SignatureError() {
            super("signatureError", "Ошибка при проверке ЭЦП", "");
        }

    }

}
