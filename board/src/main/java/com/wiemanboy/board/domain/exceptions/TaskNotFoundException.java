package com.wiemanboy.board.domain.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TaskNotFoundException extends RuntimeException {
  private final UUID taskId;

    public TaskNotFoundException(UUID taskId) {
        super(String.format("Task not found with id: %s", taskId));
        this.taskId = taskId;
    }
}
