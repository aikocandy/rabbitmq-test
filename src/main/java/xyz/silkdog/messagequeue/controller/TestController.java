package xyz.silkdog.messagequeue.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.silkdog.messagequeue.service.send.Send;
import xyz.silkdog.messagequeue.service.task.NewTask;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/send")
public class TestController {
    private final static String SUCCESS = "success";
    private final Send send;
    private final NewTask newTask;

    @PostMapping
    public ResponseEntity<?> sendTest(@RequestBody String message){
        if(StringUtils.isEmpty(message)){
            throw new RuntimeException("message not found");
        }
        assert(message.length() > 0 && message.length() <= 1000);
        send.send(message);
        return ResponseEntity.ok(SUCCESS);
    }

    @GetMapping("new-task")
    public ResponseEntity<?> createNewTask(String message){
        newTask.createNewTask(message);
        return ResponseEntity.ok(SUCCESS);
    }

}
