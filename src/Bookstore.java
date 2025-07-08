import Services.*;
import Factory.*;
import Models.*;
import DTOs.*;


import java.util.*;

public class Bookstore {
    private BookStoreService bookstore;
    private Scanner scanner;

    public Bookstore() {
        this.bookstore = new BookStoreService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("book store: Welcome to the our Bookstore System!");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeOutdatedBooks();
                    break;
                case 3:
                    buyBook();
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    searchBookByISBN();
                    break;
                case 6:
                    System.out.println("Thank you for using our Bookstore");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n===  Book Store Menu ===");
        System.out.println("1. Add Book to Inventory");
        System.out.println("2. Remove Outdated Books");
        System.out.println("3. Buy a Book");
        System.out.println("4. Display Inventory");
        System.out.println("5. Search Book by ISBN");
        System.out.println("6. Exit");
        System.out.print("Please choose an option (1-6): ");
    }

    private int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private void addBook() {
        System.out.println("\n=== Add Book to Inventory ===");
        System.out.println("Book Types:");
        System.out.println("1. Paper Book");
        System.out.println("2. eBook");
        System.out.println("3. Showcase Book");
        System.out.print("Choose book type (1-3): ");

        int bookType = getUserChoice();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter year published: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        try {
            Book book = null;

            switch (bookType) {
                case 1:
                    System.out.print("Enter price: $");
                    double price = scanner.nextDouble();
                    System.out.print("Enter stock quantity: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    book = BookFactory.createPaperBook(isbn, title, author, year, price, stock);
                    break;

                case 2:
                    System.out.print("Enter price: $");
                    double ebookPrice = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter file type (PDF, EPUB, etc.): ");
                    String fileType = scanner.nextLine();
                    book = BookFactory.createEBook(isbn, title, author, year, ebookPrice, fileType);
                    break;

                case 3:
                    book = BookFactory.createShowcaseBook(isbn, title, author, year);
                    break;

                default:
                    System.out.println("Invalid book type!");
                    return;
            }

            if (book != null) {
                bookstore.addBook(book);
                System.out.println("Book added successfully!");
            }

        } catch (Exception e) {
            System.out.println("Error adding book - " + e.getMessage());
        }
    }

    private void removeOutdatedBooks() {
        System.out.println("\n=== Remove Outdated Books ===");
        System.out.print("Enter years threshold (books older than this will be removed): ");
        try {
            int years = scanner.nextInt();
            scanner.nextLine();

            List<Book> removedBooks = bookstore.removeOutdatedBooks(years);

            if (removedBooks.isEmpty()) {
                System.out.println("No outdated books found.");
            } else {
                System.out.println("Removed " + removedBooks.size() + " outdated books:");
                for (Book book : removedBooks) {
                    System.out.println("  - " + book.getTitle() + " (Published: " + book.getYearPublished() + ")");
                }
            }
        } catch (Exception e) {
            System.out.println("Error removing books - " + e.getMessage());
        }
    }

    private void buyBook() {
        System.out.println("\n=== Buy a Book ===");

        System.out.print("Enter ISBN of the book you want to buy: ");
        String isbn = scanner.nextLine();

        Book book = bookstore.getBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }

        System.out.println("Found book: " + book.getTitle() + " by " + book.getAuthor());
        System.out.println("Price: $" + book.getPrice());

        if (!book.canBePurchased()) {
            System.out.println("This book is not available for purchase.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        PurchaseRequest request = new PurchaseRequest(isbn, quantity, email, address);
        PurchaseResponse response = bookstore.buyBook(request);

        if (response.isSuccess()) {
            System.out.println("Purchase successful!");
            System.out.println("Total amount: $" + String.format("%.2f", response.getTotalAmount()));
        } else {
            System.out.println("Purchase failed - " + response.getMessage());
        }
    }

    private void displayInventory() {
        System.out.println("\n=== Current Inventory ===");
        bookstore.displayInventory();
    }

    private void searchBookByISBN() {
        System.out.println("\n=== Search Book by ISBN ===");
        System.out.print("Enter ISBN to search: ");
        String isbn = scanner.nextLine();

        Book book = bookstore.getBookByIsbn(isbn);
        if (book != null) {
            System.out.println(" Book found:");
            System.out.println(book);
        } else {
            System.out.println(" No book found with ISBN: " + isbn);
        }
    }
}
