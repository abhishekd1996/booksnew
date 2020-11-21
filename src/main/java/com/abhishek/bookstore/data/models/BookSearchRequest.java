package com.abhishek.bookstore.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchRequest {

    private String isbn;
    private String title;
    private String author;
}
