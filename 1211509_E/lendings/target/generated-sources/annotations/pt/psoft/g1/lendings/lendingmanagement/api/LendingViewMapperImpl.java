package pt.psoft.g1.lendings.lendingmanagement.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pt.psoft.g1.lendings.lendingmanagement.model.Lending;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-04T17:58:48+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class LendingViewMapperImpl implements LendingViewMapper {

    @Override
    public LendingView toView(Lending lending) {
        if ( lending == null ) {
            return null;
        }

        String isbn = null;
        String readerNumber = null;
        LocalDate startDate = null;
        LocalDate limitDate = null;
        LocalDate returnedDate = null;
        Integer fineValuePerDayInCents = null;

        isbn = lending.getIsbn();
        readerNumber = lending.getReaderNumber();
        startDate = lending.getStartDate();
        limitDate = lending.getLimitDate();
        returnedDate = lending.getReturnedDate();
        fineValuePerDayInCents = lending.getFineValuePerDayInCents();

        Integer daysUntilReturn = lending.getDaysUntilReturn().orElse(null);
        Integer daysOverdue = lending.getDaysOverdue().orElse(null);
        Integer fineValueInCents = lending.getFineValueInCents().orElse(null);
        Integer rating = null;
        String commentary = null;

        LendingView lendingView = new LendingView( isbn, readerNumber, startDate, limitDate, returnedDate, rating, commentary, fineValuePerDayInCents, daysUntilReturn, daysOverdue, fineValueInCents );

        return lendingView;
    }

    @Override
    public List<LendingView> toView(List<Lending> lendings) {
        if ( lendings == null ) {
            return null;
        }

        List<LendingView> list = new ArrayList<LendingView>( lendings.size() );
        for ( Lending lending : lendings ) {
            list.add( toView( lending ) );
        }

        return list;
    }
}
