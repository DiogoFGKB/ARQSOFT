package pt.psoft.g1.readers.configuration;

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


    @Bean(name = "userTempQueue")
    public Queue userTempQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding userTempBinding(DirectExchange directExchange,
                                   @Qualifier("userTempQueue") Queue queue) {
        return BindingBuilder.bind(queue)
                .to(directExchange)
                .with("TEMP_USER_CREATED"); }
}
