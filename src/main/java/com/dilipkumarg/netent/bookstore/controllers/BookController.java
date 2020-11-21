package com.dilipkumarg.netent.bookstore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dilipkumarg.netent.bookstore.config.Constants;
import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.models.BookCoverage;
import com.dilipkumarg.netent.bookstore.data.models.BookSearchRequest;
import com.dilipkumarg.netent.bookstore.services.BookService;
import com.dilipkumarg.netent.bookstore.services.MediaCoverageService;

@RestController
@RequestMapping(Constants.BASE_PATH + "/books")
public class BookController {

    private final BookService bookService;

    private final MediaCoverageService mediaCoverageService;

    public BookController(
            final BookService bookService,
            final MediaCoverageService mediaCoverageService) {
        this.bookService = bookService;
        this.mediaCoverageService = mediaCoverageService;
    }

    @GetMapping
    public Page<Book> findAll(
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            Pageable pageable) {
        return bookService.findBooks(new BookSearchRequest(isbn, title, author), pageable);
    }

    @PostMapping
    public Book create(@RequestBody Book request) {
        return bookService.registerBook(request);
    }

    @GetMapping("/{isbn}")
    public Book findBook(@PathVariable("isbn") final String isbn) {
        return bookService.getByIsbn(isbn);
    }

    @GetMapping("/{isbn}/coverage")
    public BookCoverage findCoverage(@PathVariable("isbn") final String isbn) {
        return mediaCoverageService.searchForBook(isbn);
    }

}
