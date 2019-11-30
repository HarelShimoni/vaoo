package com.finastra.vaoo.client.ffdc.auth.client;

import com.finastra.vaoo.client.ffdc.auth.exceptions.FFDCAuthentificationException;
import com.finastra.vaoo.client.ffdc.auth.model.SecretStorage;
import com.finastra.vaoo.client.ffdc.auth.model.Session;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class AuthService {
    private Interceptor interceptor = chain -> {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("Authorization", Credentials.basic("8ff77956-6469-49b5-a7fa-79624fe8bef4", "ac157903-c0a3-4527-a18a-828c63eb58b0"))
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    };

    private OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

    private FFDCOauth oauth = new Retrofit.Builder()
            .baseUrl("https://api.lobdev.fusionfabric.cloud/")
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(FFDCOauth.class);

    Session login() {
        return getToken(oauth
                .getAccessToken("client_credentials"));
    }

    Session refresh() {
        return getToken(oauth
                .getAccessToken("client_credentials", SecretStorage.getInstance().getSession().getRefreshToken())
        );
    }

    private Session getToken(Call<Session> call){
        Session session = null;
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