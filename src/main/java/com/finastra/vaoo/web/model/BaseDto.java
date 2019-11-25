package com.finastra.vaoo.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    @NotNull
    private String token="";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
