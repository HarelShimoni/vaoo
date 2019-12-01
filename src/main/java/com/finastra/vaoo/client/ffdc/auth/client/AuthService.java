package com.finastra.vaoo.client.ffdc.auth.client;

import com.finastra.vaoo.client.ffdc.auth.exceptions.FFDCAuthentificationException;
import com.finastra.vaoo.client.ffdc.auth.model.SecretStorage;
import com.finastra.vaoo.client.ffdc.auth.model.Session;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static com.finastra.vaoo.client.ffdc.config.FFDCConstants.*;

class AuthService {
    private static final String GRANT_TYPE = "client_credentials";
    private Interceptor interceptor = chain -> {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header(AUTH_HEADER, CREDENTIALS)
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    };

    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

    private FFDCOauth oauth = new Retrofit.Builder()
            .baseUrl(FFDC_BASE_URL)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(FFDCOauth.class);

    Session login() {
        return getToken(oauth
                .getAccessToken(GRANT_TYPE));
    }

    Session refresh() {
        return getToken(oauth
                .getAccessToken(GRANT_TYPE, SecretStorage.getInstance().getSession().getRefreshToken())
        );
    }

    private Session getToken(Call<Session> call){
        Session session;
        try {
            session = call
                    .execute()
                    .body();
        } catch (IOException e) {
            throw new FFDCAuthentificationException("Failed to get access token");
        }
        SecretStorage
                .getInstance()
                .setSession(
                        session
                );
        return session;
    }

}