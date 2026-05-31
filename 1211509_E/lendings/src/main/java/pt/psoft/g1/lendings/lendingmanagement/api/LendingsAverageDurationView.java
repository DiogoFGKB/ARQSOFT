package pt.psoft.g1.lendings.lendingmanagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "The average lending duration.")
public class LendingsAverageDurationView {
    @NotNull
    private Double lendingsAverageDuration;
}
