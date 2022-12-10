package xyz.silkdog.messagequeue;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.silkdog.messagequeue.service.receive.Recv;

@SpringBootApplication
@RequiredArgsConstructor
public class MessageQueueApplication {
    private final Recv recv;

    public static void main(String[] args) {
        SpringApplication.run(MessageQueueApplication.class, args);
    }

    @PostConstruct
    public void onstart(){
        recv.receive();
    }

}
