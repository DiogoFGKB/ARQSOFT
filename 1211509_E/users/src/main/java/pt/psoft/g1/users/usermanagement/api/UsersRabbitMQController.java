package pt.psoft.g1.users.usermanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pt.psoft.g1.users.usermanagement.dto.UserDTO;
import pt.psoft.g1.users.usermanagement.services.UserService;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class UsersRabbitMQController {

    private final UserService service;

    @RabbitListener(queues = "#{readerTempQueue.name}")
    public void consumeMessage_ReaderTempCreated(Message msg)
    {
        try
        {
            String jsonReceived = new String(msg.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received Reader Temp CREATED by AMQP.");

            ObjectMapper objectMapper = new ObjectMapper();
            UserDTO userDTO = objectMapper.readValue(jsonReceived, UserDTO.class);

            service.createUserTemp(userDTO);
        }
        catch (Exception ex)
        {
            System.out.println(" [x] Exception receiving Reader Temp CREATED event from AMQP: '" + ex.getMessage() + "'");
            ex.printStackTrace();
        }
    }

    @RabbitListener(queues = "#{readerPersistedQueue.name}")
    public void consumeMessage_ReaderTempPersisted(Message msg)
    {
        try
        {
            String readerId = new String(msg.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received TEMP_READER_PERSISTED by AMQP.");

            service.persistTemporary(readerId);
        }
        catch (Exception ex)
        {
            System.out.println(" [x] Exception receiving TEMP_READER_PERSISTED event from AMQP: '" + ex.getMessage() + "'");
            ex.printStackTrace();
        }
    }
}
