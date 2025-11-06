package com.example.spring_filter.specification;

import com.example.spring_filter.dto.request.SearchBookRequest;
import com.example.spring_filter.entity.Book;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookSpecification {
    public static Specification<Book> getSpecification(SearchBookRequest searchBookRequest) {
        return (root, query, criteriaBuilder) -> {
            // You can add predicates based on the fields in SearchBookRequest
            List<Predicate> predicates = new ArrayList<>();
            if (searchBookRequest.getTitle() != null) {
                Predicate titlePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + searchBookRequest.getTitle().toLowerCase() + "%");
                predicates.add(titlePredicate);
            }
            if (searchBookRequest.getAuthor() != null) {
                Predicate authorPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + searchBookRequest.getAuthor().toLowerCase() + "%");
                predicates.add(authorPredicate);
            }
            if (searchBookRequest.getCategory() != null){
                Predicate categoryPredicate = criteriaBuilder.equal(root.get("category"), searchBookRequest.getCategory());
                predicates.add(categoryPredicate);
            }
            if (searchBookRequest.getLanguage() != null){
                Predicate languagePredicate = criteriaBuilder.equal(root.get("language"), searchBookRequest.getLanguage());
                predicates.add(languagePredicate);
            }


            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
