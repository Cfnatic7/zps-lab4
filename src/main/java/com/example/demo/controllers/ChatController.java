package com.example.demo.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessageToAll")
    @SendTo("/topic/public")
    public String sendMessageToAll(String message) {
        return message;
    }

    @MessageMapping("/sendMessageToRoom")
    public void sendMessageToRoom(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/rooms/" + chatMessage.getRoomId(),
                chatMessage.getMessage());
    }

    @MessageMapping("/sendPrivateMessage")
    public void sendPrivateMessage(@Payload PrivateMessage privateMessage) {
        messagingTemplate.convertAndSend("/queue/user/" + privateMessage.getReceiver(),
                privateMessage.getMessage());
    }

    @Data
    public static class ChatMessage {
        private String roomId;
        private String message;
    }

    @Data
    public static class PrivateMessage {
        private String sender;
        private String receiver;
        private String message;
    }
}
