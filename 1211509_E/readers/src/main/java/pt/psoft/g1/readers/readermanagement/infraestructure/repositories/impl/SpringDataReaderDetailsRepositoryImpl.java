package pt.psoft.g1.readers.readermanagement.infraestructure.repositories.impl;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;
import pt.psoft.g1.readers.readermanagement.repositories.ReaderDetailsRepository;

import java.util.List;
import java.util.Optional;


public interface SpringDataReaderDetailsRepositoryImpl extends ReaderDetailsRepository, CrudRepository<ReaderDetails, String> {
    @Override
    @Query("SELECT r " +
            "FROM ReaderDetails r " +
            "WHERE r.readerNumber.readerNumber = :readerNumber")
    Optional<ReaderDetails> findByReaderNumber(@Param("readerNumber") @NotNull String readerNumber);

    @Override
    @Query("SELECT r " +
            "FROM ReaderDetails r " +
            "WHERE r.phoneNumber.phoneNumber = :phoneNumber")
    List<ReaderDetails> findByPhoneNumber(@Param("phoneNumber") @NotNull String phoneNumber);

    @Override
    @Query("SELECT r FROM ReaderDetails r WHERE r.reader.email = :email")
    Optional<ReaderDetails> findByUsername(@Param("email") @NotNull String email);


    @Query("SELECT COUNT(rd) FROM ReaderDetails rd WHERE YEAR(rd.createdAt) = YEAR(CURRENT_DATE)")
    int getCountFromCurrentYear();

}

