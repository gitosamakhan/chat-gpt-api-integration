package com.osama.chatgptintegration.controller;

import com.osama.chatgptintegration.client.ChatGPTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatGPTController {

    @Autowired
    private ChatGPTClient chatGPTClient;

    @PostMapping("/ask")
    public ResponseEntity<String> askChatGTP(@RequestBody String prompt) {
        return chatGPTClient.askGPT(prompt);
    }

}
