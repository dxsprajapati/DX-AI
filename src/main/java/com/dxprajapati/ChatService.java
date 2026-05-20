package com.dxprajapati;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public ChatService() {
        this.webClient = WebClient.builder().build();
    }

    public String chat(String userText) {

        JSONObject request = new JSONObject();

        request.put("model", "gpt-4o-mini");

        JSONArray messages = new JSONArray();

        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful Gujarati assistant");

        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", userText);

        messages.put(systemMessage);
        messages.put(userMessage);

        request.put("messages", messages);

        String response = webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request.toString())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject json = new JSONObject(response);

        return json.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }
}