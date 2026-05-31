package pt.psoft.g1.readers.readermanagement.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;


@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReaderBookCountDTO {
    private ReaderDetails readerDetails;
    private long lendingCount;
}
