package com.finastra.vaoo.domain.account;

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
    String source;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    Status status;

    public Account(String source, Status status) {
        this.source = source;
        this.status = status;
    }
}
