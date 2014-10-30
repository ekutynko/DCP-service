package ru.lanit.ws.pgu.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.Document;
import ru.lanit.ws.security.SecurityService;

import javax.xml.soap.SOAPMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignatureInterceptor implements EndpointInterceptor {

	@Autowired
	private SecurityService securityService;

    /** эксепшн, возникаемый в случае неверно подписанного сообщения */
    private Exception signatureException;

    private final Logger logger = Logger.getLogger("SignatureInterceptor");

    public SignatureInterceptor() {
        signatureException = new Exception("Invalid signature");
    }

    public SignatureInterceptor(Exception signatureException) {
        this.signatureException = signatureException;
    }

    private void ensureUnicodeEncoding(SaajSoapMessage msg) {
        try {
            msg.getSaajMessage().setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        } catch (Exception e) {
            // пичалька :(
        }
    }

    @Override
	//handle signature
	public boolean handleRequest(MessageContext messageContext, Object endpoint)
			throws Exception {
//        boolean isValid = true;
//        try {
//            SaajSoapMessage msg = (SaajSoapMessage)messageContext.getRequest();
//            Document doc = msg.getSaajMessage().getSOAPPart().getEnvelope().getOwnerDocument();
//            isValid = securityService.verifyDocument(doc);
//        } catch (Exception e) {
//            logger.log(Level.SEVERE, "Exception while verifing the document", e);
//            throw signatureException;
//        }
//        if (!isValid) {
//            throw signatureException;
//        }
		return true;
	}


	@Override
	//sign response
	public boolean handleResponse(MessageContext messageContext, Object endpoint)
			throws Exception {
//		SaajSoapMessage msg = (SaajSoapMessage)messageContext.getResponse();
//        securityService.signMessage(msg.getSaajMessage());
//        ensureUnicodeEncoding(msg);
        return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext, Object endpoint)
			throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Object endpoint,
			Exception ex) {
		// TODO Auto-generated method stub

	}



}
