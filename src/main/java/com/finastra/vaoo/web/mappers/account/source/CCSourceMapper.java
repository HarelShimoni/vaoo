package com.finastra.vaoo.web.mappers.account.source;

import com.finastra.vaoo.domain.account.source.CCSource;
import com.finastra.vaoo.domain.account.source.WalletSource;
import com.finastra.vaoo.web.mappers.account.source.config.WalletMapperConfig;
import com.finastra.vaoo.web.model.account.source.CCSourceDto;
import com.finastra.vaoo.web.model.account.source.WalletSourceDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = WalletMapperConfig.class)
public abstract class CCSourceMapper {

    @InheritConfiguration(name = "map")
    public abstract CCSource toEntity(CCSourceDto ccSourceDto);

    @InheritConfiguration(name = "unmap")
    public abstract CCSourceDto toDto(CCSource ccSource);
}
