package com.example.spring_filter.service.impl;

import com.example.spring_filter.dto.request.BookRequest;
import com.example.spring_filter.dto.request.SearchBookRequest;
import com.example.spring_filter.dto.response.BookResponse;
import com.example.spring_filter.entity.Book;
import com.example.spring_filter.repository.BookRepository;
import com.example.spring_filter.service.BookService;
import com.example.spring_filter.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .yearPublished(bookRequest.getYearPublished())
                .edition(bookRequest.getEdition())
                .category(bookRequest.getCategory())
                .language(bookRequest.getLanguage())
                .pages(bookRequest.getPages())
                .location(bookRequest.getLocation())
                .totalCopies(bookRequest.getTotalCopies())
                .availableCopies(bookRequest.getAvailableCopies())
                .build();

        bookRepository.saveAndFlush(book);
        return BookResponse.builder()
                .bookId(book.getBookId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .yearPublished(book.getYearPublished())
                .edition(book.getEdition())
                .category(book.getCategory())
                .language(book.getLanguage())
                .pages(book.getPages())
                .location(book.getLocation())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .build();
    }

    @Override
    public Book getById(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @Override
    public Page<Book> getAllBooks(SearchBookRequest searchBookRequest) {
        if(searchBookRequest.getPage() <= 0){
            searchBookRequest.setPage(1);
        }

        String validSortBy;
        if ("title".equalsIgnoreCase(searchBookRequest.getSortBy()) ||
            "author".equalsIgnoreCase(searchBookRequest.getSortBy()) ||
            "category".equalsIgnoreCase(searchBookRequest.getSortBy()) ||
        "language".equalsIgnoreCase(searchBookRequest.getSortBy())) {
            validSortBy = searchBookRequest.getSortBy();
        } else {
            validSortBy = "bookId"; // default sort by id
        }

        Sort sort = Sort.by(Sort.Direction.fromString(searchBookRequest.getDirection()), validSortBy);

        Pageable pageable = PageRequest.of(searchBookRequest.getPage() -1, searchBookRequest.getSize(), sort);

        Specification<Book> bookSpecification = BookSpecification.getSpecification(searchBookRequest);
        return bookRepository.findAll(bookSpecification, pageable);
    }

    @Override
    public String deleteBookById(String bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        bookRepository.delete(book);

        return "Book deleted successfully";
    }

    @Override
    public String updateBookById(String bookId, BookRequest bookRequest) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setYearPublished(bookRequest.getYearPublished());
        book.setEdition(bookRequest.getEdition());
        book.setCategory(bookRequest.getCategory());
        book.setLanguage(bookRequest.getLanguage());
        book.setPages(bookRequest.getPages());
        book.setLocation(bookRequest.getLocation());
        book.setTotalCopies(bookRequest.getTotalCopies());
        book.setAvailableCopies(bookRequest.getAvailableCopies());

        bookRepository.saveAndFlush(book);
        return "Book updated successfully";
    }

    @Override
    public long count() {
        return bookRepository.count();
    }
}
