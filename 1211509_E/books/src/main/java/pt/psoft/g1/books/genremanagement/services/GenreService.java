package pt.psoft.g1.books.genremanagement.services;

import pt.psoft.g1.books.bookmanagement.services.GenreBookCountDTO;
import pt.psoft.g1.books.genremanagement.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Iterable<Genre> findAll();
    Genre save(Genre genre);
    Optional<Genre> findByString(String name);
    List<GenreBookCountDTO> findTopGenreByBooks();
}
