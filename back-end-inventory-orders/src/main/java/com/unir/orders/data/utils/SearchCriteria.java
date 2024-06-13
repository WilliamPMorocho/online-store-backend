package com.unir.orders.data.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.LinkedList;
import java.util.List;

public class SearchCriteria<T> implements Specification<T> {

    private final List<SearchStatement> list = new LinkedList<>();

    public void add(SearchStatement criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        List<Predicate> predicates = new LinkedList<>();
        for (SearchStatement criteria : list) {
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN:
                    predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case GREATER_THAN_EQUAL:
                    predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN_EQUAL:
                    predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case NOT_EQUAL:
                    predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case EQUAL:
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case MATCH:
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case MATCH_END:
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%"));
                    break;
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}