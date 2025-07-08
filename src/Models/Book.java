package Models;

import java.time.LocalDate;

public abstract class Book {
    protected String isbn;
    protected String title;
    protected String author;
    protected int yearPublished;
    protected double price;
    protected int stock;

    public Book(String isbn, String title, String author, int yearPublished, double price, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
        this.stock = stock;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYearPublished() { return yearPublished; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }


    public void setStock(int stock) { this.stock = stock; }


    public abstract void deliver(String email, String address);


    public abstract boolean canBePurchased();


    public void reduceStock(int quantity) {
        if (stock<quantity)
        {
            throw new IllegalStateException("Insufficient Stock for" + title);
        }
        stock -= quantity;
    }


    public boolean isOutdated(int yearsThreshold) {
        int currentYear = LocalDate.now().getYear();
        return (currentYear - yearPublished) > yearsThreshold;
    }

    @Override
    public String toString() {
        return String.format("%s - %s by %s (%d) - $%.2f [Stock: %d]",
                isbn, title, author, yearPublished, price, stock);
    }
}
