package com.finastra.vaoo.repository;

import com.finastra.vaoo.domain.payment.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentRepository extends CrudRepository<Payment,Long> {

    @Override
    List<Payment> findAll();

    List<Payment> findByReference(String reference);
}


