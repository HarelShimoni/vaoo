package com.finastra.vaoo.web.mappers.account.source;

import com.finastra.vaoo.domain.account.source.Source;
import com.finastra.vaoo.web.model.account.source.SourceDto;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface SourceMapperConfig {
    /*<T extends Source>*/ void map(SourceDto mean, @MappingTarget Source source);
    /*<T extends SourceDto>*/ void unmap(Source mean, @MappingTarget SourceDto sourceDto);

}
