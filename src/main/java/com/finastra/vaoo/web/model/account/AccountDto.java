package com.finastra.vaoo.web.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finastra.vaoo.web.model.account.source.SourceDto;
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

    @JsonProperty(required = true)
    SourceDto source;

    @JsonProperty(defaultValue = "NEW")
    String status;

    public AccountDto(SourceDto source, String status) {
        this.source = source;
        this.status = status;
    }
}
