package com.example.websocket.controller;
import com.example.websocket.model.Message;
import com.example.websocket.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/chat")
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final ChatService chatService;

    @Autowired
    WebSocketController(SimpMessagingTemplate template, ChatService chatService){
        this.template = template;
        this.chatService = chatService;
    }

    @MessageMapping("/group")
    public void sendGroupMessage(@Payload Message message) {
        chatService.sendGroupMessage(message);
    }

    @MessageMapping("/direct")
    public void sendDirectMessage(@Payload Message message) {
        chatService.sendDirectMessage(message);
    }

}

