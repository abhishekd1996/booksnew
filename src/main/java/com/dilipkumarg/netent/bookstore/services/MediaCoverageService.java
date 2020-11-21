package com.dilipkumarg.netent.bookstore.services;

import com.dilipkumarg.netent.bookstore.data.models.BookCoverage;

public interface MediaCoverageService {

    BookCoverage searchForBook(String isbn);
}
