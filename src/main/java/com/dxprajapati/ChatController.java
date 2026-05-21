package com.dxprajapati;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private GroqService groqService;

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {

        return groqService.chat(request.getMessage());
    }
    
    @GetMapping("/ping")
    public String ping() {
        return "DX-AI Running";
    }
}