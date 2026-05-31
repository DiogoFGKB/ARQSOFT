package pt.psoft.g1.users.usermanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "outbox_event")
@Getter
@Setter
@NoArgsConstructor
public class OutboxEvent {

    @Id
    private String id; // UUID

    private String aggregateId; // userId
    private String type;        // TEMP_USER_CREATED
    private String payload;     // JSON
    private String status;      // NEW, SENT

    public OutboxEvent(String aggregateId, String type, String payload) {
        this.id = java.util.UUID.randomUUID().toString();
        this.aggregateId = aggregateId;
        this.type = type;
        this.payload = payload;
        this.status = "NEW";
    }
}
