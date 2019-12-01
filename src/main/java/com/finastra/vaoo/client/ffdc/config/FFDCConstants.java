package com.finastra.vaoo.client.ffdc.config;

public class FFDCConstants {
    public static final String AUTH_HEADER="Authorization";
    public static final String FFDC_BASE_URL = "https://api.lobdev.fusionfabric.cloud/";
    public static final String FFDC_PAYMENT_BASE_URL = FFDC_BASE_URL + "payment/payment-initiation/realtime-payments/v1/us-real-time-payment/tch-rtps/";
    public static final String FFDC_TOKEN_URL = "login/v1/sandbox/oidc/token";
    public static final String ACCEPT_HEADER = "Accept: application/json";
    public static final String  GRANT_TYPE_FIELD = "grant_type";
}
