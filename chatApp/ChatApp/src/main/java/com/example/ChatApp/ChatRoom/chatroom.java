package com.example.ChatApp.ChatRoom;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class chatroom {
    @Id
    private String id;
    private String chatroomId;
    private String senderId;
    private String recipientId;

}
