package com.finastra.vaoo.web.model.user;

import com.finastra.vaoo.web.model.account.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotNull
    private UUID id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    List<AccountDto> accounts;

    private String password;

    @NotNull
    private String phone;
    @Email
    private String email;
    @NotNull
    private String city;
    @NotNull
    private String country;

}
