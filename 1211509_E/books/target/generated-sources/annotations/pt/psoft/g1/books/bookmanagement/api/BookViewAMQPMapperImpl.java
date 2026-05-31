package pt.psoft.g1.books.bookmanagement.api;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pt.psoft.g1.books.bookmanagement.model.Book;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T05:23:58+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class BookViewAMQPMapperImpl extends BookViewAMQPMapper {

    @Override
    public BookViewAMQP toBookViewAMQP(Book book) {
        if ( book == null ) {
            return null;
        }

        BookViewAMQP bookViewAMQP = new BookViewAMQP();

        bookViewAMQP.setIsbn( map( book.getIsbn() ) );
        bookViewAMQP.setDescription( map( book.getDescription() ) );
        bookViewAMQP.setTitle( map( book.getTitle() ) );
        bookViewAMQP.setGenre( map( book.getGenre() ) );
        bookViewAMQP.setVersion( book.getVersion() );

        bookViewAMQP.setAuthorIds( mapAuthors(book.getAuthors()) );

        return bookViewAMQP;
    }

    @Override
    public List<BookViewAMQP> toBookViewAMQP(List<Book> bookList) {
        if ( bookList == null ) {
            return null;
        }

        List<BookViewAMQP> list = new ArrayList<BookViewAMQP>( bookList.size() );
        for ( Book book : bookList ) {
            list.add( toBookViewAMQP( book ) );
        }

        return list;
    }
}
