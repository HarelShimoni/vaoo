package com.finastra.vaoo.domain.account;

import com.finastra.vaoo.domain.account.source.Source;
import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    List<Source> sources = new LinkedList<>();

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    Status status;

    @OneToMany(cascade = CascadeType.ALL)
    Set<VirtualAccount> virtualAccounts = new HashSet<>();

    public Account(long id, List<Source> sources, Status status) {
        this.id = id;
        this.sources = sources;
        this.status = status;
    }
}
