package com.finastra.vaoo.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finastra.vaoo.domain.account.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountDto {
    @JsonProperty
    long id;

    @JsonProperty
    String source;

    @JsonProperty
    String status;

    public AccountDto(String source, String status) {
        this.source = source;
        this.status = status;
    }
}
