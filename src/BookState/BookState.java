package BookState;

import Book.Book;

public interface BookState {
    void borrowBook(Book book);
    void returnBook(Book book);
    BookState copy();
    boolean equals(Object that);
    String toString();
}
