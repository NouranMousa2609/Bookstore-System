package com.quantum.bookstore.model;

/**
 * Showcase/Demo book that is not for sale.
 */
public class ShowcaseBook extends Book {
    
    public ShowcaseBook(String isbn, String title, String author, int yearPublished) {
        super(isbn, title, author, yearPublished, 0.0, 0); // No price, no stock
    }
    
    @Override
    public void deliver(String email, String address) {
        throw new UnsupportedOperationException("Quantum book store: Showcase books cannot be delivered");
    }
    
    @Override
    public boolean canBePurchased() {
        return false; // Showcase books are not for sale
    }
    
    @Override
    public void reduceStock(int quantity) {
        throw new UnsupportedOperationException("Quantum book store: Cannot reduce stock for showcase books");
    }
    
    @Override
    public String toString() {
        return "Quantum book store: [SHOWCASE] " + isbn + " - " + title + " by " + author + " (" + yearPublished + ") - NOT FOR SALE";
    }
}
