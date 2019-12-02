package com.finastra.vaoo.client.ffdc.payment.exceptions;

import com.finastra.vaoo.client.ffdc.payment.model.PaymentResponse;

public class FFDCPaymentException extends RuntimeException {
    public FFDCPaymentException(String message) {
        super(message);
    }

    public FFDCPaymentException(PaymentResponse pr) {
         super(
                String.format("Payment %s is %s with status %s (%s)",
                        pr.getPaymentResourceId(),
                        pr.getTransactionStatus(),
                        pr.getStatusReasonCode(),
                        pr.getStatusReasonAdditionalInformation()));
    }
}
