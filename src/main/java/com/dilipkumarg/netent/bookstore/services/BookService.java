package com.dilipkumarg.netent.bookstore.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dilipkumarg.netent.bookstore.data.entities.Author;
import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.models.BookSearchRequest;

public interface BookService {

    Book registerBook(Book book);

    Optional<Book> findByIsbn(String isbn);

    Book getByIsbn(String isbn);

    Page<Book> findBooks(BookSearchRequest request, Pageable pageable);

    // Enhancements
    Page<Author> findAllAuthors(Pageable pageable);

    Page<Book> findByAuthorId(Integer authorId, final Pageable pageable);
}
