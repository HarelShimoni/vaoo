package com.finastra.vaoo.domain.account;

import com.finastra.vaoo.domain.account.source.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue
    long id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Source source;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    Status status;

    @SuppressWarnings({"unused", "for serializing"})
    public Account(Source source, Status status) {
        this.source = source;
        this.status = status;
    }
}
