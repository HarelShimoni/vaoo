package com.finastra.vaoo.web.mappers.account.source.config;

import com.finastra.vaoo.domain.account.source.CCSource;
import com.finastra.vaoo.domain.account.source.WalletSource;
import com.finastra.vaoo.web.model.account.source.CCSourceDto;
import com.finastra.vaoo.web.model.account.source.WalletSourceDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MappingTarget;

public interface CCMapperConfig extends SourceMapperConfig {
    @InheritConfiguration(name = "toEntity")
    void toEntity(CCSourceDto mean, @MappingTarget CCSource ccs);
}
