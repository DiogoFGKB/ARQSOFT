package pt.psoft.g1.users.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoleDTO {

    public final String authority;

    public RoleDTO(@JsonProperty("authority") String authority) {
        this.authority = authority;
    }
}
