package BookState;

import Book.Book;
import BookState.BookState;

public class AvailableState implements BookState {
    @Override
    public void borrowBook(Book book) {
        System.out.println(book.getTitle() + " is absent now");
        book.setBookState(new AbsentState());
    }

    @Override
    public void returnBook(Book book) {
        System.out.println(book.getTitle() + " is already available and cannot be returned");
    }

    @Override
    public AvailableState copy(){
        return new AvailableState();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;
        if (that == null)
            return false;
        if (!(that instanceof AvailableState thatAvailableState))
            return false;
        return this.getClass() == thatAvailableState.getClass();
    }

    @Override
    public String toString(){
        return "Available";
    }
}
