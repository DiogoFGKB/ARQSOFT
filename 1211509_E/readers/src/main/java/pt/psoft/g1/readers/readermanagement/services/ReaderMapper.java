package pt.psoft.g1.readers.readermanagement.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.psoft.g1.readers.readermanagement.model.Reader;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetails;
import pt.psoft.g1.readers.readermanagement.model.ReaderDetailsTemp;
import pt.psoft.g1.readers.readermanagement.model.ReaderTemp;

import java.util.List;

/**
 * Brief guide:
 * <a href="https://www.baeldung.com/mapstruct">https://www.baeldung.com/mapstruct</a>
 * */
@Mapper(componentModel = "spring", uses = {ReaderService.class})
public abstract class ReaderMapper {

    @Mapping(target = "photo", source = "photoURI")
    @Mapping(target = "interestList", source = "interestList")
    public abstract ReaderDetails createReaderDetails(int readerNumber, Reader reader, CreateReaderRequest request, String photoURI, List<String> interestList);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "reader", source = "readerTemp")
    @Mapping(target = "readerNumber", expression = "java(new ReaderNumber(0))")
    @Mapping(target = "birthDate", expression = "java(new BirthDate(request.getBirthDate()))")
    @Mapping(target = "phoneNumber", expression = "java(new PhoneNumber(request.getPhoneNumber()))")
    @Mapping(target = "gdprConsent", constant = "true")
    @Mapping(target = "marketingConsent", source = "request.marketing")
    @Mapping(target = "thirdPartySharingConsent", source = "request.thirdParty")
    @Mapping(target = "photo", source = "photoURI")
    @Mapping(target = "interestList", source = "request.interestList")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    public abstract ReaderDetailsTemp createReaderDetailsTemp(
            ReaderTemp readerTemp,
            CreateReaderRequest request,
            String photoURI
    );


}
