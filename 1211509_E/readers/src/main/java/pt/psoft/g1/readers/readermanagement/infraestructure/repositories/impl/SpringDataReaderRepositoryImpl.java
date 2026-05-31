package pt.psoft.g1.readers.readermanagement.infraestructure.repositories.impl;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pt.psoft.g1.readers.readermanagement.model.Reader;
import pt.psoft.g1.readers.readermanagement.repositories.ReaderRepository;

import java.util.Optional;

public interface SpringDataReaderRepositoryImpl extends ReaderRepository, CrudRepository<Reader, String> {

    @Override
    @Query("SELECT r FROM Reader r where r.email = :email")
    Optional<Reader> findByEmail(@Param("email") @NotNull String email);
}