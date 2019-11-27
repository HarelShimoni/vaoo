package com.finastra.vaoo.client.ffdc.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Session {

    @JsonProperty("access_token")
    String token;

    @JsonProperty("expires_in")
    int expires;

    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("refresh_expires_in")
    int refreshExpires;

    @JsonProperty
    String scope;

    @JsonProperty("token_type")
    String type;

    @JsonProperty("id_token")
    String id;
}
