package com.finastra.vaoo.web.mappers.payment;

import com.finastra.vaoo.domain.payment.Payment;
import com.finastra.vaoo.web.model.payment.PaymentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring")
public interface PaymentMapper {

    PaymentDto toDto (Payment payment);
    Payment toEntity (PaymentDto paymentDto);
    List<PaymentDto> toDtos (List<Payment> payments);
}
