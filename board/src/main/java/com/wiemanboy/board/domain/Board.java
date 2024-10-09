package com.wiemanboy.board.domain;

import com.wiemanboy.board.domain.exceptions.TaskListNotFoundException;
import com.wiemanboy.board.domain.exceptions.TaskNotFoundException;
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

    public void moveTaskById(UUID taskId, UUID targetTaskListId) {
        moveTask(getTaskById(taskId), getTaskListById(targetTaskListId));
    }

    public void addTaskList(TaskList taskList) {
        taskLists.add(taskList);
    }

    public void addCollaborator(UUID collaboratorId) {
        collaboratorIds.add(collaboratorId);
    }

    public void addTaskToTaskList(TaskList taskList, Task task) {
        taskList.addTask(task);
    }

    public void addTaskToTaskListById(UUID taskListId, Task task) {
        addTaskToTaskList(getTaskListById(taskListId), task);
    }

    public Task getTaskById(UUID taskId) {
        return taskLists.stream()
                .map(taskList -> taskList.getTaskById(taskId))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public TaskList getTaskListById(UUID taskListId) {
        return taskLists.stream()
                .filter(taskList -> taskList.getId().equals(taskListId))
                .findFirst()
                .orElseThrow(() -> new TaskListNotFoundException(taskListId));
    }

    public void addTagToTask(UUID taskId, Tag tag) {
        Task task = getTaskById(taskId);
        task.addTag(tag);
    }
}
