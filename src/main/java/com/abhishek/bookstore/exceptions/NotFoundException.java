package com.abhishek.bookstore.exceptions;

public class NotFoundException extends BookStoreException {

    public NotFoundException(final String message) {
        super(message);
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
