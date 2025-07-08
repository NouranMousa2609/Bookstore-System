package Services;

import Models.Book;

public class ShippingService {
    public void ship(Book book, String address) {
        System.out.println("Shipping " + book.getTitle() + " to address: " + address);
        System.out.println("Tracking number: TRACK-" + System.currentTimeMillis());
    }
}
