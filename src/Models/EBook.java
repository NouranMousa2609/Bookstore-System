package com.quantum.bookstore.model;

import com.quantum.bookstore.service.MailService;

/**
 * EBook implementation that can be sent via email.
 */
public class EBook extends Book {
    private String fileType;
    
    public EBook(String isbn, String title, String author, int yearPublished, double price, String fileType) {
        super(isbn, title, author, yearPublished, price, Integer.MAX_VALUE); // Unlimited stock for digital books
        this.fileType = fileType;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    @Override
    public void deliver(String email, String address) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Quantum book store: Email is required for ebook delivery");
        }
        
        System.out.println("Quantum book store: Preparing ebook for email delivery - " + title);
        MailService mailService = new MailService();
        mailService.sendEmail(this, email);
    }
    
    @Override
    public boolean canBePurchased() {
        return true; // Digital books are always available
    }
    
    @Override
    public void reduceStock(int quantity) {
        // No stock reduction for digital books
    }
    
    @Override
    public String toString() {
        return "Quantum book store: [EBOOK-" + fileType.toUpperCase() + "] " + super.toString();
    }
}
