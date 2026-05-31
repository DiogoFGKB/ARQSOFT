package pt.psoft.g1.users.usermanagement.infrastructure.repositories.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.users.usermanagement.model.OutboxEvent;
import pt.psoft.g1.users.usermanagement.repositories.OutboxEventRepository;

import java.util.List;

@Repository
public interface SpringDataOutboxEventRepository
        extends OutboxEventRepository, CrudRepository<OutboxEvent, String> {

    @Override
    List<OutboxEvent> findByStatus(String status);
}
