package com.finastra.vaoo.client.ffdc.auth.client;

import com.finastra.vaoo.client.ffdc.auth.model.SecretStorage;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Optional;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request
                .newBuilder()
                .addHeader("Authorization", Optional
                        .ofNullable(SecretStorage.getInstance().getSession().getToken())
                        .orElseGet(() -> new AuthService().login().getToken()));
        return chain.proceed(request);
    }
}
