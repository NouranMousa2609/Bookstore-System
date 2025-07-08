package Models;


public class ShowcaseBook extends Book {

    public ShowcaseBook(String isbn, String title, String author, int yearPublished) {
        super(isbn, title, author, yearPublished, 0.0, 0);
    }

    @Override
    public void deliver(String email, String address) {
        throw new UnsupportedOperationException("Showcase books cannot be delivered");
    }

    @Override
    public boolean canBePurchased() {
        return false;
    }

    @Override
    public void reduceStock(int quantity) {
        throw new UnsupportedOperationException("Cannot reduce stock for showcase books");
    }

    @Override
    public String toString() {
        return "[SHOWCASE] " + isbn + " - " + title + " by " + author + " (" + yearPublished + ") - NOT FOR SALE";
    }
}
