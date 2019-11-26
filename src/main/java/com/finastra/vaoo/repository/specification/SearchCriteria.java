package com.finastra.vaoo.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private Operation operation;
    private Object value;
}
