package com.wiemanboy.board.domain.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TaskListNotFoundException extends RuntimeException {
  private final UUID taskListId;

    public TaskListNotFoundException(UUID taskListId) {
        super(String.format("TaskList not found with id: %s", taskListId));
        this.taskListId = taskListId;
    }
}
