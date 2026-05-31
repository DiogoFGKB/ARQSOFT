package pt.psoft.g1.lendings.lendingmanagement.api;

import java.time.LocalDate;

public record LendingView(
        String isbn,
        String readerNumber,
        LocalDate startDate,
        LocalDate limitDate,
        LocalDate returnedDate,
        Integer rating,
        String commentary,
        Integer fineValuePerDayInCents,
        Integer daysUntilReturn,
        Integer daysOverdue,
        Integer fineValueInCents
) {}
