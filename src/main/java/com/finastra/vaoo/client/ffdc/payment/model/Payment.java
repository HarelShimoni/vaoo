
package com.finastra.vaoo.client.ffdc.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static class Builder {
        private String initiatingParty;
        private String paymentInformationId;
        private String requestedExecutionDate;
        private InstructedAmount instructedAmount;
        private PaymentIdentification paymentIdentification;
        private Debtor debtor;
        private DebtorAgent debtorAgent;
        private DebtorAccountId debtorAccountId;
        private Creditor creditor;
        private CreditorAgent creditorAgent;
        private CreditorAccountId creditorAccountId;
        private String remittanceInformationUnstructured;

        Builder() {
        }

        public Builder initiatingParty(final String initiatingParty) {
            this.initiatingParty = initiatingParty;
            return this;
        }

        public Builder paymentInformationId(final String paymentInformationId) {
            this.paymentInformationId = paymentInformationId;
            return this;
        }

        public Builder requestedExecutionDate(final String requestedExecutionDate) {
            this.requestedExecutionDate = requestedExecutionDate;
            return this;
        }

        public Builder instructedAmount(final InstructedAmount instructedAmount) {
            this.instructedAmount = instructedAmount;
            return this;
        }

        public Builder paymentIdentification(final PaymentIdentification paymentIdentification) {
            this.paymentIdentification = paymentIdentification;
            return this;
        }

        public Builder debtor(final Debtor debtor, final DebtorAgent debtorAgent, final DebtorAccountId debtorAccountId) {
            this.debtor = debtor;
            this.debtorAgent = debtorAgent;
            this.debtorAccountId = debtorAccountId;
            return this;
        }

        public Builder creditor(final Creditor creditor, final CreditorAgent creditorAgent, final CreditorAccountId creditorAccountId) {
            this.creditor = creditor;
            this.creditorAgent = creditorAgent;
            this.creditorAccountId = creditorAccountId;
            return this;
        }

        public Builder remittanceInformationUnstructured(final String remittanceInformationUnstructured) {
            this.remittanceInformationUnstructured = remittanceInformationUnstructured;
            return this;
        }

        public Payment build() {
            return new Payment(this.initiatingParty, this.paymentInformationId, this.requestedExecutionDate, this.instructedAmount, this.paymentIdentification, this.debtor, this.debtorAgent, this.debtorAccountId, this.creditor, this.creditorAgent, this.creditorAccountId, this.remittanceInformationUnstructured);
        }

        public String toString() {
            return "Payment.Builder(initiatingParty=" + this.initiatingParty + ", paymentInformationId=" + this.paymentInformationId + ", requestedExecutionDate=" + this.requestedExecutionDate + ", instructedAmount=" + this.instructedAmount + ", paymentIdentification=" + this.paymentIdentification + ", debtor=" + this.debtor + ", debtorAgent=" + this.debtorAgent + ", debtorAccountId=" + this.debtorAccountId + ", creditor=" + this.creditor + ", creditorAgent=" + this.creditorAgent + ", creditorAccountId=" + this.creditorAccountId + ", remittanceInformationUnstructured=" + this.remittanceInformationUnstructured + ")";
        }
    }

}
