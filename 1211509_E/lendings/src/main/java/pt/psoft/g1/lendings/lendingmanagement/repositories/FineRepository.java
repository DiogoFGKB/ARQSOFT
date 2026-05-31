package pt.psoft.g1.lendings.lendingmanagement.repositories;

import pt.psoft.g1.lendings.lendingmanagement.model.Fine;

import java.util.Optional;

public interface FineRepository {

    Optional<Fine> findByLendingNumber(String lendingNumber);

    Fine save(Fine fine);

}
