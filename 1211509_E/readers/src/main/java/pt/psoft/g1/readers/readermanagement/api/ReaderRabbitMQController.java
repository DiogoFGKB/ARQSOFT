package pt.psoft.g1.readers.readermanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import pt.psoft.g1.readers.readermanagement.dto.UserDTO;
import pt.psoft.g1.readers.readermanagement.services.ReaderService;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class ReaderRabbitMQController {

    private final ReaderService readerService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "#{userTempQueue.name}")
    public void consumeMessage_UserTemp(Message msg) {
        try {
            String json = new String(msg.getBody(), StandardCharsets.UTF_8);
            UserDTO dto = objectMapper.readValue(json, UserDTO.class);
            System.out.println(" [x] Received TEMP_USER_CREATED event: " + dto);
            readerService.persistTemporary(dto);
        } catch (Exception ex) {
            System.err.println("Error processing TEMP_USER_CREATED event: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}