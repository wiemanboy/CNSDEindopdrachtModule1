package com.wiemanboy.user.presentation;

import com.wiemanboy.user.application.UserService;
import com.wiemanboy.user.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * @param username - name of the user
     * @return - the user
     */
    @PostMapping("/")
    public User registerUser(String username) {
        return userService.registerUser(username);
    }
    /**
     * Update user by UUID.
     *
     * @param id   the UUID
     * @param username the name
     * @return the user
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, String username) {
        return userService.updateUser(id, username);
    }

    /**
     * Delete a user by id
     * @param id - UUID of the user
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
    /**
     * Get a user by UUID
     * @param id - UUID of the user
     * @return - the user
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
       return userService.getUser(id);
    }
    /**
     * Get all users
     * @return - list of users
     */
    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    /**
     * Check if a user exists
     * @param id - UUID of the user
     * @return - boolean
     */
    @GetMapping(value = "/{id}/exists")
    public Boolean userExists(@PathVariable UUID id) {
        return userService.userExists(id);
    }
}
