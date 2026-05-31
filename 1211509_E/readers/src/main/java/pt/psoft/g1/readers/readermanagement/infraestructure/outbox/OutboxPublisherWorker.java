package pt.psoft.g1.readers.readermanagement.infraestructure.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pt.psoft.g1.readers.readermanagement.model.OutboxEvent;
import pt.psoft.g1.readers.readermanagement.publishers.ReaderEventsPublisher;
import pt.psoft.g1.readers.readermanagement.repositories.OutboxEventRepository;
import pt.psoft.g1.readers.shared.model.ReaderEvents;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxPublisherWorker {

    private final OutboxEventRepository outboxRepo;
    private final ReaderEventsPublisher publisher;

    @Scheduled(fixedDelay = 2000)
    public void publishPendingEvents() {

        List<OutboxEvent> events = outboxRepo.findByStatus("NEW");
        for (OutboxEvent event : events) {
            System.out.println("🔥 OUTBOX EVENT FOUND:");
            System.out.println(" ID: " + event.getId());
            System.out.println(" TYPE: " + event.getEventType());
            System.out.println(" STATUS: " + event.getStatus());
            System.out.println(" AGGREGATE ID:" + event.getAggregateId());
            System.out.println(" PAYLOAD: " + event.getPayload());
            System.out.println("---------------------------------------------");
            try {
                switch (event.getEventType()) {
                    case ReaderEvents.TEMP_READER_CREATED:
                        publisher.publishReaderTempCreatedEvent(event.getPayload());
                        break;

                    case ReaderEvents.TEMP_READER_PERSISTED:
                        publisher.publishReaderPersistedEvent(event.getPayload());
                        break;

                    /*case ReaderEvents.READER_CREATED:
                        publisher.publishReaderCreatedEvent(event.getPayload());
                        break;

                    case ReaderEvents.READER_UPDATED:
                        publisher.publishReaderUpdatedEvent(event.getPayload());
                        break;

                    case ReaderEvents.READER_DELETED:
                        publisher.publishReaderDeletedEvent(event.getPayload());
                        break;
                     */
                }

                event.setStatus("SENT");
                outboxRepo.save(event);

            } catch (Exception e) {
                System.out.println("Failed to publish Outbox event: " + event.getId());
            }
        }

    }
}

