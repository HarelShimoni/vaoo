package com.finastra.vaoo.service.payment;

import com.finastra.vaoo.domain.payment.Status;
import com.finastra.vaoo.repository.PaymentRepository;
import com.finastra.vaoo.web.mappers.payment.PaymentMapper;
import com.finastra.vaoo.web.model.payment.PaymentDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        paymentDto.setStatus(Status.NEW);
        return paymentMapper.toDto(paymentRepository.save(paymentMapper.toEntity(paymentDto)));
    }

    @Override
    public PaymentDto getPayment(long id) {
        return paymentMapper.toDto(paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment could not be found")));
    }

    @Override
    public List<PaymentDto> getPayments() {
        return Optional.of(paymentMapper.toDtos(paymentRepository.findAll())).orElseThrow(() -> new EntityNotFoundException("Payments could not be found"));
    }

    @Override
    public List<PaymentDto> getPaymentsByReference(String ref) {
        return Optional.of(
                paymentMapper.toDtos(paymentRepository.findByReference(ref)))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Payment with reference %s could not be found", ref)));
    }
}
