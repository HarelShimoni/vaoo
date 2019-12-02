package com.finastra.vaoo.client.ffdc.payment;

import com.finastra.vaoo.client.ffdc.auth.client.AuthInterceptor;
import com.finastra.vaoo.client.ffdc.auth.client.TokenRefreshService;
import com.finastra.vaoo.client.ffdc.payment.exceptions.FFDCPaymentException;
import com.finastra.vaoo.client.ffdc.payment.model.Payment;
import com.finastra.vaoo.client.ffdc.payment.model.PaymentReport;
import com.finastra.vaoo.client.ffdc.payment.model.PaymentResponse;
import okhttp3.OkHttpClient;
import okhttp3.internal.annotations.EverythingIsNonNull;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static com.finastra.vaoo.client.ffdc.config.FFDCConstants.FFDC_PAYMENT_BASE_URL;

@Service
public class FFDCService {
    private Logger logger = Logger.getLogger(FFDCService.class.getName());

    private static final String COMPLETED = "COMPLETED";
    private static final String REJECTED = "RJCT";

//region buildClient
    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
            .authenticator(new TokenRefreshService())
            .build();

    private FFDCClient ffdcClient = new Retrofit.Builder()
            .baseUrl(FFDC_PAYMENT_BASE_URL)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(FFDCClient.class);
//endregion buildClient

//region callBacks
    private BiFunction<String, CompletableFuture<String>, Callback<PaymentReport>> statusCallBack = (pid, future) -> new Callback<PaymentReport>() {
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
    };

    private final BiFunction<Payment, CompletableFuture<PaymentResponse>, Callback<PaymentResponse>> initiatePaymentCallBack = (payment, future) ->
            new Callback<PaymentResponse>() {
                @Override
                @EverythingIsNonNull
                public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                    PaymentResponse pr = response.body();
                    assert pr != null;
                    if (Optional.ofNullable(pr.getStatusReasonAdditionalInformation()).isPresent()) {
                        pr.setId(pr.getTransactionStatus());
                        future.complete(pr);
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
            };
//endregion callBacks

//region api
    public CompletableFuture<PaymentResponse> initiatePayment(Payment payment) {
        final CompletableFuture<PaymentResponse> future = new CompletableFuture<>();
        ffdcClient.initiatePayment(payment)
                .enqueue(initiatePaymentCallBack.apply(payment, future));
        return future;
    }

    public CompletableFuture<String> getStatus(String pid) {
        final CompletableFuture<String> future = new CompletableFuture<>();
        switch (pid){
            case "mockId":
                future.complete(COMPLETED);
                break;
            case "RJCT":
                future.complete(REJECTED);
                break;
            default:
                ffdcClient.getStatus(pid).enqueue(statusCallBack.apply(pid, future));
                break;
        }
        return future;
    }

    public void whenComplete(String pid, Consumer<String> pidConsumer) {
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        AtomicReference<String> status= new AtomicReference<>("");
        final ScheduledFuture<?> sched = executor.scheduleAtFixedRate(() ->
                        getStatus(pid).thenAcceptAsync(st -> {
                            logger.info("id: " + pid + " status: " + st);
                            status.set(st);
                            if (st.equals(COMPLETED)) {
                                pidConsumer.accept(pid);
                                future.complete(true);
                            } else if (st.equals(REJECTED)){
                                future.complete(false);
                            }
                        })
                , 0, 10, TimeUnit.SECONDS);
        future.whenCompleteAsync((result, throwable) -> {
            if (status.get().equals(COMPLETED) || status.get().equals(REJECTED)){
                sched.cancel(true);
                executor.shutdownNow();
            }
        });
    }
//endregion api

}
