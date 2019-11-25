package com.finastra.vaoo.domain.account.source;

import com.finastra.vaoo.util.DateUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(\\d\\s*){16}")
    String ccNumber;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    CCProvider provider;

    @NotNull
    @Future
    Date expirationDate;

    public CCSource(long id, String ccNumber, CCProvider provider, Date expirationDate) {
        this.ccNumber = ccNumber.replaceAll("\\s+", "");
        this.provider = provider;
        this.expirationDate = new DateUtil().initExpirationDate(expirationDate);
    }
}
