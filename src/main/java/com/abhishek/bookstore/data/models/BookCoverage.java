package com.abhishek.bookstore.data.models;

import java.util.List;

import com.abhishek.bookstore.data.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCoverage {
    private Book book;
    private List<MediaPost> posts;
}
