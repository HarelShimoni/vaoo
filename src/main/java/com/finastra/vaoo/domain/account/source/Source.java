package com.finastra.vaoo.domain.account.source;

import com.finastra.vaoo.domain.account.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Source {
    @Id
    @GeneratedValue
    long id;

    @OneToOne(mappedBy = "source")
    Account account;
}
