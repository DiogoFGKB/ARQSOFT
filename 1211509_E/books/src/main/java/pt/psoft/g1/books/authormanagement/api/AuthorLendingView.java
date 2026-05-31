package pt.psoft.g1.books.authormanagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(description = "An author and its total lendings")
@AllArgsConstructor
@NoArgsConstructor
public class AuthorLendingView {
    @NotNull
    private String authorName;
    @NotNull
    private Long lendingCount;
}
