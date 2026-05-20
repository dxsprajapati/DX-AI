package com.dxprajapati;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public GroqService() {
        this.webClient = WebClient.builder().build();
    }
    private JSONArray conversationHistory = new JSONArray();
    
    public String chat(String userMessage) {

        try {

            WebClient webClient = WebClient.builder().build();

            JSONObject request = new JSONObject();

            request.put("model", "llama-3.3-70b-versatile");

            // First time system prompt add
            if (conversationHistory.length() == 0) {

                JSONObject system = new JSONObject();

                system.put("role", "system");

                system.put("content",
                        "You are a smart Gujarati AI assistant. " +
                        "Always reply in Gujarati language only. " +
                        "Remember previous conversation context. " +
                        "Reply naturally like a human friend.");

                conversationHistory.put(system);
            }

            // User message
            JSONObject user = new JSONObject();

            user.put("role", "user");
            user.put("content", userMessage);

            conversationHistory.put(user);

            // Send full history
            request.put("messages", conversationHistory);

            String response = webClient.post()
                    .uri("https://api.groq.com/openai/v1/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(request.toString())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JSONObject json = new JSONObject(response);

            String botReply = json.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            // Save assistant reply also
            JSONObject assistant = new JSONObject();

            assistant.put("role", "assistant");
            assistant.put("content", botReply);

            conversationHistory.put(assistant);

            return botReply;

        } catch (Exception e) {

            e.printStackTrace();

            return "અત્યારે AI service ઉપલબ્ધ નથી 😔";
        }
    }
}