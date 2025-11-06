package com.example.spring_filter.service;

import com.example.spring_filter.dto.request.BookRequest;
import com.example.spring_filter.dto.request.SearchBookRequest;
import com.example.spring_filter.dto.response.BookResponse;
import com.example.spring_filter.entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    BookResponse createBook(BookRequest bookRequest);
    Book getById(String bookId);
    Page<Book> getAllBooks(SearchBookRequest searchBookRequest);
    String deleteBookById(String bookId);
    String updateBookById(String bookId, BookRequest bookRequest);
    long count();
}
