package pt.psoft.g1.users.usermanagement.infrastructure.outbox;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pt.psoft.g1.users.usermanagement.model.OutboxEvent;
import pt.psoft.g1.users.usermanagement.publishers.UserEventsPublisher;
import pt.psoft.g1.users.usermanagement.repositories.OutboxEventRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutboxPublisherWorker {

    private final OutboxEventRepository outboxRepo;
    private final UserEventsPublisher publisher;

    @Scheduled(fixedDelay = 2000)
    public void publishPendingEvents() {

        List<OutboxEvent> events = outboxRepo.findByStatus("NEW");

        for (OutboxEvent event : events) {

            System.out.println("🔥 USERS OUTBOX EVENT FOUND:");
            System.out.println("   ID:          " + event.getId());
            System.out.println("   TYPE:        " + event.getType());
            System.out.println("   STATUS:      " + event.getStatus());
            System.out.println("   AGGREGATE ID:" + event.getAggregateId());
            System.out.println("   PAYLOAD:     " + event.getPayload());
            System.out.println("---------------------------------------------");

            try {
                switch (event.getType()) {

                    case "TEMP_USER_CREATED":
                        publisher.publishTempUserCreated(event.getPayload());
                        break;

                    case "TEMP_USER_PERSISTED":
                        // For example, notify other services, or just mark as sent
                        System.out.println("[Outbox] TEMP_USER_PERSISTED received: " + event.getPayload());
                        break;


                    default:
                        System.out.println("⚠ Unknown event type: " + event.getType());
                        continue;
                }

                event.setStatus("SENT");
                outboxRepo.save(event);

            } catch (Exception e) {
                System.out.println("❌ Failed to publish Outbox event: " + event.getId());
            }
        }
    }
}
