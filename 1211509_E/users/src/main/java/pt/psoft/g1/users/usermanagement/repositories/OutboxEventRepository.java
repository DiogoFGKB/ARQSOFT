package pt.psoft.g1.users.usermanagement.repositories;

import pt.psoft.g1.users.usermanagement.model.OutboxEvent;

import java.util.List;

public interface OutboxEventRepository {

    OutboxEvent save(OutboxEvent event);

    List<OutboxEvent> findByStatus(String status);
}
