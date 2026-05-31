package pt.psoft.g1.readers.readermanagement.repositories;

import pt.psoft.g1.readers.readermanagement.model.Reader;
import java.util.Optional;

public interface ReaderRepository {
    Reader save(Reader reader);
    Optional<Reader> findByEmail(String email);
}