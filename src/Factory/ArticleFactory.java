package Factory;

import Book.Article;
import java.util.Scanner;

public class ArticleFactory extends BookFactory {
    @Override
    public Article getBook(String bookID, String title, String author) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type in the genre.");
        String genre = scanner.nextLine();
        System.out.println("A new Article has been created");
        return new Article(bookID,title, author, genre);
    }
}
