package Factory;

import Models.*;

public class BookFactory {


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
                throw new IllegalArgumentException("Unknown book type: " + type);
        }
    }


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
