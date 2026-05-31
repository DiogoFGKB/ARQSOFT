package pt.psoft.g1.readers.readermanagement.infraestructure.publishers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import pt.psoft.g1.readers.configuration.RabbitMQConfig;
import pt.psoft.g1.readers.readermanagement.publishers.ReaderEventsPublisher;

@Component
@RequiredArgsConstructor
public class ReaderEventPublisherImpl implements ReaderEventsPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    private static final String ROUTING_KEY_READER_PERSISTED = "TEMP_READER_PERSISTED";


    private static final String ROUTING_KEY = "TEMP_READER_CREATED";

    @Override
    public void publishReaderTempCreatedEvent(String payload) {
        try {
            System.out.println(" [x] Publishing " + ROUTING_KEY + " event to exchange: " + directExchange.getName());
            
            // Use convertAndSend which will handle message conversion automatically
            rabbitTemplate.convertAndSend(directExchange.getName(), ROUTING_KEY, payload);
            
        } catch (Exception e) {
            System.err.println("Error publishing message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void publishReaderPersistedEvent(String payload) {
        try {
            System.out.println(" [x] Publishing " + ROUTING_KEY_READER_PERSISTED +
                    " event to exchange: " + directExchange.getName());

            rabbitTemplate.convertAndSend(
                    directExchange.getName(),
                    ROUTING_KEY_READER_PERSISTED,
                    payload
            );

        } catch (Exception e) {
            System.err.println("Error publishing TEMP_READER_PERSISTED: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
