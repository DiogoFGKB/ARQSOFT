package pt.psoft.g1.books.bookmanagement.publishers;

import pt.psoft.g1.books.bookmanagement.api.BookViewAMQP;
import pt.psoft.g1.books.bookmanagement.model.Book;

public interface BookEventPublisher {

    BookViewAMQP sendBookCreated(Book book);

    BookViewAMQP sendBookUpdated(Book book, Long currentVersion);

    BookViewAMQP sendBookDeleted(Book book, Long currentVersion);
}