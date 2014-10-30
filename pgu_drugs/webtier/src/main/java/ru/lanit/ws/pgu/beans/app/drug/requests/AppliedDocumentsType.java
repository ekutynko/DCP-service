
package ru.lanit.ws.pgu.beans.app.drug.requests;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppliedDocumentsType", namespace = "http://lanit.ru/ws/commons", propOrder = {
    "appliedDocument"
})
public class AppliedDocumentsType {

    @XmlElement(name = "AppliedDocument", namespace = "http://lanit.ru/ws/commons")
    protected List<AppliedDocumentType> appliedDocument;

    public List<AppliedDocumentType> getAppliedDocument() {
        if (appliedDocument == null) {
            appliedDocument = new ArrayList<AppliedDocumentType>();
        }
        return this.appliedDocument;
    }

}
