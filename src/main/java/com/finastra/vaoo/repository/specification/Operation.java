package com.finastra.vaoo.repository.specification;

import com.finastra.vaoo.domain.virtual_account.VirtualAccount;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum Operation{
    LIKE {
        @Override
        Predicate execute(Root<VirtualAccount> root, CriteriaBuilder criteriaBuilder, String key, Object value) {
            try {
                return criteriaBuilder.like(root.<String>get(key), "%"+value.toString()+"%");
            } catch (NullPointerException e){
                return criteriaBuilder.and();
            }
        }
    },
    EQUALS {
        @Override
        Predicate execute(Root<VirtualAccount> root, CriteriaBuilder criteriaBuilder, String key, Object value) {
            try {
                return criteriaBuilder.equal(root.<String>get(key), value.toString());
            } catch (NullPointerException e){
                return criteriaBuilder.and();
            }
        }
    };

    abstract Predicate execute(Root<VirtualAccount> root, CriteriaBuilder criteriaBuilder, String key, Object value);

}
