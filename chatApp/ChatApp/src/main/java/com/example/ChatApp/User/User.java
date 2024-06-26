package com.example.ChatApp.User;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter

@Document
public class User {
    @Id
    private String nickname;
    private String fullName;
    private Status status;
}
