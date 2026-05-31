package pt.psoft.g1.readers.readermanagement.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.query.Param;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface ReaderDetailsRepository {
    Optional<ReaderDetails> findByReaderNumber(@Param("readerNumber") @NotNull String readerNumber);
    List<ReaderDetails> findByPhoneNumber(@Param("phoneNumber") @NotNull String phoneNumber);
    Optional<ReaderDetails> findByUsername(@Param("username") @NotNull String username);
    int getCountFromCurrentYear();
    ReaderDetails save(ReaderDetails readerDetails);
    Iterable<ReaderDetails> findAll();
    void delete(ReaderDetails readerDetails);
}
