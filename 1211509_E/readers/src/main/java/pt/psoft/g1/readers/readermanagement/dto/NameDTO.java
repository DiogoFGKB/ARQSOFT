package pt.psoft.g1.readers.readermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NameDTO {
    public final String name;

    public NameDTO(@JsonProperty("name") String name) {
        this.name = name;
    }
}

