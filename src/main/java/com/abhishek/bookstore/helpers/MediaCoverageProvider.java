package com.abhishek.bookstore.helpers;

import java.util.List;

import com.abhishek.bookstore.data.entities.Book;
import com.abhishek.bookstore.data.models.MediaPost;

public interface MediaCoverageProvider {

    List<MediaPost> search(Book book);
}
