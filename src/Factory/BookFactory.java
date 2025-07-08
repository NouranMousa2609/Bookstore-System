package com.quantum.bookstore.factory;

import com.quantum.bookstore.model.Book;
import com.quantum.bookstore.model.EBook;
import com.quantum.bookstore.model.PaperBook;
import com.quantum.bookstore.model.ShowcaseBook;

/**
 * Factory class for creating different types of books.
 * This follows the Factory pattern and Open/Closed Principle.
 */
public class BookFactory {
    
    /**
     * Creates a book based on the type specified.
     */
    public static Book createBook(String type, String isbn, String title, String author, 
                                 int yearPublished, double price, int stock, String fileType) {
        switch (type.toLowerCase()) {
            case "paper":
                return new PaperBook(isbn, title, author, yearPublished, price, stock);
            case "ebook":
                return new EBook(isbn, title, author, yearPublished, price, fileType);
            case "showcase":
                return new ShowcaseBook(isbn, title, author, yearPublished);
            default:
                throw new IllegalArgumentException("Quantum book store: Unknown book type: " + type);
        }
    }
    
    /**
     * Overloaded method for creating books with default values.
     */
    public static Book createPaperBook(String isbn, String title, String author, 
                                      int yearPublished, double price, int stock) {
        return new PaperBook(isbn, title, author, yearPublished, price, stock);
    }
    
    public static Book createEBook(String isbn, String title, String author, 
                                  int yearPublished, double price, String fileType) {
        return new EBook(isbn, title, author, yearPublished, price, fileType);
    }
    
    public static Book createShowcaseBook(String isbn, String title, String author, int yearPublished) {
        return new ShowcaseBook(isbn, title, author, yearPublished);
    }
}
