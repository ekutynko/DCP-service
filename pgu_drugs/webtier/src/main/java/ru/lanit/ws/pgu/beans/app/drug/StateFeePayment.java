package ru.lanit.ws.pgu.beans.app.drug;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Сведения об оплате государственной пошлины
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateFeePayment", namespace = "http://lanit.ru/ws/pgu/drug")
public class StateFeePayment {

    /**
     * Дата платежа
     */
    @XmlElement(name = "PaymentDate", required = true)
    @XmlSchemaType(name="date")
    private Date paymentDate;

    /**
     * Сумма платежа
     */
    @Pattern(regexp = "\\b(\\d{1,18})\\b")
    @XmlElement(name = "PaymentSum", required = true)
    private String paymentSum;

    /**
     * Назначение платежа
     */
    @Size(min = 1, max = 210)
    @XmlElement(name = "PaymentPurpose", required = true)
    private String paymentPurpose;

    /**
     * Номер платежного поручения
     */
    @Size(min = 1, max = 20)
    @XmlElement(name = "PaymentNo", required = true)
    private String paymentNo;

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(String paymentSum) {
        this.paymentSum = paymentSum;
    }

    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(String paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
}
