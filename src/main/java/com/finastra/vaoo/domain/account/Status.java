package com.finastra.vaoo.domain.account;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    NEW, VALIDATED, NSF;
}
