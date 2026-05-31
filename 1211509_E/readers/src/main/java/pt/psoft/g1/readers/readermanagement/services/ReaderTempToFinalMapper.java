package pt.psoft.g1.readers.readermanagement.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.psoft.g1.readers.readermanagement.model.Reader;
import pt.psoft.g1.readers.readermanagement.model.ReaderTemp;

@Mapper(componentModel = "spring")
public interface ReaderTempToFinalMapper {

    @Mapping(target = "readerId", source = "readerTemp.readerId")
    @Mapping(target = "userId", source = "userId") // passed manually
    @Mapping(target = "name", source = "readerTemp.name")
    @Mapping(target = "email", source = "readerTemp.email")
    Reader toFinalReader(ReaderTemp readerTemp, String userId);
}

