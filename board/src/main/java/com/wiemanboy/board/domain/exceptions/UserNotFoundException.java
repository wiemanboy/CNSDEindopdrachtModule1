package com.wiemanboy.board.domain.exceptions;

import lombok.Getter;

import java.util.UUID;
@Getter
public class UserNotFoundException extends RuntimeException {

    private final UUID userId;

    public UserNotFoundException(UUID userId) {
        super(String.format("User not found with id: %s", userId));
        this.userId = userId;
    }
}
