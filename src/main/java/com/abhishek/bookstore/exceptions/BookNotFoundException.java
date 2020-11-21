package com.abhishek.bookstore.exceptions;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(final String message) {
        super(message);
    }

    public BookNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
