package com.finastra.vaoo.domain.account.source;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CCSource extends Source {
    @Id
    @GeneratedValue
    long id;

    @NotNull
    String ccNumber;

}