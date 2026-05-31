package pt.psoft.g1.books.genremanagement.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.psoft.g1.books.genremanagement.model.Genre;
import pt.psoft.g1.books.bookmanagement.services.GenreBookCountDTO;
import pt.psoft.g1.books.genremanagement.services.GenreLendingsDTO;
import pt.psoft.g1.books.genremanagement.services.GenreLendingsPerMonthDTO;
import pt.psoft.g1.books.shared.api.MapperInterface;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GenreViewMapper extends MapperInterface{

    @Mapping(target = "genre", source = "genre")
    public abstract GenreView toGenreView(Genre genre);

    public abstract GenreView mapStringToGenreView(String genre);

    @Mapping(target = "genreView", source = "genre")
    public abstract GenreBookCountView toGenreBookCountView(GenreBookCountDTO genreBookCountView);
    public abstract List<GenreBookCountView> toGenreBookCountView(List<GenreBookCountDTO> genreBookCountView);

    public abstract GenreLendingsView toGenreAvgLendingsView(GenreLendingsDTO dto);
    public abstract List<GenreLendingsView> toGenreAvgLendingsView(List<GenreLendingsDTO> dtos);

    @Mapping(target = "lendingsCount", source = "values")
    public abstract GenreLendingsCountPerMonthView toGenreLendingsCountPerMonthView(GenreLendingsPerMonthDTO dto);
    public abstract List<GenreLendingsCountPerMonthView> toGenreLendingsCountPerMonthView(List<GenreLendingsPerMonthDTO> dtos);


    @Mapping(target = "durationAverages", source = "values")
    public abstract GenreLendingsAvgPerMonthView toGenreLendingsAveragePerMonthView(GenreLendingsPerMonthDTO dto);
    public abstract List<GenreLendingsAvgPerMonthView> toGenreLendingsAveragePerMonthView(List<GenreLendingsPerMonthDTO> dtos);


}
