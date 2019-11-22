package com.finastra.vaoo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    String status;

    public Account(String source, String status) {
        this.source = source;
        this.status = status;
    }
}
