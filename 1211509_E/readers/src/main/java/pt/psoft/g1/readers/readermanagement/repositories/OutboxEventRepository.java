package pt.psoft.g1.readers.readermanagement.repositories;

import pt.psoft.g1.readers.readermanagement.model.OutboxEvent;

import java.util.List;

public interface OutboxEventRepository {

    OutboxEvent save(OutboxEvent event);

    List<OutboxEvent> findByStatus(String status);

    void deleteById(Long id);
}
