package com.finastra.vaoo.web.model.account.source;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BankSourceDto.class, name = "bank"),
        @JsonSubTypes.Type(value = WalletSourceDto.class, name = "wallet")
})
public abstract class SourceDto {
}
