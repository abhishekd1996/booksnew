package com.dilipkumarg.netent.bookstore.repo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.repositories.BookRepository;

public class InMemoryBookRepositoryImpl extends InMemoryJpaRepositoryImpl<Book, String> implements BookRepository {

    @Override
    public Page<Book> findAllByAuthorId(final Integer authorId, final Pageable pageable) {
        return new PageImpl<>(map.values().stream()
                .filter(book -> authorId.equals(book.getAuthor().getId()))
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<Book> findOne(
            final Specification<Book> spec) {
        return Optional.empty();
    }

    @Override
    public List<Book> findAll(
            final Specification<Book> spec) {
        return null;
    }

    @Override
    public Page<Book> findAll(
            final Specification<Book> spec, final Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    public List<Book> findAll(
            final Specification<Book> spec, final Sort sort) {
        return super.findAll(sort);
    }

    @Override
    public long count(
            final Specification<Book> spec) {
        return super.count();
    }
}
