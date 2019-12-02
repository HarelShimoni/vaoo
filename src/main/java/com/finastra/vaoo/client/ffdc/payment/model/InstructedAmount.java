
package com.finastra.vaoo.client.ffdc.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstructedAmount {

    @JsonProperty("amount")
    public Integer amount;
    @JsonProperty("currency")
    public String currency;

}
