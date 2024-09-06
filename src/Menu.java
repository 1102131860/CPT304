import FineDecorator.*;
import Library.*;
import SearchStrategy.*;
import User.*;
import Book.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Library library;
    private User user;

    public void mainMenu(){
        initializeSystem(); // initialize library and may be check the user identification

        boolean out = false;
        while (!out){
            System.out.println("\nLibrary Menu \n");
            System.out.println("Choose an option: ");
            System.out.println("1. Add book");// 1, 2, 3 only belong to administrator
            System.out.println("2. Remove book");
            System.out.println("3. User registration");
            System.out.println("4. Search a book");
            System.out.println("5. Borrow a book");// 4 and 5 only belong to student
            System.out.println("6. Return a book");
            System.out.println("7. Check books in and out");
            System.out.println("8. Calculate Overdue Fine");
            System.out.println("9. Subscribe a book");
            System.out.println("10. UnSubscribe a book");
            System.out.println("11. Exit");

            int command = getIntInput("Type an integer between 1 and 11");
            switch (command) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    userRegistration();
                    break;
                case 4:
                    searchBooks();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    checkBooksInOut();
                    break;
                case 8:
                    calculateOverDueFine();
                    break;
                case 9:
                    subscribe();
                    break;
                case 10:
                    unsubscribe();
                    break;
                case 11:
                    System.out.println("System exits");
                    out = true;
                    break;
                default:
                    System.out.println("Invalid Choice, please re-enter between 1 and 11");
            }
            // here, notify the observer the states of their subscribed books
            library.notifyObservables("Your subscribed books has newest states, please check out");
        }
    }

    public void initializeSystem(){
        library = Library.getInstance(); // library initialization firstly
        Library newLibrary = Library.getInstance();
        int cmd = getIntInput("1: Log in with an Administrator Account\n2: Log in with a Student Account");
        if (cmd == 1) {
            user = new Administrator("Admin_test", "123@789.com","1");
        } else if (cmd == 2){
            user = new Student("Student_test", "456@789.com","2");
            /*
             * In order to test the borrow book and return book function, add some books to library initially
             */
            Book existedArticle = new Article("0", "Initial Article", "Administrator", "Unknown");
            Book existedJournal = new Journal("0", "Initial Journal", "Administrator", "Unknown");
            library.addBook(existedArticle);
            library.addBook(existedJournal);
        } else {
            System.out.println("Invalid Input");
            initializeSystem();
        }
    }

    private void addBook(){
        System.out.println("Add Book module");
        if (user.getClass() != Administrator.class){
            System.out.println("You don't have right to add book!!!");
            return;
        }
        boolean out = false;
        while(!out) {
            System.out.println("\nPlease tell me the type of book you want to add.\n");
            System.out.println("1. Article");// 1, 2, 3 only belong to administrator
            System.out.println("2. Journal");
            System.out.println("3. Exit add book module");
            int cmd = getIntInput("Type the number. Please enter between 1 and 3");
            switch (cmd) {
                case 1:
                    library.addBook(((Administrator) user).createArticle());
                    break;
                case 2:
                    library.addBook(((Administrator) user).createJournal());
                    break;
                case 3:
                    out = true;
                    break;
                default:
                    System.out.println("Invalid Choice, please re-enter between 1 and 3");
            }
        }
    }

    private void removeBook(){
        System.out.println("Remove Book module");
        if (user.getClass() != Administrator.class){
            System.out.println("You don't have right to remove book!!!");
            return;
        }
        boolean out = false;
        while (!out) {
            List<Book> searchedBooks = searchBooksWithReturn(library.getBooks());
            if (searchedBooks.isEmpty()) {
                System.out.println("No this book.");
            } else {
                library.removeBook(searchedBooks.get(0)); // remove first book it searched
            }
            String cmd = getStringInput("Continue Remove? Y/N");
            out = !cmd.equalsIgnoreCase("y"); // only "Y" or "y" will continue searching
        }
    }

    private void userRegistration(){
        System.out.println("User Registration module");
        if (user.getClass() != Administrator.class){
            System.out.println("You don't have right to register user!!!");
            return;
        }
        boolean out = false;
        while(!out) {
            System.out.println("\nPlease tell me the type of user you want to add.\n");
            System.out.println("1. Student");// 1, 2, 3 only belong to administrator
            System.out.println("2. Administrator");
            System.out.println("3. Exit user registration module");
            int cmd = getIntInput("Type the number.");
            switch (cmd) {
                case 1:
                    library.registerUser(((Administrator) user).createStudent());
                    break;
                case 2:
                    library.registerUser(((Administrator) user).createAdministrator());
                    break;
                case 3:
                    out = true;
                    break;
                default:
                    System.out.println("Invalid Choice, please re-enter between 1 and 3");
            }
        }
    }

    private void searchBooks(){
        System.out.println("Search books module");
        boolean out = false;
        while (!out) {
            List<Book> searchedBooks = searchBooksWithReturn(library.getBooks());
            if (searchedBooks.isEmpty()) {
                System.out.println("No this book.");
            } else {
                for(Book book : searchedBooks){
                    System.out.println(book.toString());
                }
            }
            String cmd = getStringInput("Continue Searching? Y/N");
            out = !cmd.equalsIgnoreCase("y"); // only "Y" or "y" will continue searching
        }
    }

    private void borrowBook() {
        System.out.println("Borrow Book module");
        if (user.getClass() != Student.class){
            System.out.println("You don't have right to borrow book!!!");
            return;
        }
        boolean out = false;
        while (!out) {
            List<Book> findBooks = searchBooksWithReturn(library.getBooks());
            if (findBooks.isEmpty()){
                System.out.println("No matched results");
            } else {
                library.borrowBook(((Student) user), findBooks.get(0)); // only student can borrow the first searched book
            }
            String cmd = getStringInput("Continue borrow book? Y/N");
            out = !cmd.equalsIgnoreCase("y"); // only "Y" or "y" will continue borrowing
        }
    }

    private void returnBook(){
        System.out.println("Return Book module");
        if (user.getClass() != Student.class){
            System.out.println("You don't have right to return book!!!");
            return;
        }
        boolean out = false;
        while (!out) {
            List<BorrowingTransaction> transactions = library.getTransactions();
            List<Book> findBooks = new ArrayList<>();
            for (BorrowingTransaction transaction : transactions){
                if (transaction.getBorrowUser().equals(user) && !transaction.getBorrowBook().isAvailable()){
                    findBooks.add(transaction.getBorrowBook());
                }
            }
            if (findBooks.isEmpty()){
                System.out.println("No borrowed books");
            } else {
                library.returnBook(((Student) user), findBooks.get(0)); // only student can borrow the first searched book
            }
            String cmd = getStringInput("Continue return book? Y/N");
            out = !cmd.equalsIgnoreCase("y"); // only "Y" or "y" will continue borrowing
        }
    }

    private void checkBooksInOut(){
        System.out.println("Check Books in and out module");
        for(Book book : library.getBooks()){
            System.out.println(book.toString()); // just print out all the books
        }
    }

    private void calculateOverDueFine(){
        System.out.println("Calculate OverDueFine module");
        double finePerDay = getDoubleInput("The fine per day is: ");
        List<BorrowingTransaction> transactions = library.getTransactions();
        List<Book> findBooks = new ArrayList<>();
        for (BorrowingTransaction transaction : transactions){
            if (!transaction.getBorrowBook().isAvailable()){
                findBooks.add(transaction.getBorrowBook());
            }
        }
        if (findBooks.isEmpty()){
            System.out.println("All books are returned and no overdue fine");
        } else {
            for (Book book : findBooks){
                OverdueFineDecorator overdueBook = new OverdueFineDecorator(book, finePerDay, transactions);
                System.out.println("The fine of " + book.toString() + " is " + overdueBook.calculateFine());
            }
        }
    }

    private void subscribe(){
        System.out.println("Subscribe module");
        boolean out = false;
        while (!out) {
            List<Book> findBooks = searchBooksWithReturn(library.getBooks());
            if (findBooks.isEmpty()){
                System.out.println("No matched results");
            } else {
                library.addObservable(findBooks.get(0), user); // both student and administrator can subscribe a book
            }
            String cmd = getStringInput("Continue subscribe? Y/N");
            out = !cmd.equalsIgnoreCase("y"); // only "Y" or "y" will continue subscribe
        }
    }

    private void unsubscribe(){
        System.out.println("Unsubscribe module");
        boolean out = false;
        while (!out) {
            List<Book> findBooks = new ArrayList<>();
            for (Observable observable : library.getObservables()){
                if(observable.getObservers().contains(user)){
                    findBooks.add(observable.getBook()); // obtain the subscribed book
                }
            }
            if (findBooks.isEmpty()){
                System.out.println("No subscribed book");
            } else {
                library.removeObservable(findBooks.get(0), user); // both student and administrator can unsubscribe a book
            }
            String cmd = getStringInput("Continue unsubscribe? Y/N");
            out = !cmd.equalsIgnoreCase("y"); // only "Y" or "y" will continue unsubscribe
        }
    }

    public List<Book> searchBooksWithReturn(List<Book> books) {
        String keyword; // search keyword
        List<Book> searchedBooks = new ArrayList<>(); // search result
        System.out.println("Choose a search type: ");
        System.out.println("1. By author name");// 1, 2, 3 only belong to administrator
        System.out.println("2. By title");
        int command = getIntInput("Type an integer between 1 and 2");
        switch (command) {
            case 1:
                keyword = getStringInput("Please type in the author name.");
                AuthorSearchStrategy hunter = new AuthorSearchStrategy();
                searchedBooks = hunter.search(books, keyword);
                break;
            case 2:
                keyword = getStringInput("Please type in the title name.");
                TitleSearchStrategy hunter2 = new TitleSearchStrategy();
                searchedBooks = hunter2.search(books, keyword);
                break;
            default:
                System.out.println("Invalid Choice, please re-enter between 1 and 2");
        }
        return searchedBooks;
    }

    public int getIntInput(String inputMessage) {
        System.out.println(inputMessage);
        Scanner kb = new Scanner(System.in);
        String input = kb.nextLine();
        int cmd;
        try {
            cmd = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number option chosen");
            cmd = getIntInput(inputMessage);
        }
        return cmd;
    }

    public String getStringInput(String inputMessage) {
        // Method to receive a message to display, and get a string input from keyboard
        System.out.println(inputMessage);
        Scanner kb = new Scanner(System.in);
        return kb.nextLine();
    }

    public double getDoubleInput(String inputMessage){
        System.out.println(inputMessage);
        Scanner kb = new Scanner(System.in);
        String input = kb.nextLine();
        double cmd;
        try {
            cmd = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number option chosen");
            cmd = getDoubleInput(inputMessage);
        }
        return cmd;
    }

}
