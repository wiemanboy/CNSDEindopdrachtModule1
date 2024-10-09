package com.wiemanboy.user.presentation;

import com.wiemanboy.user.application.UserService;
import com.wiemanboy.user.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(String username) {
        return userService.registerUser(username);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, String username) {
        return userService.updateUser(id, username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
       return userService.getUser(id);
    }

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}/exists")
    public boolean userExists(@PathVariable UUID id) {
        return userService.userExists(id);
    }
}
