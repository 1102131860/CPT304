package Book;

import Book.Book;
import BookState.BookState;
import BookState.AvailableState;

public class Journal implements Book {
    private String bookID;
    private String title;
    private String author;
    private String publication;
    private BookState state;

    public Journal (String ID, String title, String author, String publication){
        this.bookID = ID;
        this.title = title;
        this.author = author;
        this.publication = publication;
        this.state = new AvailableState(); // initialize with available state
    }

    @Override
    public String getBookID(){
        return bookID;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getPublication(){
        return publication;
    }

    @Override
    public BookState getBookState() {
        return state;
    }

    @Override
    public void setBookState(BookState state) {
        this.state = state;
    }

    @Override
    public void borrowBook() {
        state.borrowBook(this);
    }

    @Override
    public void returnBook() {
        state.returnBook(this);
    }

    @Override
    public Journal copy() {
        Journal copyJournal = new Journal(bookID, title, author, publication);
        copyJournal.setBookState(state.copy());
        return copyJournal;
    }

    @Override
    public boolean equals(Object that){
        if (this == that) // if there are same pointer, must be true
            return true;
        if (that == null) // before forced pointer convert
            return false;
        if (!(that instanceof Journal thatJournal)) // if that is not of an instance of Journal class
            return false;
        return this.bookID.equals(thatJournal.bookID) /*&& we only think the bookID is unique, no more checking
                this.title.equals(thatJournal.title) &&
                this.author.equals(thatJournal.author) &&
                this.publication.equals(thatJournal.publication) &&
                this.state.equals(thatJournal.state)*/;
    }

    @Override
    public String toString(){
        return "ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Publication: "
                + publication + ", State: " + state.toString();
    }

    @Override
    public boolean isAvailable() {
        return state.getClass() == AvailableState.class;
    }
}
