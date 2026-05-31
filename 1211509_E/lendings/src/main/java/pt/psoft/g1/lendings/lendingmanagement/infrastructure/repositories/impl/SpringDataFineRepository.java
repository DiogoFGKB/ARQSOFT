package pt.psoft.g1.lendings.lendingmanagement.infrastructure.repositories.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt.psoft.g1.lendings.lendingmanagement.model.Fine;
import pt.psoft.g1.lendings.lendingmanagement.repositories.FineRepository;

import java.util.Optional;


public interface SpringDataFineRepository extends FineRepository, CrudRepository<Fine, Long> {

    @Override
    @Query("SELECT f " +
            "FROM Fine f " +
            "JOIN Lending l ON f.lending.pk = l.pk " +
            "WHERE l.lendingNumber.lendingNumber = :lendingNumber")
    Optional<Fine> findByLendingNumber(String lendingNumber);

}
