package com.cbn.springai.controller;

import com.cbn.springai.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class ChatController {
    final ChatService chatService;

    @PostMapping("/completion")
    public Object getResponse(@RequestBody String prompt) {
        return chatService.completation(prompt);
    }

    @PostMapping("/chat")
    public Object getChatResponse(@RequestBody String message) {
        return chatService.chat(message);
    }

    @PostMapping("/image")
    public Object getImageResponse(@RequestBody String promt) {
        return chatService.image(promt);
    }
}
