package com.finastra.vaoo.web.mappers.account;

import com.finastra.vaoo.domain.account.*;
import com.finastra.vaoo.domain.account.source.BankSource;
import com.finastra.vaoo.domain.account.source.CCSource;
import com.finastra.vaoo.domain.account.source.Source;
import com.finastra.vaoo.domain.account.source.WalletSource;
import com.finastra.vaoo.web.mappers.account.source.BankSourceMapper;
import com.finastra.vaoo.web.mappers.account.source.CCSourceMapper;
import com.finastra.vaoo.web.mappers.account.source.WalletSourceMapper;
import com.finastra.vaoo.web.model.account.AccountDto;
import com.finastra.vaoo.web.model.account.source.BankSourceDto;
import com.finastra.vaoo.web.model.account.source.CCSourceDto;
import com.finastra.vaoo.web.model.account.source.SourceDto;
import com.finastra.vaoo.web.model.account.source.WalletSourceDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {BankSourceMapper.class, WalletSourceMapper.class})
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

    @SuppressWarnings("unchecked")
    default <T extends SourceDto, E extends Source> E toEntity(T sourceDto){
        if (sourceDto instanceof BankSourceDto)   return (E)Mappers.getMapper(BankSourceMapper.class).toEntity((BankSourceDto)sourceDto);
        if (sourceDto instanceof WalletSourceDto) return (E)Mappers.getMapper(WalletSourceMapper.class).toEntity((WalletSourceDto)sourceDto);
        if (sourceDto instanceof CCSourceDto)     return (E)Mappers.getMapper(CCSourceMapper.class).toEntity((CCSourceDto)sourceDto);
        throw new RuntimeException();
    }

    @SuppressWarnings("unchecked")
    default <T extends Source, E extends SourceDto> E toDto(T source) {
        if (source instanceof BankSource)   return (E)Mappers.getMapper(BankSourceMapper.class).toDto((BankSource)source);
        if (source instanceof WalletSource) return (E)Mappers.getMapper(WalletSourceMapper.class).toDto((WalletSource)source);
        if (source instanceof CCSource)     return (E)Mappers.getMapper(CCSourceMapper.class).toDto((CCSource)source);
        throw new RuntimeException();
    }
}
