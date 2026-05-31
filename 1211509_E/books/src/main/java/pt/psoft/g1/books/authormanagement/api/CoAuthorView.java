package pt.psoft.g1.books.authormanagement.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.books.bookmanagement.api.BookShortView;

import java.util.List;
import java.util.Map;
@Data
@Getter
@Setter
@AllArgsConstructor
public class CoAuthorView {
    private String name;
    private Map<String, Object> _links;
    private List<BookShortView> books;
}
