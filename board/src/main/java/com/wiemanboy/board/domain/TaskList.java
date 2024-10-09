package com.wiemanboy.board.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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
}
