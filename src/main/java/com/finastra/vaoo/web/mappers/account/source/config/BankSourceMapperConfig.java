package com.finastra.vaoo.web.mappers.account.source.config;

import com.finastra.vaoo.domain.account.source.BankSource;
import com.finastra.vaoo.web.model.account.source.BankSourceDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MappingTarget;

public interface BankSourceMapperConfig extends SourceMapperConfig {
    @InheritConfiguration(name = "toEntity")
    void toEntity(BankSourceDto mean, @MappingTarget BankSource bankSource);
}
