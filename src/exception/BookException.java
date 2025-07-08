package com.quantum.bookstore.exception;

/**
 * Custom exception for book-related errors.
 */
public class BookException extends RuntimeException {
    
    public BookException(String message) {
        super("Quantum book store: " + message);
    }
    
    public BookException(String message, Throwable cause) {
        super("Quantum book store: " + message, cause);
    }
}
