package FineDecorator;

import Book.Book;
import Library.BorrowingTransaction;

import java.util.List;

public interface FineDecorator extends Book {
    double calculateFine();
}
