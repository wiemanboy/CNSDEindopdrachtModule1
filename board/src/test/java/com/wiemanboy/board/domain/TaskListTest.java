package com.wiemanboy.board.domain;

import com.wiemanboy.board.builders.TaskBuilder;
import com.wiemanboy.board.builders.TaskListBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;
class TaskListTest {

    @Test
    @Description("Test if a task can be added to the task list")
    void addTask() {
        TaskList taskList = new TaskListBuilder().build();
        taskList.addTask(new TaskBuilder().build());

        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    @Description("Test if a task can be removed from the task list")
    void removeTask() {
        TaskList taskList = new TaskListBuilder().build();
        Task task = new TaskBuilder().build();
        taskList.addTask(task);
        taskList.removeTask(task);

        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    @Description("Test if a task can be retrieved by its id")
    void getTaskById() {
        Task task = new TaskBuilder().build();
        TaskList taskList = new TaskListBuilder().build();

        taskList.addTask(task);

        assertEquals(task, taskList.getTaskById(task.getId()));
    }
}