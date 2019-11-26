package com.finastra.vaoo.web.mappers;

import com.finastra.vaoo.domain.virtual_account.Context;
import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import com.finastra.vaoo.web.mappers.account.AccountMapper;
import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
@Component
public interface VirtualAccountMapper {
    VirtualAccount toEntity(VirtualAccountDto virtualAccountDto);

    VirtualAccountDto toDto(VirtualAccount virtualAccount);

    default String fromEnum(Context status) {
        return status == null ? null : status.toString();
    }

    default Context toEnum(String string) {
        return string == null ? null : Context.valueOf(string.toUpperCase());
    }

}
