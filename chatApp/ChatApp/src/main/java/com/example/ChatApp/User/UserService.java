package com.example.ChatApp.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class UserService {

    private final UserRepository repository;
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
           repository.save(user);

    }
    public void disconnect(User user) {
        var storedUser= repository.findById(user.getNickname())
                .orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }

    }
    public List<User> findConnectedUsers() {
        return  repository.findAllByStatus(Status.ONLINE);

    }
}
