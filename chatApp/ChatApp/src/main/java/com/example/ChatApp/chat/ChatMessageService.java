package com.example.ChatApp.chat;

import com.example.ChatApp.ChatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save (ChatMessage chatMessage) {
        var chatId=chatRoomService.getChatRoomId(chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                true).orElseThrow();
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;

    }
    public List<ChatMessage> findChatMessages(String senderId, String receiverId) {
        var chatId=chatRoomService.getChatRoomId(senderId,receiverId,false);
        return chatId.map(chatMessageRepository::findByChatId).orElse((new ArrayList<>()));
    }
}
