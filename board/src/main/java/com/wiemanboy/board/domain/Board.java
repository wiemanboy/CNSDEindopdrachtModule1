package com.wiemanboy.board.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Board extends DatabaseObject {
    private final String title;
    private final List<UUID> collaboratorIds = new ArrayList<>();
    private final List<TaskList> taskLists = new ArrayList<>();

    public Board(String title) {
        this.title = title;
    }

    public void moveTask(Task task, TaskList targetTaskList) {
        taskLists.forEach(taskList -> {
            if (taskList.getTasks().contains(task)) {
                taskList.removeTask(task);
                targetTaskList.addTask(task);
            }
        });
    }

    public void addTaskList(TaskList taskList) {
        taskLists.add(taskList);
    }

    public void addCollaborator(UUID collaboratorId) {
        collaboratorIds.add(collaboratorId);
    }
}
