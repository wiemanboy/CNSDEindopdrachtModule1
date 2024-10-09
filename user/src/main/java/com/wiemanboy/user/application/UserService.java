package com.wiemanboy.user.application;

import com.wiemanboy.user.data.UserRepository;
import com.wiemanboy.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(String username) {
        return userRepository.save(new User(username));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UUID id, String username) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setUsername(username);
        return user;
    }

    public User getUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Boolean userExists(UUID id) {
        return userRepository.existsById(id);
    }
}
