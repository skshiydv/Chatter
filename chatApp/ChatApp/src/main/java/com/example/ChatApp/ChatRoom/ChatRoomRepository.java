package com.example.ChatApp.ChatRoom;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<chatroom,String>{

    Optional<chatroom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
