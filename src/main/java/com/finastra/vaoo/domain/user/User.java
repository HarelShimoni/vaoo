package com.finastra.vaoo.domain.user;

import com.finastra.vaoo.domain.account.Account;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;
    private String country;
}
