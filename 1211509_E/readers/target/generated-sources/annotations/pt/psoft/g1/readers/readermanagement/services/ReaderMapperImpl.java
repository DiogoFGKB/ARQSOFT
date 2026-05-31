package pt.psoft.g1.readers.readermanagement.services;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pt.psoft.g1.readers.readermanagement.model.BirthDate;
import pt.psoft.g1.readers.readermanagement.model.PhoneNumber;
import pt.psoft.g1.readers.readermanagement.model.Reader;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetailsTemp;
import pt.psoft.g1.readers.readermanagement.model.ReaderNumber;
import pt.psoft.g1.readers.readermanagement.model.ReaderTemp;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-04T10:49:47+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Microsoft)"
)
@Component
public class ReaderMapperImpl extends ReaderMapper {

    @Override
    public ReaderDetails createReaderDetails(int readerNumber, Reader reader, CreateReaderRequest request, String photoURI, List<String> interestList) {
        if ( reader == null && request == null && photoURI == null && interestList == null ) {
            return null;
        }

        String birthDate = null;
        String phoneNumber = null;
        boolean gdpr = false;
        boolean marketing = false;
        boolean thirdParty = false;
        if ( request != null ) {
            birthDate = request.getBirthDate();
            phoneNumber = request.getPhoneNumber();
            gdpr = request.getGdpr();
            marketing = request.getMarketing();
            thirdParty = request.getThirdParty();
        }
        int readerNumber1 = 0;
        readerNumber1 = readerNumber;
        Reader reader1 = null;
        reader1 = reader;
        List<String> interestList1 = null;
        List<String> list = interestList;
        if ( list != null ) {
            interestList1 = new ArrayList<String>( list );
        }

        String photoURI1 = null;

        ReaderDetails readerDetails = new ReaderDetails( readerNumber1, reader1, birthDate, phoneNumber, gdpr, marketing, thirdParty, photoURI1, interestList1 );

        readerDetails.setPhoto( photoURI );

        return readerDetails;
    }

    @Override
    public ReaderDetailsTemp createReaderDetailsTemp(ReaderTemp readerTemp, CreateReaderRequest request, String photoURI) {
        if ( readerTemp == null && request == null && photoURI == null ) {
            return null;
        }

        boolean marketingConsent = false;
        boolean thirdPartySharingConsent = false;
        List<String> interestList = null;
        if ( request != null ) {
            marketingConsent = request.getMarketing();
            thirdPartySharingConsent = request.getThirdParty();
            List<String> list = request.getInterestList();
            if ( list != null ) {
                interestList = new ArrayList<String>( list );
            }
        }
        ReaderTemp reader = null;
        reader = readerTemp;

        String id = java.util.UUID.randomUUID().toString();
        ReaderNumber readerNumber = new ReaderNumber(0);
        BirthDate birthDate = new BirthDate(request.getBirthDate());
        PhoneNumber phoneNumber = new PhoneNumber(request.getPhoneNumber());
        boolean gdprConsent = true;
        String photoUri = null;

        ReaderDetailsTemp readerDetailsTemp = new ReaderDetailsTemp( id, reader, readerNumber, birthDate, phoneNumber, gdprConsent, marketingConsent, thirdPartySharingConsent, photoUri, interestList );

        readerDetailsTemp.setPhoto( photoURI );
        readerDetailsTemp.setCreatedAt( java.time.LocalDateTime.now() );

        return readerDetailsTemp;
    }
}
