package FineDecorator;

import Book.Book;
import BookState.BookState;
import FineDecorator.FineDecorator;

import Library.BorrowingTransaction;

import java.util.ArrayList;
import java.util.List;

public class OverdueFineDecorator implements FineDecorator {
    private Book decoratedBook;
    private double finePerDay;
    private List<BorrowingTransaction> transactions; // there only be one transaction list in the system

    public OverdueFineDecorator(Book book, double fine, List<BorrowingTransaction> transactions) {
        this.decoratedBook = book;
        this.finePerDay = fine;
        this.transactions = transactions;
    }

    @Override
    public String getBookID(){
        return decoratedBook.getBookID();
    }

    @Override
    public String getTitle(){
        return decoratedBook.getTitle();
    }

    @Override
    public String getAuthor(){
        return decoratedBook.getAuthor();
    }

    @Override
    public BookState getBookState(){
        return decoratedBook.getBookState();
    }

    @Override
    public void setBookState(BookState state){
        decoratedBook.setBookState(state);
    }

    @Override
    public void borrowBook(){
        decoratedBook.borrowBook();
    }

    @Override
    public void returnBook(){
        decoratedBook.returnBook();
    }

    @Override
    public OverdueFineDecorator copy(){
        return new OverdueFineDecorator(decoratedBook.copy(), finePerDay, new ArrayList<>(transactions));
    }

    @Override
    public boolean equals(Object that){
        if (this == that)
            return true;
        if (that == null)
            return false;
        if (!(that instanceof OverdueFineDecorator thatOverdueFineDecorator))
            return false;
        return this.decoratedBook.equals(thatOverdueFineDecorator.decoratedBook); // we only compare the book itself
    }

    @Override
    public String toString() {
        return decoratedBook.toString() + ", fine per day: " + finePerDay;
    }

    @Override
    public boolean isAvailable(){
        return decoratedBook.isAvailable();
    }

    @Override
    public double calculateFine() {
        /*
        * There could be some other calculation method here
        */
        return finePerDay * calculateOverdueDays(); // this is an example
    }

    private int calculateOverdueDays(){
        // This part is an example from chatGPT
        // There may a threshold that borrowDay becomes overDueDay
        int overdueDays = 0;
        for (BorrowingTransaction transaction : transactions) {
            if (transaction.getBorrowBook().equals(decoratedBook) && transaction.getReturnDate() == null) { // unreturned book
                overdueDays = transaction.calculateBorrowDays();
                break;
            }
        }
        return Math.max(0, overdueDays);
    }

}
