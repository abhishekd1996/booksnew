package com.dilipkumarg.netent.bookstore.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.models.BookCoverage;
import com.dilipkumarg.netent.bookstore.data.models.MediaPost;
import com.dilipkumarg.netent.bookstore.helpers.MediaCoverageProvider;

@Service
public class MediaCoverageServiceImpl implements MediaCoverageService {

    private final BookService bookService;
    private final List<MediaCoverageProvider> providers;

    public MediaCoverageServiceImpl(
            final BookService bookService,
            final List<MediaCoverageProvider> providers) {
        this.bookService = bookService;
        this.providers = providers;
    }

    @Override
    public BookCoverage searchForBook(final String isbn) {
        final Book book = bookService.getByIsbn(isbn);
        final List<MediaPost> posts = providers.stream()
                .flatMap(provider -> provider.search(book).stream())
                .collect(Collectors.toList());
        return new BookCoverage(book, posts);
    }
}
