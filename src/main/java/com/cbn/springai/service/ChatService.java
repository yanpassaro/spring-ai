package com.cbn.springai.service;

import com.cbn.springai.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ChatService {
    public String completation(String prompt) {
        ChatBotTemplate chatBotTemplate = new ChatBotTemplate(
                Map.of("model", "text-davinci-003", "max_tokens", 100, "prompt", prompt));
        chatBotTemplate.executeQuery("https://api.openai.com/v1/completions");
        return chatBotTemplate.getCompletationResponse();
    }

    public String chat(String message) {
        ChatBotTemplate chatBotTemplate = new ChatBotTemplate(
                Map.of("model", "gpt-3.5-turbo", "messages", List.of(new Message("user", message))));
        chatBotTemplate.executeQuery("https://api.openai.com/v1/chat/completions");
        return chatBotTemplate.getChatResponse();
    }

    public Object image(String prompt) {
        ChatBotTemplate chatBotTemplate = new ChatBotTemplate(
                Map.of("prompt", prompt));
        chatBotTemplate.executeQuery("https://api.openai.com/v1/images/generations");
        return chatBotTemplate.getImageResponse();
    }
}
