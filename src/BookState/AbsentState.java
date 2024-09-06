package BookState;

import Book.Book;
import BookState.BookState;

public class AbsentState implements BookState {
    @Override
    public void borrowBook(Book book) {
        System.out.println(book.getTitle() +" is already absent and cannot be borrowed");
    }

    @Override
    public void returnBook(Book book) {
        System.out.println(book.getTitle() + " is available now");
        book.setBookState(new AvailableState());
    }

    @Override
    public AbsentState copy(){
        return new AbsentState();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;
        if (that == null)
            return false;
        if (!(that instanceof AbsentState thatAbsentState))
            return false;
        return this.getClass() == thatAbsentState.getClass();
    }

    @Override
    public String toString(){
        return "Absent";
    }
}
