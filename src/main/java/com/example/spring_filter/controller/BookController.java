package com.example.spring_filter.controller;

import com.example.spring_filter.constant.APIUrl;
import com.example.spring_filter.dto.request.BookRequest;
import com.example.spring_filter.dto.request.SearchBookRequest;
import com.example.spring_filter.dto.response.BookResponse;
import com.example.spring_filter.dto.response.CommonResponse;
import com.example.spring_filter.dto.response.PagingResponse;
import com.example.spring_filter.entity.Admin;
import com.example.spring_filter.entity.Book;
import com.example.spring_filter.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIUrl.BOOK)
public class BookController {

    @Autowired
    private final BookService bookService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<BookResponse>> createBook(
            @RequestBody BookRequest bookRequest
            ){
        BookResponse book = bookService.createBook(bookRequest);

        CommonResponse<BookResponse> commonResponse = CommonResponse.<BookResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Book created successfully")
                .data(book)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @GetMapping(produces = "application/json", path = APIUrl.PATH_VAR_BOOK_ID)
    public ResponseEntity<CommonResponse<Book>> getBookById (
            @PathVariable("bookId") String bookId
    ){
        Book book = bookService.getById(bookId);

        CommonResponse<Book> commonResponse = CommonResponse.<Book>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Book retrieved successfully")
                .data(book)
                .build();

        return ResponseEntity.ok(commonResponse);

    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CommonResponse<Page<Book>>> getAllBooks(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "menuId") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "language", required = false) String language
    ) {

        SearchBookRequest searchBookRequest = SearchBookRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .title(title)
                .author(author)
                .category(category)
                .language(language)
                .build();

        Page<Book> allBooks = bookService.getAllBooks(searchBookRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(allBooks.getPageable().getPageNumber() +1)
                .size(allBooks.getPageable().getPageSize())
                .totalPages(allBooks.getTotalPages())
                .totalElements(allBooks.getTotalElements())
                .hasNext(allBooks.hasNext())
                .hasPrevious(allBooks.hasPrevious())
                .build();

        CommonResponse<Page<Book>> commonResponse = CommonResponse.<Page<Book>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully retrieving all books")
                .data(allBooks)
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(commonResponse);
    }

    @PutMapping(produces = "application/json", path = APIUrl.PATH_VAR_BOOK_ID)
    public ResponseEntity<String> updateBookById(
            @PathVariable("bookId") String bookId,
            @RequestBody BookRequest bookRequest
    ){
        String book = bookService.updateBookById(bookId, bookRequest);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping(produces = "application/json",path = APIUrl.PATH_VAR_BOOK_ID)
    public ResponseEntity<String> deleteBookById(
            @PathVariable("bookId") String bookId
    ){
        String book = bookService.deleteBookById(bookId);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

}
