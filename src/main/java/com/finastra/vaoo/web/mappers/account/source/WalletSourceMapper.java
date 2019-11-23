package com.finastra.vaoo.web.mappers.account.source;

import com.finastra.vaoo.domain.account.source.WalletSource;
import com.finastra.vaoo.web.model.account.source.WalletSourceDto;
import org.mapstruct.BeforeMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = WalletMapperConfig.class)
public abstract class WalletSourceMapper {
    @BeforeMapping
    void logMapper(){
        System.out.println(this.getClass());
    }

    @InheritConfiguration(name = "map")
    public abstract WalletSource toEntity(WalletSourceDto walletSourceDto);

    @InheritConfiguration(name = "unmap")
    public abstract WalletSourceDto toDto(WalletSource walletSource);
}
