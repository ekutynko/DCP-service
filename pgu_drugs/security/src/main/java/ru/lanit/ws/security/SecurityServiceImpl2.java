package ru.lanit.ws.security;

import com.objsys.asn1j.runtime.*;
import java.io.ByteArrayInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.token.X509Security;
import org.apache.xml.security.transforms.Transforms;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.*;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Certificate;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.CertificateSerialNumber;
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Name;
import ru.CryptoPro.JCP.JCP;
import ru.CryptoPro.JCP.params.OID;

public class SecurityServiceImpl2 implements SecurityService {

    //private static final String STR_CMS_OID_SIGNED = "1.2.840.113549.1.9.3";
    //private static final String STR_CMS_OID_DATA = "1.2.840.113549.1.7.1";
    private static final String STR_CMS_OID_SIGNED = "1.2.840.113549.1.7.2";
    private static final String STR_CMS_OID_DATA = "1.2.840.113549.1.7.1";
    private String keyStorePath;
    private String aliasName;
    private String password;
    private final String STORE_TYPE = JCP.HD_STORE_NAME;
    private KeyStore keyStore;
    private Provider xmlDSigProvider = new ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI();

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    /* (non-Javadoc)
     * @see ru.lanit.ws.security.SecurityService#signMessage(org.springframework.ws.soap.saaj.SaajSoapMessage)
     */
    @Override
    public void signMessage(SOAPMessage msg) throws Exception {

        msg.getSOAPPart().getEnvelope().addNamespaceDeclaration("wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        msg.getSOAPPart().getEnvelope().addNamespaceDeclaration("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        msg.getSOAPPart().getEnvelope().addNamespaceDeclaration("ds", "http://www.w3.org/2000/09/xmldsig#");
        //msg.getSOAPPart().getEnvelope().getBody().addAttribute(new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "wsu:Id"), "body");
        msg.getSOAPPart().getEnvelope().getBody().addAttribute(new QName("", "wsu:Id"), "body");

        WSSecHeader header = new WSSecHeader();
        header.setActor("http://smev.gosuslugi.ru/actors/smev");
        header.setMustUnderstand(false);

        Element sec = header.insertSecurityHeader(msg.getSOAPPart());

        Document doc = msg.getSOAPPart().getEnvelope().getOwnerDocument();
        Element token = (Element) sec.appendChild(
                doc.createElementNS("", "wsse:BinarySecurityToken"));
        token.setAttribute("EncodingType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary");
        token.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        String certIdGUID = java.util.UUID.randomUUID().toString();

        token.setAttribute("wsu:Id", "CertId-" + certIdGUID);
        //token.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        //token.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        header.getSecurityHeader().appendChild(token);

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);

        List<Transform> transformList = new ArrayList<Transform>();
        Transform transformC14N = fac.newTransform(Transforms.TRANSFORM_C14N_EXCL_OMIT_COMMENTS, (XMLStructure) null);
        transformList.add(transformC14N);

        Reference ref = fac.newReference(
                "#body",
                fac.newDigestMethod("http://www.w3.org/2001/04/xmldsig-more#gostr3411", null),
                //null, null, null);
                transformList, null, null);
        //Collections.singletonList(transform), null, null);

        // Make link to signing element
        SignedInfo si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.EXCLUSIVE,
                (C14NMethodParameterSpec) null),
                fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#gostr34102001-gostr3411", null),
                Collections.singletonList(ref));

        // Prepare key information to verify signature in future on other side
        KeyInfoFactory kif = fac.getKeyInfoFactory();

        X509Certificate cert = getCertificate();
        X509Data x509d = kif.newX509Data(Collections.singletonList(cert));
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(x509d));


        // Create signature and sign by private key
        XMLSignature sig = fac.newXMLSignature(si, ki);

        PrivateKey privateKey = getPrivateKey();

        DOMSignContext signContext = new DOMSignContext(privateKey, token);
        signContext.putNamespacePrefix(XMLSignature.XMLNS, "ds");
        sig.sign(signContext);



        // Insert signature node in document
        Element sigE = (Element) XPathAPI.selectSingleNode(signContext.getParent(), "//ds:Signature");
        Node keyE = XPathAPI.selectSingleNode(sigE, "//ds:KeyInfo", sigE);
        token.appendChild(doc.createTextNode(XPathAPI.selectSingleNode(keyE, "//ds:X509Certificate", keyE).getFirstChild().getNodeValue()));
        keyE.removeChild(XPathAPI.selectSingleNode(keyE, "//ds:X509Data", keyE));
        NodeList chl = keyE.getChildNodes();

        for (int i = 0; i < chl.getLength(); i++) {
            keyE.removeChild(chl.item(i));
        }

        Element secTokenRef = doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:SecurityTokenReference");
        secTokenRef.setAttribute("wsu:Id", "StrId-" + java.util.UUID.randomUUID().toString());
        //secTokenRef.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        //secTokenRef.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Node str = keyE.appendChild(secTokenRef);

        Element reference = doc.createElementNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "wsse:Reference");

        //reference.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        Element strRef = (Element) str.appendChild(reference);

        strRef.setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509v3");
        strRef.setAttribute("URI", "#CertId-" + certIdGUID);
        header.getSecurityHeader().appendChild(sigE);

    }

    //load and return private key from jks based on aliasName
    private PrivateKey getPrivateKey() throws Exception {
        PrivateKey result;
        KeyStore keyStore = loadKeyStore();
        result = (PrivateKey) keyStore.getKey(aliasName, password.toCharArray());
        return result;
    }

    private synchronized KeyStore loadKeyStore() throws Exception {
        if (keyStore == null) {
            keyStore = KeyStore.getInstance(keyStorePath);
            //InputStream is = new FileInputStream(keyStorePath); // exception
            //keyStore.load(is, password.toCharArray()); // exception
            keyStore.load(null, null); // exception
        }
        return keyStore;
    }

    public X509Certificate getCertificate() throws Exception {
        KeyStore keyStore = loadKeyStore();
        X509Certificate result = (X509Certificate) keyStore.getCertificate(aliasName);
//        Enumeration<String> enumeration = keyStore.aliases();
//        while(enumeration.hasMoreElements()) {
//        	String el= enumeration.nextElement();
//        	System.out.println(el);
//        };
        return result;
    }

    //
    public boolean verify(byte[] data, byte[] signature) throws Exception {
        boolean result;
        Asn1BerDecodeBuffer arg0 = new Asn1BerDecodeBuffer(signature);
        ContentInfo all = new ContentInfo();
        all.decode(arg0);
        SignedData cms = new SignedData();
        arg0 = new Asn1BerDecodeBuffer(((Asn1OpenType) all.content).toInputStream());
        cms.decode(arg0);
//		SignedData cms = (SignedData) all.content;

        final byte[] text;
        if (cms.encapContentInfo.eContent != null) {
            text = cms.encapContentInfo.eContent.value;
        } else if (data != null) {
            text = data;
        } else {
            throw new Exception("No content for verify");
        }
        OID digestOid = null;
        final DigestAlgorithmIdentifier digestAlgorithmIdentifier = new DigestAlgorithmIdentifier(
                new OID(JCP.GOST_DIGEST_OID).value);
        for (int i = 0; i < cms.digestAlgorithms.elements.length; i++) {
            if (cms.digestAlgorithms.elements[i].algorithm
                    .equals(digestAlgorithmIdentifier.algorithm)) {
                digestOid = new OID(
                        cms.digestAlgorithms.elements[i].algorithm.value);
                break;
            }
        }
        if (digestOid == null) {
            throw new Exception("Unknown digest");
        }
        final OID eContTypeOID = new OID(
                cms.encapContentInfo.eContentType.value);
        result = true;
        if (cms.certificates != null) {
            for (int i = 0; i < cms.certificates.elements.length; i++) {
                final Asn1BerEncodeBuffer encBuf = new Asn1BerEncodeBuffer();
                cms.certificates.elements[i].encode(encBuf);

                final CertificateFactory cf = CertificateFactory
                        .getInstance("X.509");
                final X509Certificate cert = (X509Certificate) cf
                        .generateCertificate(encBuf.getInputStream());

                for (int j = 0; j < cms.signerInfos.elements.length; j++) {
                    final SignerInfo info = cms.signerInfos.elements[j];
                    if (!digestOid.equals(new OID(
                            info.digestAlgorithm.algorithm.value))) {
                        throw new Exception("Not signed on certificate.");
                    }
                    result = result && verifyOnCert(cert,
                            cms.signerInfos.elements[j], text, eContTypeOID);
                }
            }
        }


        return result;
    }

    private static boolean verifyOnCert(X509Certificate cert, SignerInfo info,
            byte[] text, OID eContentTypeOID) throws Exception {
        final byte[] sign = info.signature.value;
        final byte[] data;
        data = text;

        final Signature signature = Signature
                .getInstance(JCP.GOST_EL_SIGN_NAME);
        signature.initVerify(cert);
        signature.update(data);
        return signature.verify(sign);
    }

    public List<byte[]> signByteArray(byte[] data) throws Exception {
        ContentInfo all = new ContentInfo();
        all.contentType = new Asn1ObjectIdentifier(new OID(STR_CMS_OID_SIGNED).value);
        final SignedData cms = new SignedData();
        all.content = cms;
        //cms.version = new CMSVersion(4);
        cms.version = new CMSVersion(1);
        cms.digestAlgorithms = new DigestAlgorithmIdentifiers(new DigestAlgorithmIdentifier[]{new DigestAlgorithmIdentifier(new OID(JCP.GOST_DIGEST_OID).value)});
        cms.encapContentInfo = new EncapsulatedContentInfo(
                new Asn1ObjectIdentifier(
                new OID(STR_CMS_OID_DATA).value),
                null);
        X509Certificate cert = getCertificate();

        cms.certificates = new CertificateSet(1);
        cms.certificates.elements = new CertificateChoices[1];
        cms.certificates.elements[0] = new CertificateChoices();
        cms.certificates.elements[0].set_certificate(convertToAsn1Certificate(cert));

        // Signature.getInstance
        byte[] sign;

        Signature signature = Signature.getInstance(JCP.GOST_EL_SIGN_NAME);
        signature.initSign(getPrivateKey());

        signature.update(data);
        sign = signature.sign();

        // signer infos

        cms.signerInfos = new SignerInfos(1);
        cms.signerInfos.elements[0] = new SignerInfo();
        cms.signerInfos.elements[0].version = new CMSVersion(1);                //because of issuerAndSerialNumber. More: rfc.3852, p.13.
        cms.signerInfos.elements[0].sid = new SignerIdentifier();
        final byte[] encodedName = cert.getIssuerX500Principal().getEncoded();
        final Asn1BerDecodeBuffer nameBuf = new Asn1BerDecodeBuffer(encodedName);
        final Name name = new Name();
        name.decode(nameBuf);

        final CertificateSerialNumber num = new CertificateSerialNumber(cert.getSerialNumber());
        cms.signerInfos.elements[0].sid.set_issuerAndSerialNumber(new IssuerAndSerialNumber(name, num));
        cms.signerInfos.elements[0].digestAlgorithm = new DigestAlgorithmIdentifier(new OID(JCP.GOST_DIGEST_OID).value);
        cms.signerInfos.elements[0].digestAlgorithm.parameters = new Asn1Null();
        cms.signerInfos.elements[0].signatureAlgorithm = new SignatureAlgorithmIdentifier(new OID(JCP.GOST_EL_SIGN_OID).value);
        cms.signerInfos.elements[0].signatureAlgorithm.parameters = new Asn1Null();

        cms.signerInfos.elements[0].signature = new SignatureValue(sign);
        final Asn1BerEncodeBuffer asnBuf = new Asn1BerEncodeBuffer();
        all.encode(asnBuf, true);
        List<byte[]> r = new LinkedList<byte[]>();
        r.add(data);
        r.add(asnBuf.getMsgCopy());
        return r;//asnBuf.getMsgCopy();
    }

    private Certificate convertToAsn1Certificate(X509Certificate cert) throws Exception {
        Certificate c = new Certificate();
        Asn1BerDecodeBuffer db = new Asn1BerDecodeBuffer(cert.getEncoded());
        c.decode(db);
        return c;
    }

    /* (non-Javadoc)
     * @see ru.lanit.ws.security.SecurityService#verifyDocument(org.w3c.dom.Document)
     */
    @Override
    public boolean verifyDocument(Document doc) throws Exception {
        final Element wssecontext = doc.createElementNS(null, "namespaceContext");
        wssecontext.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:" + "wsse".trim(), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        NodeList secnodeList = XPathAPI.selectNodeList(doc.getDocumentElement(), "//wsse:Security");

        Element r = null;
        r = extractCertificate(wssecontext, secnodeList, r);
        if (r == null) {
            return false;
        }

        final X509Security x509 = new X509Security(r);

        // Extract certificate
        final X509Certificate cert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(x509.getToken()));

        if (cert == null) {
            throw new Exception("Cannot find certificate to verify signature");
        }

        // Get signature node
        NodeList nl = doc.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("Cannot find Signature element");
        }

        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM", xmlDSigProvider);
        // Set public key
        DOMValidateContext valContext = new DOMValidateContext(KeySelector.singletonKeySelector(cert.getPublicKey()), nl.item(0));
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);

        // Verify signature
        return signature.validate(valContext);
    }

    private Element extractCertificate(final Element wssecontext,
            NodeList secnodeList, Element r) throws TransformerException {
        Element el;
        if (secnodeList != null && secnodeList.getLength() > 0) {
            String actorAttr = null;
            for (int i = 0; i < secnodeList.getLength(); i++) {
                el = (Element) secnodeList.item(i);
                actorAttr = el.getAttributeNS("http://schemas.xmlsoap.org/soap/envelope/", "actor");
                if (actorAttr != null && actorAttr.equals("http://smev.gosuslugi.ru/actors/smev")) {
                    r = (Element) XPathAPI.selectSingleNode(el, "//wsse:BinarySecurityToken[1]", wssecontext);
                    break;
                }
            }
        }
        return r;
    }
}
