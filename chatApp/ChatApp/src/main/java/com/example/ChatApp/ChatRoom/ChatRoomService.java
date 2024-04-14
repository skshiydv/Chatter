package com.example.ChatApp.ChatRoom;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    public Optional<String> getChatRoomId(String senderId,
                                          String receiverId,
                                          boolean createChatRoomIfNotExist){
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,receiverId)
            .map(chatroom::getChatroomId)
            .or(()->{
                if (createChatRoomIfNotExist){
                    var chatId=createChat(senderId,receiverId);
                    return Optional.of(chatId);

                }
                return Optional.empty();

            });

    }
    private String createChat (String senderId , String recipientId){
        var chatId=String.format("%s_%s",senderId,recipientId);
        chatroom senderRecipient = chatroom.builder()
                .chatroomId(chatId)
                .senderId(senderId)
                .recipientId(recipientId).build();
        chatroom recipientSender = chatroom.builder()
                .chatroomId(chatId)
                .senderId(recipientId)
                .recipientId(senderId).build();
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return  chatId;
    }
}
