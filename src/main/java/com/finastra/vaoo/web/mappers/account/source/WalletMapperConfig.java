package com.finastra.vaoo.web.mappers.account.source;

import com.finastra.vaoo.domain.account.source.WalletSource;
import com.finastra.vaoo.web.model.account.source.WalletSourceDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MappingTarget;

public interface WalletMapperConfig extends SourceMapperConfig {
    @InheritConfiguration(name = "toEntity")
    void toEntity(WalletSourceDto mean, @MappingTarget WalletSource bsa);
}
