package com.dilipkumarg.netent.bookstore.exceptions;

public class AuthorNotFoundException extends NotFoundException {
    public AuthorNotFoundException(final String message) {
        super(message);
    }

    public AuthorNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
