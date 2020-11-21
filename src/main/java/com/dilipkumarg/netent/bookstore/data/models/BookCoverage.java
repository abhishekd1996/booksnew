package com.dilipkumarg.netent.bookstore.data.models;

import java.util.List;

import com.dilipkumarg.netent.bookstore.data.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCoverage {
    private Book book;
    private List<MediaPost> posts;
}
