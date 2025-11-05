package com.example.spring_filter.specification;

import com.example.spring_filter.dto.request.SearchAdminRequest;
import com.example.spring_filter.entity.Admin;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class AdminSpecification {
    public static Specification<Admin> getSpecification(SearchAdminRequest searchAdminRequest) {
        return (root, query, criteriaBuilder) -> {
            // You can add predicates based on the fields in SearchAdminRequest
            List<Predicate> predicates = new ArrayList<>();
            if (searchAdminRequest.getAdminName() != null) {
                Predicate adminNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("adminName")), "%" + searchAdminRequest.getAdminName().toLowerCase() + "%");
                predicates.add(adminNamePredicate);
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
