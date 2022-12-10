package xyz.silkdog.messagequeue.service.receive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.silkdog.messagequeue.code.ServiceCode;
import xyz.silkdog.messagequeue.config.OkhttpConfig;
import xyz.silkdog.messagequeue.dto.MarketingMessageDto;
import xyz.silkdog.messagequeue.util.MapperUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketingRecv {

    private final OkhttpConfig okhttpConfig;

    private final static String QUEUE_NAME = ServiceCode.Marketing.name();

    public void marketingRecv() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for marketing messages. To exit press CTRL+C");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                log.info("consumerTag: " + consumerTag + ", delivery: " + delivery);
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + message + "'");
                this.process(message);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }

    }

    private void process(String json) {
        MarketingMessageDto dto = MapperUtil.readAndParse(MarketingMessageDto.class, json);
        log.info("[process][INFO] dto: " + dto.toString());
        log.info("[process][INFO] okhttp result: " + okhttpConfig.call(json));
    }


}
