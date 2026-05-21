package com.dxprajapati;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private GroqService groqService;

//    @Autowired
//    private ChatLogRepository chatLogRepository;

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {

        String response = groqService.chat(request.getMessage());

//        ChatLog log = new ChatLog(
//                request.getMessage(),
//                response,
//                LocalDateTime.now()
//        );

        //chatLogRepository.save(log);

        return response;
    }

//    @GetMapping("/logs")
//    public Object getLogs() {
//        return chatLogRepository.findAll();
//    }

    @GetMapping("/ping")
    public String ping() {
        return "DX-AI Running";
    }
}