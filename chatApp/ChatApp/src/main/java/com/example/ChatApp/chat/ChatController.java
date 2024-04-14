package com.example.ChatApp.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    @MessageMapping("/chat")
    public  void  processMessage(@Payload ChatMessage chatMessage){
        ChatMessage savedMessage=chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(chatMessage.getReceiverId(),"/queue/messages",ChatNotification.builder().id(savedMessage.getId()).senderId(savedMessage.getSenderId()).recipientId(savedMessage.getReceiverId()).content(savedMessage.getContent()).build());
    }
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable ("recipientId") String recipientId
    ){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }
}
