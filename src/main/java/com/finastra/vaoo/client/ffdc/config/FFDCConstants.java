package com.finastra.vaoo.client.ffdc.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.Credentials;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FFDCConstants {
    public static final String AUTH_HEADER="Authorization";
    public static final String FFDC_BASE_URL = "https://api.lobdev.fusionfabric.cloud/";
    public static final String FFDC_PAYMENT_BASE_URL = FFDC_BASE_URL + "payment/payment-initiation/realtime-payments/v1/us-real-time-payment/tch-rtps/";
    public static final String FFDC_TOKEN_URL = "login/v1/sandbox/oidc/token";
    public static final String ACCEPT_HEADER = "Accept: application/json";
    public static final String  GRANT_TYPE_FIELD = "grant_type";
    public static final String CREDENTIALS = Credentials.basic("8ff77956-6469-49b5-a7fa-79624fe8bef4", "ac157903-c0a3-4527-a18a-828c63eb58b0");
}
