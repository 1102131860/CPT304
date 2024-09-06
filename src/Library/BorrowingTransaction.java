package Library;

import Book.Book;
import User.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BorrowingTransaction {
    private Book borrowBook;
    private User borrowUser;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    public BorrowingTransaction(Book book, User user, LocalDateTime borrowDate){
        this.borrowBook = book;
        this.borrowUser = user;
        this.borrowDate = borrowDate;
        // assume with no return date (null), if return then set it later
    }

    public Book getBorrowBook() {
        return borrowBook.copy(); // return a copied one to prevent property alteration
    }

    public User getBorrowUser(){
        return borrowUser.copy(); // return a copied one to prevent property alteration
    }

    public LocalDateTime getBorrowDate(){
        return borrowDate; // LocalDateTime is immutable so it can be directly passed
    }

    public LocalDateTime getReturnDate(){
        return returnDate; // LocalDateTime is immutable so it can be directly passed
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate; // if user returns book, then call it to change the record
    }

    public int calculateBorrowDays(){
        LocalDateTime currentDate = LocalDateTime.now();
        int borrowDays = (returnDate != null) ?
                (int) ChronoUnit.DAYS.between(borrowDate, returnDate) :
                (int) ChronoUnit.DAYS.between(borrowDate, currentDate) ;
        return Math.max(0, borrowDays);
    }

}
