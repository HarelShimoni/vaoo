package com.finastra.vaoo.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Source {
    @Id
    @GeneratedValue
    long id;

    @OneToOne(mappedBy = "source")
    Account account;

    @NotNull
    String number;
}
