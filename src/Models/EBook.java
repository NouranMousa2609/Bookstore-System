package Models;


import Services.MailService;

public class EBook extends Book {
    private String fileType;

    public EBook(String isbn, String title, String author, int yearPublished, double price, String fileType) {
        super(isbn, title, author, yearPublished, price, Integer.MAX_VALUE);
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public void deliver(String email, String address) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required for ebook delivery");
        }

        System.out.println("Preparing ebook for email delivery - " + title);
        MailService mailService = new MailService();
        mailService.sendEmail(this, email);
    }

    @Override
    public boolean canBePurchased() {
        return true;
    }

    @Override
    public void reduceStock(int quantity) {

    }

    @Override
    public String toString() {
        return "[EBOOK-" + fileType.toUpperCase() + "] " + super.toString();
    }
}
