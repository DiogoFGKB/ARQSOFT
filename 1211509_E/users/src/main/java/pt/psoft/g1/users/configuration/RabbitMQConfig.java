package pt.psoft.g1.users.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "LMS";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // Queue where USERS listens for TEMP_READER_CREATED
    @Bean(name = "readerTempQueue")
    public Queue readerTempQueue() {
        return new AnonymousQueue();
    }

    @Bean(name = "readerPersistedQueue")
    public Queue readerPersistedQueue() {
        return new AnonymousQueue();
    }

    // Binding: USERS listens to TEMP_READER_CREATED
    @Bean
    public Binding readerTempBinding(DirectExchange directExchange,
                                     @Qualifier("readerTempQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(directExchange)
                .with("TEMP_READER_CREATED");
    }

    @Bean
    public Binding readerPersistedBinding(DirectExchange directExchange,
                                          @Qualifier("readerPersistedQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(directExchange)
                .with("TEMP_READER_PERSISTED");
    }


}
