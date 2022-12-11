package xyz.silkdog.messagequeue.service.task;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.silkdog.messagequeue.code.ServiceCode;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewTask {
    private final static String TASK_QUEUE_NAME = "hello";


    public void createNewTask(String argv){
        String message = String.join(" ", argv);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // Auto-closable
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", TASK_QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
