package com.finastra.vaoo.client.ffdc.auth.client;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import static com.finastra.vaoo.client.ffdc.config.FFDCConstants.AUTH_HEADER;

public class TokenRefreshService implements Authenticator {

    @Override
    public Request authenticate(Route route, Response response) {
        if (response.code() == 401) {
            return response
                    .request()
                    .newBuilder()
                    .header(AUTH_HEADER, new AuthService().refresh().getToken())
                    .build();
        } else {
            return null;
        }
    }
}
