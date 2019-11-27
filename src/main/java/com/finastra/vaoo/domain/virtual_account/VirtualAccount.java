package com.finastra.vaoo.domain.virtual_account;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VirtualAccount {
    @Id
    @GeneratedValue
//    @EqualsAndHashCode.Include
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
        this.expirationDate = new DateUtil().initExpirationDate(expirationDate);
    }

    @ManyToOne
    Account account;

    public VirtualAccount(long id, String name, Context context, double vlimit, double balance, boolean autotop, Date expirationDate, Account account) {
        this.id = id;
        this.name = name;
        this.context = context;
        this.vlimit = vlimit;
        this.balance = balance;
        this.autotop = autotop;
        setExpirationDate(expirationDate);
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirtualAccount that = (VirtualAccount) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
