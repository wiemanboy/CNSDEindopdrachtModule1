package com.wiemanboy.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User {
    @Id()
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String username;

    public User(String username) {
        this.username = username;
    }
}
