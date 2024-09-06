package Library;

import Book.*;
import User.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static Library instance;
    private List<Book> books;
    private List<User> users;
    private List<BorrowingTransaction> transactions;
    private List<Observable> observables;

    private Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        transactions = new ArrayList<>();
        observables = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            synchronized (Library.class) {
                if (instance == null) {
                    instance = new Library();
                }
            }
        }
        return instance;
    }

    public List<Book> getBooks(){
        return new ArrayList<>(books);
    }

    public List<User> getUsers(){
        return new ArrayList<>(users);
    }

    public List<BorrowingTransaction> getTransactions(){
        return new ArrayList<>(transactions);
    }

    public List<Observable> getObservables(){
        return new ArrayList<>(observables);
    }

    public void addBook(Book book){
        // To ensure that there the book ID is unique, title, author may be the same
        if (books.contains(book)){
            System.out.println("The book has existed in the library and please change the bookID");
            return;
        }
        books.add(book);
        System.out.println(book.toString() + " has been added into the library");
    }

    public void removeBook(Book book){
        // Logically the Book must be the one (copy one) that is in this library system
        // To enhance the robustness of this method, we check it firstly
        if (!books.contains(book)){
            System.out.println("The book doesn't exist in the library");
            return;
        }
        books.remove(book);
        System.out.println(book.toString() + " has been removed from the library");
    }

    public void registerUser(User user){
        if (users.contains(user)){
            System.out.println("This account has been registered and please change another ID");
            return;
        }
        users.add(user);
        System.out.println(user.toString() + " has been registered");
    }

    public void borrowBook(Student student, Book book){
        if (!books.contains(book)){
            System.out.println("The book doesn't existed in the library");
            return;
        }
        if (!book.isAvailable()){
            System.out.println("The book is absent and cannot be borrowed");
            return;
        }
        Book borrowedBook = books.get(books.indexOf(book)); // must be the one has existed in the books List
        student.borrowBook(borrowedBook);
        recordBorrowingTransaction(student, borrowedBook, LocalDateTime.now());
        System.out.println(borrowedBook.toString() + " is successfully borrowed");
    }

    public void returnBook(Student student, Book book){
        if (!books.contains(book)){
            System.out.println("The student or book doesn't existed in the library");
            return;
        }
        if (book.isAvailable()){
            System.out.println("The book is already available and cannot be returned");
            return;
        }
        Book returnedBook = books.get(books.indexOf(book));
        student.returnBook(returnedBook);
        updateReturnDate(student, returnedBook, LocalDateTime.now());
        System.out.println(returnedBook.toString() + " is successfully returned");
    }

    private void recordBorrowingTransaction(Student student, Book book, LocalDateTime borrowDate){
        BorrowingTransaction record = new BorrowingTransaction(book, student, borrowDate);
        transactions.add(record);
    }

    private void updateReturnDate(Student student, Book book, LocalDateTime returnDate){
        for (BorrowingTransaction transaction : transactions){
            if (transaction.getBorrowUser().equals(student) && transaction.getBorrowBook().equals(book)){
                transaction.setReturnDate(returnDate);
            }
        }
    }

    public void addObservable(Book book, User user){
        for (Observable observable : observables){
            if (observable.getBook().equals(book) && !observable.getObservers().contains(user)){
                observable.addObserver(user); // the book has existed in the observable book list
                System.out.println("successfully subscribe " + book.toString());
                return;
            }
        }
        // add the book to the observable book list and then add observer
        Observable observable = new Observable(book, new ArrayList<>());
        observable.addObserver(user);
        observables.add(observable);
        System.out.println("successfully subscribe " + book.toString());
    }

    public void removeObservable(Book book, User user){
        for (Observable observable : observables){
            if (observable.getBook().equals(book) && observable.getObservers().contains(user)){
                observable.removeObserver(user);
                System.out.println("successfully unsubscribe " + book.toString());
            }
        }
    }

    public void notifyObservables(String message){
        for (Observable observable : observables){
            observable.notifyObservers(message);
        }
    }

    // The following methods may not occur at this library class as we supply the get methods
    /*
    public double consultOverdueFine(Book book, double finePerDay){
        OverdueFineDecorator overdueBook = new OverdueFineDecorator(book, finePerDay, transactions);
        return overdueBook.calculateFine();
    }
    */
}
