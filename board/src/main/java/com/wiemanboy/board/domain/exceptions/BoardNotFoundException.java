package com.wiemanboy.board.domain.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BoardNotFoundException extends RuntimeException {
    private final UUID boardId;

    public BoardNotFoundException(UUID boardId) {
        super(String.format("Board not found with id: %s", boardId));
        this.boardId = boardId;
    }
}
