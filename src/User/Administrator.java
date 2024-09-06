package User;

import Book.*;
import Factory.*;
import User.User;

public class Administrator implements User {
    private String name;
    private String email;
    private String ID;
    private BookFactory bookCreator;
    private UserFactory userCreator;


    public Administrator(String name, String email, String ID){
        this.name = name;
        this.email = email;
        this.ID = ID;
        this.bookCreator = new ArticleFactory(); // initialize with articleFactory
        this.userCreator = new AdministratorFactory(); // initialize with administratorFactory
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
    public Administrator copy() {
        Administrator copyAdministrator = new Administrator(name, email, ID);
        copyAdministrator.bookCreator = bookCreator;
        copyAdministrator.userCreator = userCreator;
        return copyAdministrator;
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
        if (!(that instanceof Administrator thatAdministrator)) // if that is not of an instance of Administrator class
            return false;
        return /*this.name.equals(thatAdministrator.name) &&
                this.email.equals(thatAdministrator.email) &&*/
                this.ID.equals(thatAdministrator.ID); // if we only think that ID is unique
    }

    @Override
    public String toString() {
        return "Administrator ID: " + getID() + ", name: " + getName() + ", email: " + getEmail();
    }

    public Book createJournal(){
        bookCreator = new JournalFactory(); // use Journal Factory to create new Journal rather than interface book
        return bookCreator.createBook();
    }

    public Book createArticle(){
        bookCreator = new ArticleFactory();
        return bookCreator.createBook();
    }

    public User createAdministrator(){
        userCreator = new AdministratorFactory();
        return userCreator.createUser();
    }

    public User createStudent(){
        userCreator = new StudentFactory();
        return userCreator.createUser();
    }

}
