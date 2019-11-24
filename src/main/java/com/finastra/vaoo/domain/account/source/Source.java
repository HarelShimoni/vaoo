package com.finastra.vaoo.domain.account.source;

import com.finastra.vaoo.domain.account.Account;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Source {
    @Id
    @GeneratedValue
    @SuppressWarnings({"unused", "I have to keep it here, cause I cannot use a @MappedSuperclass"})
    long id;

    @SuppressWarnings({"unused", "for JPA usage"})
    @OneToOne(mappedBy = "source")
    Account account;
}
