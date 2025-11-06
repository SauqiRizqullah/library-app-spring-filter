package com.example.spring_filter.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchBookRequest {
    private Integer page;
    private Integer size;

    private String sortBy;
    private String direction;

    private String title;
    private String author;
    private String category;
    private String language;
}
