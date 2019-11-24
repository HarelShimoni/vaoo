package com.finastra.vaoo.domain.user;

import com.finastra.vaoo.domain.account.Account;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    private UUID id;

    @NotNull
    @OneToMany (cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phone;

    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String city;

    @NotNull
    private String country;
}
