package com.finastra.vaoo.domain.account.source;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BankSource extends Source {
    @Id
    @GeneratedValue
    long id;

    @NotNull
    @Pattern(regexp = "^\\d+$")
    String accountNumber;

    @NotNull
    @Pattern(regexp = "^[A-Za-z -].*$")
    String bank;

    @NotNull
    String branch;

    String iban;
}
