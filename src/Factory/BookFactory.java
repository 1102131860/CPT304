package Factory;

import Book.Book;
import java.util.Scanner;

abstract public class BookFactory { // this class can be instanced with a factory object to create book
    public Book createBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the ID.");
        String bookID = scanner.nextLine();
        System.out.println("Please type in the title.");
        String title = scanner.nextLine();
        System.out.println("Please type in the author.");
        String author = scanner.nextLine();
        return getBook(bookID, title, author);
    };

    public abstract Book getBook(String bookID, String title, String author);
}
