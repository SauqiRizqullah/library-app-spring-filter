package com.example.spring_filter.entity;

import com.example.spring_filter.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.nio.charset.StandardCharsets;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.BOOK)
public class Book {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.example.spring_filter.utils.BookCustomId")
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "year_published", nullable = false)
    private Integer yearPublished;
    @Column(name = "edition", nullable = false)
    private String edition;
    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "language", nullable = false)
    private String language;
    @Column(name = "pages", nullable = false)
    private Integer pages;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name = "total_copies", nullable = false)
    private Integer totalCopies;
    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

}
