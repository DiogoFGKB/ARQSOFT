package pt.psoft.g1.lendings.lendingmanagement.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pt.psoft.g1.lendings.lendingmanagement.model.Lending;
import pt.psoft.g1.lendings.shared.api.MapperInterface;

import java.util.*;

/**
 * Brief guides:
 * <a href="https://www.baeldung.com/mapstruct">https://www.baeldung.com/mapstruct</a>
 * <p>
 * <a href="https://medium.com/@susantamon/mapstruct-a-comprehensive-guide-in-spring-boot-context-1e7202da033e">https://medium.com/@susantamon/mapstruct-a-comprehensive-guide-in-spring-boot-context-1e7202da033e</a>
 * */
@Mapper(componentModel = "spring")
public interface LendingViewMapper {

    @Mapping(target = "daysUntilReturn",
            expression = "java(lending.getDaysUntilReturn().orElse(null))")

    @Mapping(target = "daysOverdue",
            expression = "java(lending.getDaysOverdue().orElse(null))")

    @Mapping(target = "fineValueInCents",
            expression = "java(lending.getFineValueInCents().orElse(null))")

    LendingView toView(Lending lending);

    List<LendingView> toView(List<Lending> lendings);
}


