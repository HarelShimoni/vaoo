package com.finastra.vaoo.client.ffdc.payment;

import com.finastra.vaoo.client.ffdc.payment.model.Payment;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

//@SpringBootTest
class FFDCServiceTest {

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
    void clientTest() throws InterruptedException, ExecutionException, TimeoutException {
        FFDCService service = new FFDCService();
        CompletableFuture<String> pr = service.initiatePayment(payment).thenCompose(r -> service.getStatus(r.getId()));
        System.out.println(pr.join());
    }
}
