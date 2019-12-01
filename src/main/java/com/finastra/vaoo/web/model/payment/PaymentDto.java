package com.finastra.vaoo.web.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finastra.vaoo.domain.payment.Status;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Component
@Data
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private long id;

    @NotBlank
    @JsonProperty
    private String reference;

    @PositiveOrZero
    @JsonProperty
    private BigDecimal amount;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}")
    @JsonProperty
    private String currency;

    @NotNull
    @JsonProperty
    private long debitAccount;

    @NotNull
    @JsonProperty
    private long creditAccount;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @JsonProperty
    private Status status;

    private Date releaseAt;
}
