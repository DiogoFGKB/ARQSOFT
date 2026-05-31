package pt.psoft.g1.books.bookmanagement.repositories;

import org.springframework.data.repository.query.Param;
import pt.psoft.g1.books.bookmanagement.model.Book;
import pt.psoft.g1.books.bookmanagement.services.SearchBooksQuery;

import java.util.List;
import java.util.Optional;


/**
 *
 */
public interface BookRepository {


    List<Book> findByGenre(@Param("genre") String genre);
    List<Book> findByTitle(@Param("title") String title);
    List<Book> findByAuthorName(@Param("authorName") String authorName);
    Optional<Book> findByIsbn(@Param("isbn") String isbn);
    List<Book> findBooksByAuthorNumber(Long authorNumber);

    List<Book> searchBooks(pt.psoft.g1.books.shared.services.Page page, SearchBooksQuery query);

    Book save(Book book);
    void delete(Book book);
}
