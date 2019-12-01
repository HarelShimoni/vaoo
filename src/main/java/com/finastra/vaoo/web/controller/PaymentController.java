package com.finastra.vaoo.web.controller;

import com.finastra.vaoo.service.payment.PaymentService;
import com.finastra.vaoo.web.model.payment.PaymentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping (consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDto), HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable long id) {
        return new ResponseEntity<>(paymentService.getPayment(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getPaymentByReference(@RequestParam String reference) {
        return new ResponseEntity<>(paymentService.getPaymentsByReference(reference),HttpStatus.OK);
    }

}
