package com.wiemanboy.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@Setter
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    private UUID id;

    private String username;

    public User(String username) {
        this.username = username;
    }

    public User() {

    }
}
