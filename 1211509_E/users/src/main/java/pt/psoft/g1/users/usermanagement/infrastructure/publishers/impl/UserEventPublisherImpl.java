package pt.psoft.g1.users.usermanagement.infrastructure.publishers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import pt.psoft.g1.users.usermanagement.publishers.UserEventsPublisher;

@Component
@RequiredArgsConstructor
public class UserEventPublisherImpl implements UserEventsPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    private static final String ROUTING_KEY = "TEMP_USER_CREATED";

    @Override
    public void publishTempUserCreated(String payload) {
        try {
            System.out.println(" [x] Publishing " + ROUTING_KEY + " event to exchange: " + directExchange.getName());

            // convertAndSend handles JSON conversion automatically
            rabbitTemplate.convertAndSend(directExchange.getName(), ROUTING_KEY, payload);

        } catch (Exception e) {
            System.err.println("Error publishing message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
