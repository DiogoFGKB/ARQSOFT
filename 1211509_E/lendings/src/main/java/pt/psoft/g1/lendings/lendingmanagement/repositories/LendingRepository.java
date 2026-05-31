package pt.psoft.g1.lendings.lendingmanagement.repositories;

import pt.psoft.g1.lendings.lendingmanagement.model.Lending;
import pt.psoft.g1.lendings.shared.services.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LendingRepository {
    Optional<Lending> findByLendingNumber(String lendingNumber);
    int getCountFromCurrentYear();
    List<Lending> listOutstandingByReaderNumber(String readerNumber);

    Lending save(Lending lending);

    void delete(Lending lending);

}
