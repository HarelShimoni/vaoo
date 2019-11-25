package com.finastra.vaoo.domain.virtual_account;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

import static com.finastra.vaoo.util.DateUtil.initExpirationDate;

@Entity
@Builder
@NoArgsConstructor
@Data
public class VirtualAccount {
    @Id
    @GeneratedValue
    long id;

    @NotNull
    String name;

    @Enumerated(EnumType.ORDINAL)
    Context context;

    @PositiveOrZero
    double vlimit;

    @PositiveOrZero
    double balance;

    boolean autotop;

    @NotNull
    Date expirationDate;

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = initExpirationDate(expirationDate);
    }

    public VirtualAccount(long id, String name, Context context, double vlimit, double balance, boolean autotop, Date expirationDate) {
        this.id = id;
        this.name = name;
        this.context = context;
        this.vlimit = vlimit;
        this.balance = balance;
        this.autotop = autotop;
        setExpirationDate(expirationDate);
    }
}
