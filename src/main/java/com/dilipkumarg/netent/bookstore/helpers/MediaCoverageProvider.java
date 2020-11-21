package com.dilipkumarg.netent.bookstore.helpers;

import java.util.List;

import com.dilipkumarg.netent.bookstore.data.entities.Book;
import com.dilipkumarg.netent.bookstore.data.models.MediaPost;

public interface MediaCoverageProvider {

    List<MediaPost> search(Book book);
}
