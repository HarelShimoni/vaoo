package com.finastra.vaoo.client.ffdc.payment;

import com.finastra.vaoo.client.ffdc.auth.client.AuthInterceptor;
import com.finastra.vaoo.client.ffdc.auth.client.TokenRefreshService;
import com.finastra.vaoo.client.ffdc.payment.model.*;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class FFDCService {
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
            .authenticator(new TokenRefreshService())
            .build();

    private FFDCClient ffdcClient = new Retrofit.Builder()
            .baseUrl("https://api.lobdev.fusionfabric.cloud/payment/payment-initiation/realtime-payments/v1/us-real-time-payment/tch-rtps/")
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(FFDCClient.class);
    private static Payment payment = Payment.builder()
            .initiatingParty("LOCALOFFICEUS1")
            .paymentInformationId("MMSTADV52788075")
            .requestedExecutionDate("2019-12-01")
            .instructedAmount(new InstructedAmount(10, "USD"))
            .paymentIdentification(new PaymentIdentification("112fffsdf213"))
            .debtor(new Debtor("NPP DR test2 ACC"))
            .debtorAgent(new DebtorAgent("020010001"))
            .debtorAccountId(new DebtorAccountId("745521145"))
            .creditor(new Creditor("NPP CR test ACC"))
            .creditorAgent(new CreditorAgent("131000000"))
            .creditorAccountId(new CreditorAccountId("1111111111"))
            .remittanceInformationUnstructured("RmtInf1234")
            .build();

    public PaymentResponse initiatePayment(Payment payment) throws IOException {
        Response<PaymentResponse> r = ffdcClient.initiatePayment(payment)
                .execute();
        PaymentResponse pr = r.body();
        pr.setId(r.headers().get("Location").split("/")[3]);
        return pr;
    }

    public String getStatus(String pid) throws IOException {
        return ffdcClient.getStatus(pid).execute().body().getPaymentResponse().getTransactionStatus();
    }



    public static void main(String[] args) throws IOException {
        FFDCService service = new FFDCService();
        PaymentResponse r = service.initiatePayment(payment);
        System.out.println(service.getStatus(r.getId()));
    }
}
