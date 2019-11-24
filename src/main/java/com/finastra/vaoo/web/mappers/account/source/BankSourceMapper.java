package com.finastra.vaoo.web.mappers.account.source;

import com.finastra.vaoo.domain.account.source.BankSource;
import com.finastra.vaoo.web.mappers.account.source.config.BankSourceMapperConfig;
import com.finastra.vaoo.web.model.account.source.BankSourceDto;
import org.mapstruct.*;

@Mapper(config = BankSourceMapperConfig.class)
public abstract class BankSourceMapper {

    @InheritConfiguration(name = "map")
    public abstract BankSource toEntity(BankSourceDto bankSourceDto);

    @InheritConfiguration(name = "unmap")
    public abstract BankSourceDto toDto(BankSource bankSource);
}
