package pt.psoft.g1.readers.readermanagement.infraestructure.repositories.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetailsTemp;
import pt.psoft.g1.readers.readermanagement.repositories.ReaderDetailsTempRepository;

import java.util.Optional;

@Repository
public interface SpringDataReaderDetailsTempRepositoryImpl
        extends ReaderDetailsTempRepository, CrudRepository<ReaderDetailsTemp, String> {
    @Override
    @Query("SELECT r FROM ReaderDetailsTemp r WHERE r.readerNumber.readerNumber = :readerNumber")
    Optional<ReaderDetailsTemp> findByReaderNumber(@Param("readerNumber") String readerNumber);

    @Override
    @Query("SELECT rd FROM ReaderDetailsTemp rd WHERE rd.reader.readerId = :readerId")
    Optional<ReaderDetailsTemp> findByReaderId(@Param("readerId") String readerId);

    // save() and deleteById() come from CrudRepository automatically
}
