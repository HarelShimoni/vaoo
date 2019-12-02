package com.finastra.vaoo.domain.payment;

import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
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
import java.util.Objects;

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
    @OneToOne
    private VirtualAccount debitAccount;

    @NotNull
    @OneToOne
    private VirtualAccount creditAccount;

    private Date releaseAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
