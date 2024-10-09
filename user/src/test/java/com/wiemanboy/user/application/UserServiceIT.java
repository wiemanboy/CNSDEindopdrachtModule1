package com.wiemanboy.user.application;

import com.wiemanboy.user.data.UserRepository;
import com.wiemanboy.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceIT {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private User savedUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        savedUser = userRepository.save(new User("John"));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void registerUser() {
        User user = userService.registerUser("Jane");
        assertNotNull(user.getId());
        assertEquals("Jane", user.getUsername());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(savedUser.getId());
        assertFalse(userRepository.existsById(savedUser.getId()));
    }

    @Test
    void updateUser() {
        User user = userService.updateUser(savedUser.getId(), "Jane");
        assertEquals("Jane", user.getUsername());
    }

    @Test
    void getUser() {
        User user = userService.getUser(savedUser.getId());
        assertEquals("John", user.getUsername());
    }

    @Test
    void getUsers() {
        assertEquals(1, userService.getUsers().size());
    }

    @Test
    void userExists() {
        assertTrue(userService.userExists(savedUser.getId()));
    }
}