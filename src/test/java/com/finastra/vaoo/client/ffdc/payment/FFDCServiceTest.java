package com.finastra.vaoo.client.ffdc.payment;

import com.finastra.vaoo.client.ffdc.payment.model.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class FFDCServiceTest {

    @Autowired
    FFDCService service;

    private static Payment payment = Payment.builder()
            .initiatingParty("LOCALOFFICEUS1")
            .paymentInformationId("MMSTADVdftrteg52788075")
            .requestedExecutionDate(new Date())
            .instructedAmount(10, "USD")
            .paymentIdentification("112fffsdf213")
            .debtor("NPP DR test2 ACC", "020010001", "745521145")
            .creditor("NPP CR test ACC", "131000000", "1111111111")
            .remittanceInformationUnstructured("RmtInf1234")
            .build();

    @Test
    @Disabled
    void clientTest() throws InterruptedException, ExecutionException, TimeoutException {
        service.initiatePayment(payment)
                .thenCompose(r -> service.getStatus(r.getId()))
                .thenAccept(st -> Assertions.assertNotNull(st))
                .join();
    }

    @Test
    @Disabled
    void whenCompleteTest() throws InterruptedException {
        String pid = service.initiatePayment(payment).join().getId();
        LoggerFactory.getLogger(FFDCService.class).info(() -> "PID=" + pid);
        service.whenComplete(pid, (Assertions::assertNotNull));
        TimeUnit.SECONDS.sleep(20);
    }


}
