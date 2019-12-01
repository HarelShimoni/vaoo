package com.finastra.vaoo.client.ffdc.auth.client;

import com.finastra.vaoo.client.ffdc.auth.model.Session;
import retrofit2.Call;
import retrofit2.http.*;

import static com.finastra.vaoo.client.ffdc.config.FFDCConstants.*;

public interface FFDCOauth {

    @FormUrlEncoded
    @Headers({
            ACCEPT_HEADER,
            })
    @POST(FFDC_TOKEN_URL)
    Call<Session> getAccessToken(@Field(GRANT_TYPE_FIELD) String grantType);

    @FormUrlEncoded
    @Headers({
            ACCEPT_HEADER,
    })
    @POST(FFDC_TOKEN_URL)
    Call<Session> getAccessToken(@Field(GRANT_TYPE_FIELD) String grantType,
                                 @Field("refresh_token") String token
    );
}
