package com.finastra.vaoo.client.ffdc.payment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {
    @JsonProperty
    String paymentResourceId;
    @JsonProperty
    String transactionStatus;
    @JsonProperty
    String statusReasonCode;
    @JsonProperty
    String statusReasonAdditionalInformation;

    String Id;
}
