package pt.psoft.g1.lendings.lendingmanagement.services;

import pt.psoft.g1.lendings.lendingmanagement.model.Lending;
import pt.psoft.g1.lendings.shared.services.Page;

import java.util.List;
import java.util.Optional;

public interface LendingService {
    /**
     * @param lendingNumber
     * @return {@code Optional<Lending>}
     */
    Optional<Lending> findByLendingNumber(String lendingNumber);
    /**
     * @param readerNumber - Reader Number of the Reader associated with the lending
     * @param isbn         - ISBN of the book associated with the lending
     * @param returned     - Wether it's intended to filter by the return status of a lending
     * @return {@code Iterable<Lending>}
     */
    Lending create(CreateLendingRequest resource); //No ID passed, as it is auto generated

}
