package com.finastra.vaoo.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    SourceDto source;

    @JsonProperty
    String status;

    public AccountDto(SourceDto source, String status) {
        this.source = source;
        this.status = status;
    }
}
