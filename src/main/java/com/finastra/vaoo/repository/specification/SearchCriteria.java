package com.finastra.vaoo.repository.specification;

import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.BiFunction;

@Getter
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private Operation operation;
    private Object value;
}
