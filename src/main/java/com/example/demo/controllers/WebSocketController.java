package com.example.demo.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.example.demo.dtos.MessageDto;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send/message")
    public void sendMessage(MessageDto messageDTO, HttpSession session) {
        session.setAttribute("global", "global");
        messagingTemplate.convertAndSend("/topic/messages", messageDTO.getMessage());
    }

    @MessageMapping("/send/message-to-user")
    public void sendMessageToUser(MessageDto messageDTO, HttpSession session) {
        session.setAttribute("user", "user");
        messagingTemplate.convertAndSendToUser(messageDTO.getSessionId(),
                "/queue/messages", messageDTO.getMessage());
    }
}
