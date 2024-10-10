package com.wiemanboy.board.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ApiUserService implements UserService {
    @Value("${user.baseURL}")
    private String url;

    private final RestTemplate restTemplate;

    public ApiUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Boolean validateUser(UUID id) {
        return restTemplate.getForObject(String.format("%s/%s/exists", url, id) , Boolean.class);
    }
}
