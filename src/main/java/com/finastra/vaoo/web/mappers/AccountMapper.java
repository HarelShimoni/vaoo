package com.finastra.vaoo.web.mappers;

import com.finastra.vaoo.domain.account.Account;
import com.finastra.vaoo.domain.account.Status;
import com.finastra.vaoo.web.model.AccountDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {
    Account toEntity (AccountDto accountDto);
    AccountDto toDto (Account account);

    default String fromEnum(Status status) {
        return status == null ? null : status.toString();
    }

    default Status toEnum(String string) {
        return string == null ? null : Status.valueOf(string.toUpperCase());
    }
}
