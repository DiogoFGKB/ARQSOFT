package pt.psoft.g1.books.genremanagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.psoft.g1.books.bookmanagement.services.GenreBookCountDTO;
import pt.psoft.g1.books.genremanagement.model.Genre;

import java.util.Optional;

public interface GenreRepository {

    Iterable<Genre> findAll();
    Optional<Genre> findByString(String genreName);
    Genre save(Genre genre);
    Page<GenreBookCountDTO> findTop5GenreByBookCount(Pageable pageable);
    void delete(Genre genre);
}
