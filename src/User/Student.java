package User;

import Book.Book;
import User.User;

public class Student implements User {
    private String name;
    private String email;
    private String ID;

    public Student(String name, String email, String ID){
        this.name = name;
        this.email = email;
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public Student copy() {
        return new Student(name, email, ID);
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received notification: " + message);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) // if there are same pointer, must be true
            return true;
        if (that == null) // before forced pointer convert
            return false;
        if (!(that instanceof Student thatStudent)) // if that is not of an instance of Student class
            return false;
        return /*this.name.equals(thatStudent.name) &&
                this.email.equals(thatStudent.email) &&*/
                this.ID.equals(thatStudent.ID); // if we only think that ID is unique
    }

    @Override
    public String toString() {
        return "Student ID: " + getID() + ", name: " + getName() + ", email: " + getEmail();
    }

    public void borrowBook(Book book){
        book.borrowBook();
    }

    public void returnBook(Book book){
        book.returnBook();
    }
}
