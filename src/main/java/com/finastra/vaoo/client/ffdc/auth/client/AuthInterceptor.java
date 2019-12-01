package com.finastra.vaoo.client.ffdc.auth.client;

import com.finastra.vaoo.client.ffdc.auth.model.SecretStorage;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

import static com.finastra.vaoo.client.ffdc.config.FFDCConstants.AUTH_HEADER;

public class AuthInterceptor implements Interceptor {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String CONTENT_HEADER_KEY = "Content-Type";
    private static final String CONTENT_HEADER_JSON = "application/json";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request r1 = request
                .newBuilder()
                .addHeader(AUTH_HEADER, TOKEN_PREFIX + Optional
                        .ofNullable(SecretStorage.getInstance().getSession().getToken())
                        .orElseGet(() -> new AuthService().login().getToken()))
                .addHeader(CONTENT_HEADER_KEY, CONTENT_HEADER_JSON)
                .build();
        return chain.proceed(r1);
    }
}
