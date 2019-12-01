package com.finastra.vaoo.client.ffdc.payment;

import com.finastra.vaoo.client.ffdc.auth.client.AuthInterceptor;
import com.finastra.vaoo.client.ffdc.auth.client.TokenRefreshService;
import com.finastra.vaoo.client.ffdc.payment.model.*;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.CompletableFuture;

import static com.finastra.vaoo.client.ffdc.config.FFDCConstants.FFDC_PAYMENT_BASE_URL;

public class FFDCService {
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
            .authenticator(new TokenRefreshService())
            .build();

    private FFDCClient ffdcClient = new Retrofit.Builder()
            .baseUrl(FFDC_PAYMENT_BASE_URL)
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

    public CompletableFuture<PaymentResponse> initiatePayment(Payment payment) {
        CompletableFuture<PaymentResponse> future = new CompletableFuture<>();
        ffdcClient.initiatePayment(payment)
                .enqueue(new Callback<PaymentResponse>() {
                    @Override
                    public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                        PaymentResponse pr = response.body();
                        pr.setId(response.headers().get("Location").split("/")[3]);
                        future.complete(pr);
                    }

                    @Override
                    public void onFailure(Call<PaymentResponse> call, Throwable throwable) {
                        future.completeExceptionally(throwable);
                    }
                });

        return future;
    }

    public CompletableFuture<String> getStatus(String pid) {
        CompletableFuture<String> future = new CompletableFuture<>();
        ffdcClient.getStatus(pid).enqueue(new Callback<PaymentReport>() {
            @Override
            public void onResponse(Call<PaymentReport> call, Response<PaymentReport> response) {
                future.complete(response.body().getPaymentResponse().getTransactionStatus());
            }

            @Override
            public void onFailure(Call<PaymentReport> call, Throwable throwable) {
                future.completeExceptionally(throwable);
            }
        });
        return future;
    }



    public static void main(String[] args) {
        FFDCService service = new FFDCService();
        PaymentResponse r = service.initiatePayment(payment).join();
        System.out.println(service.getStatus(r.getId()).join());
    }
}
