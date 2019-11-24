package com.finastra.vaoo.web.model.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.finastra.vaoo.web.model.account.source.SourceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountDto {
    @JsonProperty
    long id;

    @JsonProperty(required = true)
    List<SourceDto> sources = new LinkedList<>();

    @JsonProperty(defaultValue = "NEW")
    String status;

    public AccountDto(List<SourceDto> sources, String status) {
        this.sources = sources;
        this.status = status;
    }
}
