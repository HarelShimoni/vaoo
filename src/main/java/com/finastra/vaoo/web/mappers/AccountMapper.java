package com.finastra.vaoo.web.mappers;

import com.finastra.vaoo.domain.Account;
import com.finastra.vaoo.domain.User;
import com.finastra.vaoo.web.model.AccountDto;
import com.finastra.vaoo.web.model.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {
    Account toEntity (AccountDto accountDto);
    AccountDto toDto (Account account);
}
