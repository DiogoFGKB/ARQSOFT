package pt.psoft.g1.lendings.lendingmanagement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pt.psoft.g1.lendings.exceptions.LendingForbiddenException;
import pt.psoft.g1.lendings.exceptions.NotFoundException;
import pt.psoft.g1.lendings.lendingmanagement.model.Fine;
import pt.psoft.g1.lendings.lendingmanagement.model.Lending;
import pt.psoft.g1.lendings.lendingmanagement.repositories.FineRepository;
import pt.psoft.g1.lendings.lendingmanagement.repositories.LendingRepository;
import pt.psoft.g1.lendings.shared.services.Page;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@PropertySource({"classpath:config/library.properties"})
public class LendingServiceImpl implements LendingService{
    private final LendingRepository lendingRepository;

    @Value("${lendingDurationInDays}")
    private int lendingDurationInDays;
    @Value("${fineValuePerDayInCents}")
    private int fineValuePerDayInCents;

    @Override
    public Optional<Lending> findByLendingNumber(String lendingNumber){
        return lendingRepository.findByLendingNumber(lendingNumber);
    }

    @Override
    public Lending create(final CreateLendingRequest resource) {
        int count = 0;

        Iterable<Lending> lendingList = lendingRepository.listOutstandingByReaderNumber(resource.getReaderNumber());
        for (Lending lending : lendingList) {
            //Business rule: cannot create a lending if user has late outstanding books to return.
            if (lending.getDaysDelayed() > 0) {
                throw new LendingForbiddenException("Reader has book(s) past their due date");
            }
            count++;
            //Business rule: cannot create a lending if user already has 3 outstanding books to return.
            if (count >= 3) {
                throw new LendingForbiddenException("Reader has three books outstanding already");
            }
        }

        final var b = resource.getIsbn();
        final var r = resource.getReaderNumber();
        int seq = lendingRepository.getCountFromCurrentYear()+1;
        final Lending l = new Lending(b,r,seq, lendingDurationInDays, fineValuePerDayInCents );

        return lendingRepository.save(l);
    }
}
