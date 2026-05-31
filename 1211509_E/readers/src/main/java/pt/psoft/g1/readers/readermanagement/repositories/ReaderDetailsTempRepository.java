package pt.psoft.g1.readers.readermanagement.repositories;

import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetailsTemp;

import java.util.Optional;

public interface ReaderDetailsTempRepository {

    ReaderDetailsTemp save(ReaderDetailsTemp details);

    void deleteById(String readerId);


    Optional<ReaderDetailsTemp> findByReaderId(String readerId);

    Optional<ReaderDetailsTemp> findByReaderNumber(String readerNumber);
}
