package com.ycyw.chatbot.controller;

import com.ycyw.chatbot.dto.MessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/send") // Correspond à l'URL que tu veux utiliser pour envoyer des messages
    @SendTo("/topic/messages") // Lieu où les messages seront envoyés
    public MessageDTO send(@Payload MessageDTO messageDTO) {
        return messageDTO;
    }
}
