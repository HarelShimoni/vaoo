package com.finastra.vaoo.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @JsonProperty
    String id;

    @JsonProperty
    String source;

    @JsonProperty
    String Status;
}
