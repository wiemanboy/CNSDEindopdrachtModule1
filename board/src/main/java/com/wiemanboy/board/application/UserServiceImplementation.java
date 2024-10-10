package com.wiemanboy.board.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {
    @Value("${user.baseURL}")
    private String url;

    private final RestTemplate restTemplate;

    public UserServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Boolean validateUser(UUID id) {
        return restTemplate.getForObject(url + id + "/exists" , Boolean.class);
    }
}
