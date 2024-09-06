package Book;

import BookState.BookState;

public interface Book {
    String getBookID();
    String getTitle();
    String getAuthor();
    BookState getBookState();
    void setBookState(BookState state);
    void borrowBook();
    void returnBook();
    Book copy();
    boolean equals(Object that);
    String toString();
    boolean isAvailable();
}
