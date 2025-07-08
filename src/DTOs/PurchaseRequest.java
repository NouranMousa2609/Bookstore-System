package com.quantum.bookstore.dto;

/**
 * Data Transfer Object for purchase requests.
 * This follows the principle of encapsulation and data integrity.
 */
public class PurchaseRequest {
    private String isbn;
    private int quantity;
    private String email;
    private String address;
    
    public PurchaseRequest(String isbn, int quantity, String email, String address) {
        this.isbn = isbn;
        this.quantity = quantity;
        this.email = email;
        this.address = address;
    }
    
    // Getters
    public String getIsbn() { return isbn; }
    public int getQuantity() { return quantity; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    
    @Override
    public String toString() {
        return String.format("Quantum book store: PurchaseRequest{isbn='%s', quantity=%d, email='%s', address='%s'}", 
                           isbn, quantity, email, address);
    }
}
