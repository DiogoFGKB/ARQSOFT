package pt.psoft.g1.readers.readermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BirthDateDTO {
    public final String birthDate;

    public BirthDateDTO(@JsonProperty("birthDate") String birthDate) {
        this.birthDate = birthDate;
    }
}

