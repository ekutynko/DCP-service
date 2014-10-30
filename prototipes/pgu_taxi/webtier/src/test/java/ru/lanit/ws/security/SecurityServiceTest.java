package ru.lanit.ws.security;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.Node;

@Ignore
public class SecurityServiceTest {

	private SecurityServiceImpl serviceImpl = new SecurityServiceImpl();

	@Test
	public void testVerifySign() throws Exception {
		URL u = this.getClass().getClassLoader().getResource("test.xml");

		FileInputStream is = new FileInputStream(new File(u.toURI()));

		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage sm = mf.createMessage();
		SOAPPart soapPart = sm.getSOAPPart();

		soapPart.setContent(new StreamSource(is));
		SaajSoapMessage msg = new SaajSoapMessage(sm);
		//sign message
		serviceImpl.setKeyStorePath("HDImageStore");
        serviceImpl.setAliasName("test_key");
		serviceImpl.setPassword("test");

		serviceImpl.signMessage(msg.getSaajMessage());

		boolean result = serviceImpl.verifyDocument(msg.getSaajMessage().getSOAPPart().getEnvelope().getOwnerDocument());
		Assert.assertTrue(result);

	}


	@Test
	public void testVerifyCorruptedSign() throws Exception {
		URL u = this.getClass().getClassLoader().getResource("test.xml");

		FileInputStream is = new FileInputStream(new File(u.toURI()));

		MessageFactory mf = MessageFactory.newInstance();

		SOAPMessage sm = mf.createMessage();
		SOAPPart soapPart = sm.getSOAPPart();

		soapPart.setContent(new StreamSource(is));
		SaajSoapMessage msg = new SaajSoapMessage(sm);
		//sign message
        serviceImpl.setKeyStorePath("HDImageStore");
		serviceImpl.setAliasName("test_key");
		serviceImpl.setPassword("test");

		serviceImpl.signMessage(msg.getSaajMessage());

		Node txt = msg.getSaajMessage().getSOAPPart().getEnvelope().getOwnerDocument().createTextNode("bla-bla");
		Node nd = msg.getSaajMessage().getSOAPBody().getChildNodes().item(1);
		nd.appendChild(txt);
		boolean result = serviceImpl.verifyDocument(msg.getSaajMessage().getSOAPPart().getEnvelope().getOwnerDocument());
		Assert.assertFalse(result);

	}

}
