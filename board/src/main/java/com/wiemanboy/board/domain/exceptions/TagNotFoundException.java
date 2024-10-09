package com.wiemanboy.board.domain.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TagNotFoundException extends RuntimeException {
  private final UUID tagId;

    public TagNotFoundException(UUID tagId) {
        super(String.format("Tag not found with id: %s", tagId));
        this.tagId = tagId;
    }
}
