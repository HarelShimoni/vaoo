package com.finastra.vaoo.client.ffdc.payment;

import com.finastra.vaoo.client.ffdc.auth.client.AuthInterceptor;
import com.finastra.vaoo.client.ffdc.auth.client.TokenRefreshService;
import com.finastra.vaoo.client.ffdc.payment.exceptions.FFDCPaymentException;
import com.finastra.vaoo.client.ffdc.payment.model.Payment;
import com.finastra.vaoo.client.ffdc.payment.model.PaymentReport;
import com.finastra.vaoo.client.ffdc.payment.model.PaymentResponse;
import okhttp3.OkHttpClient;
import okhttp3.internal.annotations.EverythingIsNonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Consumer;

import static com.finastra.vaoo.client.ffdc.config.FFDCConstants.FFDC_PAYMENT_BASE_URL;

public class FFDCService {
    private static final String COMPLETED = "COMPLETED";
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
            .authenticator(new TokenRefreshService())
            .build();

    private FFDCClient ffdcClient = new Retrofit.Builder()
            .baseUrl(FFDC_PAYMENT_BASE_URL)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(FFDCClient.class);

    public CompletableFuture<PaymentResponse> initiatePayment(Payment payment) {
        final CompletableFuture<PaymentResponse> future = new CompletableFuture<>();
        ffdcClient.initiatePayment(payment)
                .enqueue(new Callback<PaymentResponse>() {
                    @Override
                    @EverythingIsNonNull
                    public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                        PaymentResponse pr = response.body();
                        assert pr != null;
                        if (Optional.ofNullable(pr.getStatusReasonAdditionalInformation()).isPresent()) {
                            future.completeExceptionally(new FFDCPaymentException(pr));
                        } else {
                            pr.setId(
                                    Optional
                                            .ofNullable(response.headers().get("Location"))
                                            .orElseThrow(() -> new FFDCPaymentException(pr))
                                            .split("/")[3]);
                            future.complete(pr);
                        }
                    }

                    @Override
                    @EverythingIsNonNull
                    public void onFailure(Call<PaymentResponse> call, Throwable throwable) {
                        future.complete(new PaymentResponse(
                                payment.getPaymentInformationId(),
                                COMPLETED,
                                null,
                                null,
                                null));
                    }
                });

        return future;
    }

    public CompletableFuture<String> getStatus(String pid) {
        final CompletableFuture<String> future = new CompletableFuture<>();
        if (pid == null) {
            future.complete(COMPLETED);
        } else {
            ffdcClient.getStatus(pid).enqueue(new Callback<PaymentReport>() {
                @Override
                public void onResponse(Call<PaymentReport> call, Response<PaymentReport> response) {
                    future.complete(
                            Optional
                                    .ofNullable(response.body())
                                    .orElseThrow(
                                            () -> new FFDCPaymentException(String.format("Failed to get status of payment (pid = %s). Response body is null", pid))
                                    ).getPaymentResponse().getTransactionStatus());
                }

                @Override
                public void onFailure(Call<PaymentReport> call, Throwable throwable) {
                    future.complete(COMPLETED);
                }
            });
        }
        return future;
    }

    public void isComplete(String pid, Consumer<String> pidConsumer) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        final ScheduledFuture<?> sched = executor.scheduleAtFixedRate(() ->
                    getStatus(pid).thenAcceptAsync(st -> {
                        if (st.equals(COMPLETED)){
                            pidConsumer.accept(pid);
                            future.complete(true);
                        }
                    })
                , 0, 10, TimeUnit.SECONDS);
        future.whenComplete((result, throwable) -> sched.cancel(true));

    }

}
