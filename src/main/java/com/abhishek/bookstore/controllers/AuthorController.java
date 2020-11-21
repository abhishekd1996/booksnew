package com.abhishek.bookstore.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.bookstore.config.Constants;
import com.abhishek.bookstore.data.entities.Author;
import com.abhishek.bookstore.data.entities.Book;
import com.abhishek.bookstore.services.BookService;

@RestController
@RequestMapping(Constants.BASE_PATH + "/authors")
public class AuthorController {

    private final BookService bookService;

    public AuthorController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<Author> findAll(Pageable pageable) {
        return bookService.findAllAuthors(pageable);
    }

    @GetMapping("/{authorId}")
    public Page<Book> findByAuthor(@PathVariable("authorId") Integer authorId, Pageable pageable) {
        return bookService.findByAuthorId(authorId, pageable);
    }
}
