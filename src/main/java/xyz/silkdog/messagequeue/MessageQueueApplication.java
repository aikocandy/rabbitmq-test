package xyz.silkdog.messagequeue;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.silkdog.messagequeue.service.receive.GeneralRecv;
import xyz.silkdog.messagequeue.service.receive.MarketingRecv;

@SpringBootApplication
@RequiredArgsConstructor
public class MessageQueueApplication {
    private final GeneralRecv generalRecv;
    private final MarketingRecv marketingRecv;

    public static void main(String[] args) {
        SpringApplication.run(MessageQueueApplication.class, args);
    }

    @PostConstruct
    public void onstart(){
        generalRecv.receive();
        marketingRecv.marketingRecv();
    }

}
