package com.dxprajapati;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
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
    
    @GetMapping("/logs")
    public ResponseEntity<InputStreamResource> downloadLogs() throws Exception {

        File file = new File("chat-log.txt");

        InputStreamResource resource =
                new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=chat-log.txt")
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(file.length())
                .body(resource);
    }
}