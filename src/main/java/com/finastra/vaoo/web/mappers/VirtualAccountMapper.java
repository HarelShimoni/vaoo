package com.finastra.vaoo.web.mappers;

import com.finastra.vaoo.domain.virtual_account.Context;
import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import com.finastra.vaoo.web.mappers.account.AccountMapper;
import com.finastra.vaoo.web.model.virtual_account.VirtualAccountDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
@Component
public interface VirtualAccountMapper {
    VirtualAccount toEntity(VirtualAccountDto virtualAccountDto);

    VirtualAccountDto toDto(VirtualAccount virtualAccount);

    default VirtualAccountDto toDto(Long vid) {
        Calendar cal = Calendar.getInstance();
        return VirtualAccountDto.builder().id(vid).expirationDate(cal.getTime()).build();
    }

    default Long toId(VirtualAccountDto virtualAccountDto) {
        return virtualAccountDto.getId();
    }

    default String fromEnum(Context status) {
        return status == null ? null : status.toString();
    }

    default Context toEnum(String string) {
        return string == null ? null : Context.valueOf(string.toUpperCase());
    }

}
