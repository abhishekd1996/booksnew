package com.abhishek.bookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;

import com.abhishek.bookstore.data.repositories.AuthorRepository;
import com.abhishek.bookstore.exceptions.BookNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abhishek.bookstore.data.entities.Author;
import com.abhishek.bookstore.data.entities.Book;
import com.abhishek.bookstore.data.models.BookSearchRequest;
import com.abhishek.bookstore.data.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(
            final BookRepository repository,
            final AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book registerBook(final Book book) {
        return repository.save(book);
    }

    @Override
    public Optional<Book> findByIsbn(final String isbn) {
        return repository.findById(isbn);
    }

    @Override
    public Book getByIsbn(final String isbn) {
        return findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("No book found for given isbn:" + isbn));
    }

    @Override
    public Page<Book> findBooks(
            final BookSearchRequest request, final Pageable pageable) {
        return repository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(request.getIsbn())) {
                predicates.add(cb.equal(root.get("isbn"), request.getIsbn()));
            }
            if (StringUtils.isNotBlank(request.getTitle())) {
                predicates.add(cb.like(root.get("title"), "%" + request.getTitle() + "%"));
            }
            if (StringUtils.isNotBlank(request.getAuthor())) {
                predicates.add(cb.like(root.get("author").get("name"), "%" + request.getAuthor() + "%"));
            }
            if (predicates.isEmpty()) {
                return null;
            } else {
                return cb.or(predicates.toArray(new Predicate[0]));
            }
        }, pageable);
    }

    @Override
    public Page<Author> findAllAuthors(final Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findByAuthorId(final Integer authorId, final Pageable pageable) {
        return repository.findAllByAuthorId(authorId, pageable);
    }
}
