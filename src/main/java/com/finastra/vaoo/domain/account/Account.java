package com.finastra.vaoo.domain.account;

import com.finastra.vaoo.domain.account.source.Source;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

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
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Source> sources = new LinkedList<>();

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    Status status;

    @SuppressWarnings({"unused", "for serializing"})
    public Account(List<Source> sources, Status status) {
        this.sources = sources;
        this.status = status;
    }
}
