package com.finastra.vaoo.client.ffdc.auth.client;

import com.finastra.vaoo.client.ffdc.auth.model.Session;
import retrofit2.Call;
import retrofit2.http.*;

public interface FFDCOauth {
    @FormUrlEncoded
    @Headers({
            "Accept: application/json",
            })
    @POST("login/v1/sandbox/oidc/token")
    Call<Session> getAccessToken(@Field("grant_type") String grantType);

    @FormUrlEncoded
    @Headers({
            "Accept: application/json",
    })
    @POST("login/v1/sandbox/oidc/token")
    Call<Session> getAccessToken(@Field("grant_type") String grantType,
                                 @Field("refresh_token") String token
    );
}
