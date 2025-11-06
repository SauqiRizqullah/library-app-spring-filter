package com.example.spring_filter.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    @NotBlank(message = "Title must be provided!!!")
    private String title;
    @NotBlank(message = "Author must be provided!!!")
    private String author;
    @NotNull(message = "Year Published must be provided!!!")
    private Integer yearPublished;
    @NotBlank(message = "Edition must be provided!!!")
    private String edition;
    @NotBlank(message = "Category must be provided!!!")
    private String category;
    @NotBlank(message = "Language must be provided!!!")
    private String language;
    @NotNull(message = "Pages must be provided!!!")
    private Integer pages;
    @NotBlank(message = "Location must be provided!!!")
    private String location;
    @NotNull(message = "Total Copies must be provided!!!")
    @Min(value = 0,message = "Total Copies cannot be less than 0!!!")
    private Integer totalCopies;
    @NotNull(message = "Available Copies must be provided!!!")
    @Min(value = 0, message = "Available Copies cannot be less than 0!!!")
    private Integer availableCopies;
}
