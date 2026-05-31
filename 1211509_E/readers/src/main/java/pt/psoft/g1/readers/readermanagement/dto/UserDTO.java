package pt.psoft.g1.readers.readermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {

    private final String id;
    private final String readerId;
    private final String username;
    private final String password;
    private final String fullname;
    private final Long version;

    public UserDTO(@JsonProperty("id") String id,
                   @JsonProperty("readerId") String readerId,
                   @JsonProperty("username") String username,
                   @JsonProperty("password") String password,
                   @JsonProperty("fullname") String fullname,
                   @JsonProperty("version") Long version) {
        this.id = id;
        this.readerId = readerId;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.version = version;
    }
}
