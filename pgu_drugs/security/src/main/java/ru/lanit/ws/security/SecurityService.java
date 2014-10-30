package ru.lanit.ws.security;

import java.util.List;

import javax.xml.soap.SOAPMessage;
import org.w3c.dom.Document;

public interface SecurityService {

	void signMessage(SOAPMessage soapMessage) throws Exception;

	boolean verifyDocument(Document doc) throws Exception;

	boolean verify(byte[] data, byte[] signature) throws Exception;

	List<byte[]> signByteArray(byte[] data) throws Exception;

}
