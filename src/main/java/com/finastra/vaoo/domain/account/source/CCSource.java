package com.finastra.vaoo.domain.account.source;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Calendar;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Builder
@Data
public class CCSource extends Source {
    @Id
    @GeneratedValue
    long id;

    @NotNull
    @Pattern(regexp = "^\\d{16}")
    String ccNumber;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    CCProvider provider;

    @NotNull
    @Future
    Date expirationDate;

    public void setExpirationDate(Date expirationDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(expirationDate);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        this.expirationDate = cal.getTime();
    }

    public CCSource(long id, String ccNumber, CCProvider provider, Date expirationDate) {
        this.ccNumber = ccNumber;
        this.provider = provider;
        setExpirationDate(expirationDate);
    }
}
