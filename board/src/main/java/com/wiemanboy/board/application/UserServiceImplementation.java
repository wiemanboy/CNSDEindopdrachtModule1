package com.wiemanboy.board.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImplementation implements UserService {
    @Value("${user.baseURL}")
    private String url;

    private final RestTemplate restTemplate;

    public UserServiceImplementation(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Boolean validateUser(String id) {
        return restTemplate.getForObject(url + id, Boolean.class);
    }
}
