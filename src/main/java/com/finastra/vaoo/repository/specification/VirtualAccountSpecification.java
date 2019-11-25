package com.finastra.vaoo.repository.specification;

import com.finastra.vaoo.domain.virtual_account.VirtualAccount;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class VirtualAccountSpecification implements Specification<VirtualAccount> {
private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<VirtualAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteria.getOperation().execute(root, criteriaBuilder, criteria.getKey(), criteria.getValue());
    }
}
