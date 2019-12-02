package com.finastra.vaoo.client.ffdc.payment;

import com.finastra.vaoo.client.ffdc.payment.model.Payment;
import com.finastra.vaoo.client.ffdc.payment.model.PaymentReport;
import com.finastra.vaoo.client.ffdc.payment.model.PaymentResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FFDCClient {
    @POST("initiate")
    Call<PaymentResponse> initiatePayment(@Body Payment payment);

    @GET("{pid}/status")
    Call<PaymentReport> getStatus(@Path("pid") String pid);

}

