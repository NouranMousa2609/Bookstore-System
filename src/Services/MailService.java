package Services;

import Models.Book;

public class MailService {
    public void sendEmail(Book book, String email) {
        System.out.println("Sending " + book.getTitle() + " to email: " + email);
        System.out.println("Email sent successfully!");
    }
}
