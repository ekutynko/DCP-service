package ru.lanit.ws.pgu.journalling;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.saaj.SaajSoapMessage;

import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Date;

/**
 *
 * @author user006
 */
public abstract class BaseJournaling {
    
    @Autowired
    private JournalWriter journalWriter;
    
    private final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    
    /**
     * Преобразует SOAP-сообщение в строку
     * @throws Exception
     */
    protected String soapMessageToString(WebServiceMessage message) {
        try {
            SOAPMessage soapMessage = ((SaajSoapMessage)message).getSaajMessage();
            StringWriter stringWriter = new StringWriter();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(soapMessage.getSOAPPart().getContent(), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            return "";
        }
    }

    @Before("execution (* ru.lanit.ws.pgu.security.SignatureInterceptor.handleRequest(..)) "
            + "&& args(mc,..)")
    public void journalHandleRequest(MessageContext mc) {
        journalWriter.beginNewEntry();
        journalWriter.setRequestDate(new Date());
        journalWriter.setRequestText(soapMessageToString(mc.getRequest()));
    }
    
    @After("execution (* ru.lanit.ws.pgu.security.SignatureInterceptor.handleResponse(..)) "
            + "&& args(mc,..)")
    public void journalHandleResponse(MessageContext mc) {
        journalWriter.setResponseDate(new Date());
        journalWriter.setResponseText(soapMessageToString(mc.getResponse()));
        journalWriter.setStatus(1001);
        journalWriter.send();
    }
    
    @After("execution (* ru.lanit.ws.pgu.security.SignatureInterceptor.handleFault(..)) "
            + "&& args(mc,..)")
    public void journalHandleFault(MessageContext mc) {
        journalWriter.setResponseDate(new Date());
        journalWriter.setResponseText(soapMessageToString(mc.getResponse()));
        journalWriter.setStatus(1002);
        journalWriter.send();
    }
    
}
