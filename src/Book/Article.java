package Book;

import Book.Book;
import BookState.BookState;
import BookState.AvailableState;

public class Article implements Book {
    private String bookID;
    private String title;
    private String author;
    private String genre;
    private BookState state;

    public Article(String ID, String title, String author, String genre) {
        this.bookID = ID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.state = new AvailableState();
    }

    @Override
    public String getBookID(){
        return bookID;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public BookState getBookState() {
        return state;
    }

    @Override
    public void setBookState(BookState state){
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
    public Article copy() {
        Article copyArticle = new Article(bookID, title, author, genre);
        copyArticle.setBookState(state.copy());
        return copyArticle;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) // if there are same pointer, must be true
            return true;
        if (that == null) // before forced pointer convert
            return false;
        if (!(that instanceof Article thatArticle)) // if that is not of an instance of Article class
            return false;
        return this.bookID.equals(thatArticle.bookID) /*&& we only think the bookID is unique, no more checking
                this.title.equals(thatArticle.title) &&
                this.author.equals(thatArticle.author) &&
                this.genre.equals(thatArticle.genre) &&
                this.state.equals(thatArticle.state)*/;
    }

    @Override
    public String toString(){
        return "ID: "+ bookID + ", Title: " + title + ", Author: " + author + ", Genre: "
                + genre + ", State: " + state.toString();
    }

    @Override
    public boolean isAvailable() {
        return state.getClass() == AvailableState.class;
    }
}
