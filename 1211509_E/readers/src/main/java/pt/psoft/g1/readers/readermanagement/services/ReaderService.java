package pt.psoft.g1.readers.readermanagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import pt.psoft.g1.readers.readermanagement.dto.UserDTO;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;
import pt.psoft.g1.readers.shared.services.Page;

/**
 *
 */
public interface ReaderService {
    ReaderDetails create(CreateReaderRequest request, String photoURI);
    ReaderDetails update(String username, UpdateReaderRequest request, long desireVersion, String photoURI);
    Optional<ReaderDetails> findByUsername(final String username);
    Optional<ReaderDetails> findByReaderNumber(String readerNumber);
    List<ReaderDetails> findByPhoneNumber(String phoneNumber);
    Iterable<ReaderDetails> findAll();
    //Optional<Reader> update(UpdateReaderRequest request) throws Exception;
    void persistTemporary(UserDTO dto);
    Optional<ReaderDetails> removeReaderPhoto(String readerNumber, long desiredVersion);
}
