package pt.psoft.g1.readers.readermanagement.infraestructure.repositories.impl;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.psoft.g1.readers.readermanagement.model.OutboxEvent;
import pt.psoft.g1.readers.readermanagement.repositories.OutboxEventRepository;

import java.util.List;

@Repository
public interface SpringDataOutboxEventRepositoryImpl extends OutboxEventRepository, CrudRepository<OutboxEvent, Long> {

    @Override
    @Query("SELECT e FROM OutboxEvent e WHERE e.status = :status")
    List<OutboxEvent> findByStatus(@Param("status") String status);

    // save() and deleteById() come from CrudRepository
}
