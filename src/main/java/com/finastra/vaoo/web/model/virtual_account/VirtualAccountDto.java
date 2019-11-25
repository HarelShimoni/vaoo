package com.finastra.vaoo.web.model.virtual_account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finastra.vaoo.web.model.account.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VirtualAccountDto {
    @JsonProperty
    long id;

    @JsonProperty(required = true)
    String name;

    @JsonProperty
    String context;

    @JsonProperty(value = "limit", defaultValue = "0.0")
    double vlimit;

    @JsonProperty(defaultValue = "0.0")
    double balance;

    @JsonProperty
    boolean autotop;

    @JsonProperty(required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    Date expirationDate;


}
