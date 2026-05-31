package pt.psoft.g1.readers.readermanagement.infraestructure.repositories.impl;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.psoft.g1.readers.readermanagement.model.ReaderTemp;
import pt.psoft.g1.readers.readermanagement.repositories.ReaderTempRepository;

import java.util.Optional;

public interface SpringDataReaderTempRepositoryImpl
        extends ReaderTempRepository, CrudRepository<ReaderTemp, String> {

    @Override
    @Query("SELECT r FROM ReaderTemp r WHERE r.readerId = :readerId")
    Optional<ReaderTemp> findByReaderId(@Param("readerId") @NotNull String readerId);

    @Override
    @Query("SELECT r FROM ReaderTemp r WHERE r.email = :username")
    Optional<ReaderTemp> findByUsername(@Param("username") @NotNull String username);

    // save() and delete() come from CrudRepository
}
