package com.finastra.vaoo.client.ffdc.auth.client;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import java.io.IOException;

public class TokenRefreshService implements Authenticator {

    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (response.code()==401){
            return response
                    .request()
                    .newBuilder()
                    .header("Authorization", new AuthService().refresh().getToken())
                    .build();
        } else {
            return null;
        }
    }
}
