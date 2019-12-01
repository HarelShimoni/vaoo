package com.finastra.vaoo.service.payment;

import com.finastra.vaoo.web.model.payment.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto createPayment (PaymentDto paymentDto);
    PaymentDto getPayment (long id);
    List<PaymentDto> getPayments();
    List<PaymentDto> getPaymentsByReference (String ref);
}
