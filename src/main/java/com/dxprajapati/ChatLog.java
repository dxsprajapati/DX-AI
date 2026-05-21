package com.dxprajapati;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_logs")
public class ChatLog {

    @Id
    private String id;

    private String userMessage;
    private String aiResponse;
    private LocalDateTime time;

    public ChatLog() {
    }

    public ChatLog(String userMessage, String aiResponse, LocalDateTime time) {
        this.userMessage = userMessage;
        this.aiResponse = aiResponse;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getAiResponse() {
        return aiResponse;
    }

    public void setAiResponse(String aiResponse) {
        this.aiResponse = aiResponse;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}