package ru.laint.ws.pgu.beans.app.drug;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
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
    private Date paymentDate;

    /**
     * Сумма платежа
     */
    @XmlElement(name = "PaymentSum", required = true)
    private Date paymentSum;

    /**
     * Назначение платежа
     */
    @XmlElement(name = "PaymentPurpose", required = true)
    private Date paymentPurpose;

    /**
     * Номер платежного поручения
     */
    @XmlElement(name = "PaymentNo", required = true)
    private Date paymentNo;

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getPaymentSum() {
        return paymentSum;
    }

    public void setPaymentSum(Date paymentSum) {
        this.paymentSum = paymentSum;
    }

    public Date getPaymentPurpose() {
        return paymentPurpose;
    }

    public void setPaymentPurpose(Date paymentPurpose) {
        this.paymentPurpose = paymentPurpose;
    }

    public Date getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(Date paymentNo) {
        this.paymentNo = paymentNo;
    }
}
