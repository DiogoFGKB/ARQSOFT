package pt.psoft.g1.books.authormanagement.services;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pt.psoft.g1.books.authormanagement.model.Author;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-03T05:23:58+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl extends AuthorMapper {

    @Override
    public Author create(CreateAuthorRequest request) {
        if ( request == null ) {
            return null;
        }

        String name = null;
        String bio = null;
        String photoURI = null;

        name = map( request.getName() );
        bio = map( request.getBio() );
        photoURI = map( request.getPhotoURI() );

        Author author = new Author( name, bio, photoURI );

        author.setPhoto( map( request.getPhotoURI() ) );

        return author;
    }

    @Override
    public void update(UpdateAuthorRequest request, Author author) {
        if ( request == null ) {
            return;
        }

        author.setPhoto( map( request.getPhoto() ) );
        author.setName( map( request.getName() ) );
        author.setBio( map( request.getBio() ) );
    }
}
