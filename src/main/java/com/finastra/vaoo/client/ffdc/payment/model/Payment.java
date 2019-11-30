
package com.finastra.vaoo.client.ffdc.payment.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "initiatingParty",
    "paymentInformationId",
    "requestedExecutionDate",
    "instructedAmount",
    "paymentIdentification",
    "debtor",
    "debtorAgent",
    "debtorAccountId",
    "creditor",
    "creditorAgent",
    "creditorAccountId",
    "remittanceInformationUnstructured"
})
public class Payment {

    @JsonProperty("initiatingParty")
    public String initiatingParty;
    @JsonProperty("paymentInformationId")
    public String paymentInformationId;
    @JsonProperty("requestedExecutionDate")
    public String requestedExecutionDate;
    @JsonProperty("instructedAmount")
    public InstructedAmount instructedAmount;
    @JsonProperty("paymentIdentification")
    public PaymentIdentification paymentIdentification;
    @JsonProperty("debtor")
    public Debtor debtor;
    @JsonProperty("debtorAgent")
    public DebtorAgent debtorAgent;
    @JsonProperty("debtorAccountId")
    public DebtorAccountId debtorAccountId;
    @JsonProperty("creditor")
    public Creditor creditor;
    @JsonProperty("creditorAgent")
    public CreditorAgent creditorAgent;
    @JsonProperty("creditorAccountId")
    public CreditorAccountId creditorAccountId;
    @JsonProperty("remittanceInformationUnstructured")
    public String remittanceInformationUnstructured;

}
