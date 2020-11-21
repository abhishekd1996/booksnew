package com.abhishek.bookstore.exceptions;

public class BookStoreException extends RuntimeException {

    public BookStoreException(final String message) {
        super(message);
    }

    public BookStoreException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
