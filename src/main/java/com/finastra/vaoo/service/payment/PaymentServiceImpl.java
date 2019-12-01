package com.finastra.vaoo.service.payment;

import com.finastra.vaoo.repository.PaymentRepository;
import com.finastra.vaoo.web.mappers.payment.PaymentMapper;
import com.finastra.vaoo.web.model.payment.PaymentDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        return paymentMapper.toDto(paymentRepository.save(paymentMapper.toEntity(paymentDto)));
    }

    @Override
    public PaymentDto getPayment(long id) {
        return paymentMapper.toDto(paymentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Payment could not be found")));
    }

    @Override
    public List<PaymentDto> getPayments() {
        return paymentMapper.toDtos(paymentRepository.findAll());
    }

    @Override
    public List<PaymentDto> getPaymentsByReference(String ref) {
        return paymentMapper.toDtos(paymentRepository.findByReference(ref));
    }
}
