package com.wiemanboy.board.domain;

import com.wiemanboy.board.domain.exceptions.TaskNotFoundException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class TaskList extends DatabaseObject {
    private final String title;
    private final List<Task> tasks = new ArrayList<>();

    public TaskList(String title) {
        this.title = title;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Task getTaskById(UUID taskId) {
        return tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
