package com.dilipkumarg.netent.bookstore.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;

import com.dilipkumarg.netent.bookstore.data.entities.Author;
import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.models.BookSearchRequest;
import com.dilipkumarg.netent.bookstore.data.repositories.BookRepository;
import com.dilipkumarg.netent.bookstore.repo.InMemoryAuthorRepository;
import com.dilipkumarg.netent.bookstore.repo.InMemoryBookRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookServiceTest {

    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    public void onBefore() {
        bookRepository = new InMemoryBookRepositoryImpl();
        bookService = new BookServiceImpl(bookRepository, new InMemoryAuthorRepository());
    }

    @Test
    void registerBook() {
        Book book = new Book();
        book.setIsbn("xyz");
        book.setTitle("abcd");
        book.setPrice(10.0);
        book.setAuthor(new Author(1, "abc"));

        bookService.registerBook(book);

        assertEquals(book, bookRepository.getOne("xyz"));
    }

    @Test
    void getByIsbn() {
        Book book = new Book();
        book.setIsbn("xyz");
        book.setTitle("abcd");
        book.setPrice(10.0);
        book.setAuthor(new Author(1, "abc"));

        bookService.registerBook(book);

        assertEquals(book, bookService.getByIsbn("xyz"));
    }

    @Test
    void findBooks() {
        Book book = new Book();
        book.setIsbn("xyz");
        book.setTitle("abcd");
        book.setPrice(10.0);
        book.setAuthor(new Author(1, "abc"));

        bookService.registerBook(book);

        assertEquals(1, bookService.findBooks(new BookSearchRequest(), Pageable.unpaged()).getTotalElements());
    }
}
