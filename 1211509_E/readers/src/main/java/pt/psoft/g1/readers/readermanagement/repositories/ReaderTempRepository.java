package pt.psoft.g1.readers.readermanagement.repositories;

import pt.psoft.g1.readers.readermanagement.model.Reader;
import pt.psoft.g1.readers.readermanagement.model.ReaderTemp;

import java.util.Optional;

public interface ReaderTempRepository {

    ReaderTemp save(ReaderTemp readerTemp);

    Optional<ReaderTemp> findByReaderId(String readerId);

    Optional<ReaderTemp> findByUsername(String username);

    void deleteById(String readerId);
}
