package pt.psoft.g1.readers.readermanagement.services;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pt.psoft.g1.readers.readermanagement.model.Reader;
import pt.psoft.g1.readers.readermanagement.model.ReaderTemp;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-04T10:49:47+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class ReaderTempToFinalMapperImpl implements ReaderTempToFinalMapper {

    @Override
    public Reader toFinalReader(ReaderTemp readerTemp, String userId) {
        if ( readerTemp == null && userId == null ) {
            return null;
        }

        String readerId = null;
        String name = null;
        String email = null;
        if ( readerTemp != null ) {
            readerId = readerTemp.getReaderId();
            name = readerTemp.getName();
            email = readerTemp.getEmail();
        }
        String userId1 = null;
        userId1 = userId;

        Reader reader = new Reader( readerId, userId1, name, email );

        return reader;
    }
}
