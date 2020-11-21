package com.abhishek.bookstore.services;

import com.abhishek.bookstore.data.models.BookCoverage;

public interface MediaCoverageService {

    BookCoverage searchForBook(String isbn);
}
