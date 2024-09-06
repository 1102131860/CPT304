package Library;

import Book.Book;
import BookState.AvailableState;
import User.User;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    private Book book;
    private List<User> observers;

    public Observable(Book book, List<User> observers){
        this.book = book;
        this.observers = observers;
    }

    public Book getBook(){
        return book.copy();
    }

    public List<User> getObservers(){
        return new ArrayList<>(observers);
    }

    public void addObserver(User observer){
        observers.add(observer);
    }

    public void removeObserver(User observer){
        observers.remove(observer);
    }

    public void notifyObservers(String message){
        if (book.isAvailable()) { // judge whether it is available
            for (User observer : observers) {
                observer.update(message);
            }
        }
    }
}
