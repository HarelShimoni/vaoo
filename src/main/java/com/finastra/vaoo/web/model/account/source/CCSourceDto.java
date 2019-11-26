package com.finastra.vaoo.web.model.account.source;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CCSourceDto extends SourceDto {
    @JsonProperty
    long id;

    @JsonProperty(required = true)
    String ccNumber;

    @JsonProperty(required = true)
    String provider;

    @JsonProperty(required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yy")
    Date expirationDate;
}
