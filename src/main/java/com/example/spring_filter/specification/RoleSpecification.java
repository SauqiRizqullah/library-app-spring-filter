package com.example.spring_filter.specification;

import com.example.spring_filter.dto.request.SearchRoleRequest;
import com.example.spring_filter.entity.Role;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RoleSpecification {
    public static Specification<Role> getSpecification (SearchRoleRequest searchRoleRequest) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (searchRoleRequest.getRoleName() != null) {
                Predicate rolePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("roleName")), "%" + searchRoleRequest.getRoleName().toLowerCase() + "%");
                predicates.add(rolePredicate);
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
