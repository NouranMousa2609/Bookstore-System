package Services;

import DTOs.*;
import Models.*;
import exception.BookException;

import java.util.*;

public class BookStoreService {
    private Map<String, Book> inventory;

    public BookStoreService() {
        this.inventory = new HashMap<>();
    }


    public void addBook(Book book) {
        if (book == null) {
            throw new BookException("Cannot add null book to inventory");
        }

        if (inventory.containsKey(book.getIsbn())) {
            Book existingBook = inventory.get(book.getIsbn());
            existingBook.setStock(existingBook.getStock() + book.getStock());
            System.out.println("Updated stock for " + book.getTitle() +
                    " (ISBN: " + book.getIsbn() + ") to " + existingBook.getStock());
        } else {
            inventory.put(book.getIsbn(), book);
            System.out.println("Added new book - " + book.getTitle() +
                    " (ISBN: " + book.getIsbn() + ")");
        }
    }

    public List<Book> removeOutdatedBooks(int yearsThreshold) {
        List<Book> outdatedBooks = new ArrayList<>();
        List<String> isbnToRemove = new ArrayList<>();

        for (Book book : inventory.values()) {
            if (book.isOutdated(yearsThreshold)) {
                outdatedBooks.add(book);
                isbnToRemove.add(book.getIsbn());
            }
        }

        for (String isbn : isbnToRemove) {
            inventory.remove(isbn);
            System.out.println("Removed outdated book with ISBN: " + isbn);
        }

        return outdatedBooks;
    }


    public PurchaseResponse buyBook(PurchaseRequest request) {
        try {
            if (request.getQuantity() <= 0) {
                throw new BookException("Quantity must be greater than 0");
            }


            Book book = inventory.get(request.getIsbn());
            if (book == null) {
                throw new BookException("Book not found with ISBN: " + request.getIsbn());
            }


            if (!book.canBePurchased()) {
                throw new BookException("This book is not available for purchase: " + book.getTitle());
            }


            book.reduceStock(request.getQuantity());


            double totalAmount = book.getPrice() * request.getQuantity();


            book.deliver(request.getEmail(), request.getAddress());

            System.out.println("Purchase completed - " + book.getTitle() +
                    " (Quantity: " + request.getQuantity() +
                    ", Total: $" + String.format("%.2f", totalAmount) + ")");

            return new PurchaseResponse("Purchase successful", totalAmount, true);

        } catch (Exception e) {
            return new PurchaseResponse(e.getMessage(), 0.0, false);
        }
    }

    public Book getBookByIsbn(String isbn) {
        return inventory.get(isbn);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(inventory.values());
    }


    public int getTotalBooks() {
        return inventory.size();
    }


    public void displayInventory() {
        System.out.println("==================== INVENTORY ====================");
        if (inventory.isEmpty()) {
            System.out.println("No books in inventory");
        } else {
            inventory.values().forEach(System.out::println);
        }
        System.out.println(" ================================================");
    }
}
