package com.finastra.vaoo.web.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse extends BaseResponse {
    private boolean authenticated;

    public LoginResponse(String message, boolean authenticated) {
        super(message);
        this.authenticated = authenticated;
    }
}
