package pt.psoft.g1.readers.readermanagement.services;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;

@Data
@Schema(description = "Reader with lending count")
public class ReaderAverageDto {
    @NotNull
    private ReaderDetails readerView;

    private Long lendingCount;
}
