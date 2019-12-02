package com.finastra.vaoo.service.payment;

import com.finastra.vaoo.domain.payment.Payment;
import com.finastra.vaoo.domain.payment.Status;
import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import com.finastra.vaoo.repository.PaymentRepository;
import com.finastra.vaoo.repository.VirtualAccountRepository;
import com.finastra.vaoo.web.mappers.payment.PaymentMapper;
import com.finastra.vaoo.web.model.payment.PaymentDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private VirtualAccountRepository virtualAccountRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper, VirtualAccountRepository virtualAccountRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.virtualAccountRepository = virtualAccountRepository;
    }

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        paymentDto.setStatus(Status.NEW);
        Payment payment = paymentMapper.toEntity(paymentDto);
        long debitAccountId = paymentDto.getDebitAccount();
        VirtualAccount debitAccount = virtualAccountRepository.findById(debitAccountId).orElseThrow(() -> new EntityNotFoundException("Debit account not found"));
        payment.setDebitAccount(debitAccount);

        long creditAccountId = paymentDto.getCreditAccount();
        VirtualAccount creditAccount = virtualAccountRepository.findById(creditAccountId).orElseThrow(() -> new EntityNotFoundException("Credit account not found"));
        payment.setCreditAccount(creditAccount);

        return paymentMapper.toDto(paymentRepository.save(payment));
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
