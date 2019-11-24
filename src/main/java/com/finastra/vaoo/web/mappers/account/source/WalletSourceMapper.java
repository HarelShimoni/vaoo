package com.finastra.vaoo.web.mappers.account.source;

import com.finastra.vaoo.domain.account.source.WalletSource;
import com.finastra.vaoo.web.mappers.account.source.config.WalletMapperConfig;
import com.finastra.vaoo.web.model.account.source.WalletSourceDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = WalletMapperConfig.class)
public abstract class WalletSourceMapper {

    @InheritConfiguration(name = "map")
    public abstract WalletSource toEntity(WalletSourceDto walletSourceDto);

    @InheritConfiguration(name = "unmap")
    public abstract WalletSourceDto toDto(WalletSource walletSource);
}
