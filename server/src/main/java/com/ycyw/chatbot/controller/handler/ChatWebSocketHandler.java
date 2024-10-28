package com.ycyw.chatbot.controller.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Enregistrer le message dans la base de données
        // Diffuser le message aux utilisateurs connectés
    }
}
