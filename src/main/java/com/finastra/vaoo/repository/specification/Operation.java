package com.finastra.vaoo.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum Operation{
    LIKE {
        @Override
        <T> Predicate execute(Root<T> root, CriteriaBuilder criteriaBuilder, String key, Object value) {
            try {
                return criteriaBuilder.like(root.<String>get(key), "%"+value.toString()+"%");
            } catch (NullPointerException e){
                return criteriaBuilder.and();
            }
        }
    },
    EQUALS {
        @Override
        <T> Predicate  execute(Root<T> root, CriteriaBuilder criteriaBuilder, String key, Object value) {
            try {
                return criteriaBuilder.equal(root.<String>get(key), value.toString());
            } catch (NullPointerException e){
                return criteriaBuilder.and();
            }
        }
    };

    abstract <T> Predicate execute(Root<T> root, CriteriaBuilder criteriaBuilder, String key, Object value);

}
