package com.finastra.vaoo.web.model.payment;

import com.finastra.vaoo.domain.payment.Status;
import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;
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
@Builder
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private long id;

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

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private Date releaseAt;


}
