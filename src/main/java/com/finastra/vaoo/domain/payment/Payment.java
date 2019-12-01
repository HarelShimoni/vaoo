package com.finastra.vaoo.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @NotBlank
    private String reference;

    @PositiveOrZero
    private BigDecimal amount;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}")
    private String currency;

    @NotNull
    private long debitAccount;

    @NotNull
    private long creditAccount;

    private Date releaseAt;
}
