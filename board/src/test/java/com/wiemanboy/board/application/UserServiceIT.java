package com.wiemanboy.board.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIT {
    @Autowired
    private UserServiceImplementation userService;
    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void validateUser() {
        UUID randomUUID = UUID.randomUUID();

        mockServer.expect(requestTo("http://localhost:8080/api/users/" + randomUUID + "/exists"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON));

        Boolean actual = userService.validateUser(randomUUID);
        assertTrue(actual);

        mockServer.verify();
    }
}
