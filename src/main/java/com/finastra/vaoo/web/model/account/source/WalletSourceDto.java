package com.finastra.vaoo.web.model.account.source;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WalletSourceDto extends SourceDto {
    @JsonProperty
    long id;

    @JsonProperty
    String walletNumber;

    @JsonProperty
    String walletProvider;
}
