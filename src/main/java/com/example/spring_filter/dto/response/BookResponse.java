package com.example.spring_filter.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BookResponse {
    private String bookId;
    private String title;
    private String author;
    private Integer yearPublished;
    private String edition;
    private String category;
    private String language;
    private Integer pages;
    private String location;
    private Integer totalCopies;
    private Integer availableCopies;
}
