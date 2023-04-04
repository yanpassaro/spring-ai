package com.cbn.springai.service;

import com.cbn.springai.domain.ChatResponse;
import com.cbn.springai.domain.CompletationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.http.HttpMethod.POST;

@Component
public class ChatBotTemplate {
    private final HttpEntity<Map<?, ?>> entity;
    private ResponseEntity<Object> responseEntity;

    public ChatBotTemplate(Map<String, ?> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer k");
        entity = new HttpEntity<>(body, headers);
    }

    public void executeQuery(String url) {
        responseEntity = new RestTemplate().exchange(url, POST, entity, Object.class);
    }

    public String getChatResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<?, ?> filterMessage = objectMapper.convertValue(filterChoices().get(0), Map.class);
        return objectMapper.convertValue(filterMessage.get("message"), ChatResponse.class).getContent();
    }

    public String getCompletationResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(filterChoices().get(0), CompletationResponse.class).getText();
    }

    public Object getImageResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<?, ?> filterImage = objectMapper.convertValue(responseEntity.getBody(), Map.class);
        ArrayList<?> filterData = objectMapper.convertValue(filterImage.get("data"), ArrayList.class);
        Map<?, ?> filterUri = objectMapper.convertValue(filterData.get(0), Map.class);
        return filterUri.get("url");
    }

    private ArrayList<?> filterChoices() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<?, ?> filterChoices = objectMapper.convertValue(responseEntity.getBody(), Map.class);
        return objectMapper.convertValue(filterChoices.get("choices"), ArrayList.class);
    }
}
