package Factory;

import Book.Journal;
import java.util.Scanner;

public class JournalFactory extends BookFactory {
    @Override
    public Journal getBook(String bookID, String title, String author) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the publication.");
        String publication = scanner.nextLine();
        System.out.println("A new Journal has been created");
        return new Journal(bookID,title, author, publication);
    }
}
