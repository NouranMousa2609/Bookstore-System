package Models;

import Services.ShippingService;

public class PaperBook extends Book {
    public PaperBook(String isbn, String title, String author, int yearPublished, double price, int stock) {
        super(isbn, title, author, yearPublished, price, stock);
    }

    @Override
    public void deliver(String email, String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Quantum book store: Address is required for paper book delivery");
        }

        System.out.println("Quantum book store: Preparing paper book for shipping - " + title);
        ShippingService shippingService = new ShippingService();
        shippingService.ship(this, address);
    }

    @Override
    public boolean canBePurchased() {
        return stock > 0;
    }

    @Override
    public String toString() {
        return "Quantum book store: [PAPER] " + super.toString();
    }

}
