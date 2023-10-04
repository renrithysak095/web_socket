package com.example.websocket.listener;

import com.example.websocket.model.Message;
import com.example.websocket.model.MessageType;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketMessageListener {

    private final SimpMessagingTemplate messagingTemplate;
    public WebSocketMessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = "groupQueue")
    public void receiveGroupMessage(Message message) {
        messagingTemplate.convertAndSend("/topic/group", message);
    }

    @RabbitListener(queues = "directQueue")
    public void receiveDirectMessage(Message message) {
        messagingTemplate.convertAndSendToUser(message.getReceiver(), "/queue/direct", message);
    }
}
