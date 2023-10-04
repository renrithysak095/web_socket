package com.example.websocket.service;

import com.example.websocket.model.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendGroupMessage(Message message) {
        messagingTemplate.convertAndSend("/topic/group", message);
        rabbitTemplate.convertAndSend("groupQueue", message);
    }

    public void sendDirectMessage(Message message) {
        messagingTemplate.convertAndSendToUser(message.getReceiver(), "/queue/direct", message);
        rabbitTemplate.convertAndSend("directQueue", message);
    }
}

