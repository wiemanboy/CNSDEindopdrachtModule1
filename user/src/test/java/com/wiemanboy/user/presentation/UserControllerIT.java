package com.wiemanboy.user.presentation;

import com.wiemanboy.user.data.UserRepository;
import com.wiemanboy.user.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static javax.swing.text.html.FormSubmitEvent.MethodType.POST;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerIT {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    private User savedUser;

    private final String baseUrl = "/api/users/";

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        savedUser = userRepository.save(new User("John"));
        userRepository.save(new User("Jane"));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void registerUser() {
        ResponseEntity<User> response = restTemplate.postForEntity(baseUrl + "?username=Henk", null, User.class);
        assertEquals("Henk", Objects.requireNonNull(response.getBody()).getUsername());
    }

    @Test
    void updateUser() {
        ResponseEntity<User> response = restTemplate.exchange(baseUrl + savedUser.getId() + "?username=Jake", HttpMethod.PUT, null, User.class);
        assertEquals("Jake", Objects.requireNonNull(response.getBody()).getUsername());
    }

    @Test
    void deleteUser() {
        restTemplate.delete(baseUrl + savedUser.getId());
        assertFalse(userRepository.existsById(savedUser.getId()));
    }

    @Test
    void getUser() {
        ResponseEntity<User> response = restTemplate.getForEntity(baseUrl + savedUser.getId(), User.class);
        assertEquals("John", Objects.requireNonNull(response.getBody()).getUsername());
    }

    @Test
    void getUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(baseUrl , User[].class);
        assertEquals(2, Objects.requireNonNull(response.getBody()).length);
    }

    @Test
    void userExists() {
        assertTrue(restTemplate.getForObject(baseUrl + savedUser.getId() + "/exists" , Boolean.class));
    }
}