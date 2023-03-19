package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.session.Session;
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSessionWebSocketMessageBrokerConfigurer<Session> {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue"); // Add "/queue" for user-specific destinations
        config.setUserDestinationPrefix("/user"); // Add the user-specific destination prefix
        config.enableSimpleBroker("/topic"); // Enables a simple in-memory message broker for subscription endpoints
        config.setApplicationDestinationPrefixes("/app"); // Set the prefix for message-mapping endpoints
    }

    @Override
    public void configureStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-endpoint").withSockJS(); // Register a STOMP over WebSocket endpoint
    }
}
