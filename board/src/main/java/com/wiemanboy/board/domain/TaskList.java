package com.wiemanboy.board.domain;

import com.wiemanboy.board.domain.exceptions.TaskNotFoundException;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class TaskList extends DatabaseObject {
    private String title;
    @OneToMany
    private final List<Task> tasks = new ArrayList<>();

    public TaskList(String title) {
        this.title = title;
    }

    protected TaskList() {
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
