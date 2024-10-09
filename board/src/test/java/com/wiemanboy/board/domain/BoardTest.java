package com.wiemanboy.board.domain;

import com.wiemanboy.board.builders.BoardBuilder;
import com.wiemanboy.board.builders.TagBuilder;
import com.wiemanboy.board.builders.TaskBuilder;
import com.wiemanboy.board.builders.TaskListBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    @Description("Test if a task can be moved from one task list to another")
    void moveTask() {
        Board board = new BoardBuilder().build();
        TaskList taskList1 = new TaskListBuilder().build();
        TaskList taskList2 = new TaskListBuilder().build();
        Task task = new TaskBuilder().build();
        taskList1.addTask(task);
        board.addTaskList(taskList1);
        board.addTaskList(taskList2);

        board.moveTask(task, taskList2);

        assertEquals(0, taskList1.getTasks().size());
        assertEquals(1, taskList2.getTasks().size());
    }

    @Test
    @Description("Test if a task can be moved from one task list to another when the task is not present in any task list")
    void moveTaskNotPresent() {
        Board board = new BoardBuilder().build();
        TaskList taskList1 = new TaskListBuilder().build();
        TaskList taskList2 = new TaskListBuilder().build();
        Task task = new TaskBuilder().build();
        board.addTaskList(taskList1);
        board.addTaskList(taskList2);

        board.moveTask(task, taskList2);

        assertEquals(0, taskList1.getTasks().size());
        assertEquals(0, taskList2.getTasks().size());
    }

    @Test
    @Description("Test if a task list can be added to the board")
    void addTaskList() {
        Board board = new BoardBuilder().build();

        board.addTaskList(new TaskListBuilder().build());

        assertEquals(1, board.getTaskLists().size());
    }

    @Test
    @Description("Test if a collaborator can be added to the board")
    void addCollaborator() {
        Board board = new BoardBuilder().build();

        board.addCollaborator(UUID.randomUUID());

        assertEquals(1, board.getCollaboratorIds().size());
    }

    @Test
    @Description("Test if a task can be retrieved by its id")
    void getTaskById() {
        Board board = new BoardBuilder().build();
        Task task = new TaskBuilder().build();
        TaskList taskList = new TaskListBuilder().build();
        taskList.addTask(task);
        board.addTaskList(taskList);

        assertEquals(task, board.getTaskById(task.getId()));
    }

    @Test
    @Description("Test if a task list can be retrieved by its id")
    void getTaskListById() {
        Board board = new BoardBuilder().build();
        TaskList taskList = new TaskListBuilder().build();
        board.addTaskList(taskList);

        assertEquals(taskList, board.getTaskListById(taskList.getId()));
    }

    @Test
    @Description("Test if a task can be added to a task list")
    void addTaskToTaskList() {
        Board board = new BoardBuilder().build();
        Task task = new TaskBuilder().build();
        TaskList taskList = new TaskListBuilder().build();
        board.addTaskList(taskList);

        board.addTaskToTaskList(taskList, task);

        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    @Description("Test if a task can be added to a task list by their ids")
    void addTaskToTaskListById() {
        Board board = new BoardBuilder().build();
        Task task = new TaskBuilder().build();
        TaskList taskList = new TaskListBuilder().build();
        board.addTaskList(taskList);

        board.addTaskToTaskListById(taskList.getId(), task);

        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    @Description("Test if a task can be moved from one task list to another by their ids")
    void moveTaskById() {
        Board board = new BoardBuilder().build();
        TaskList taskList1 = new TaskListBuilder().build();
        TaskList taskList2 = new TaskListBuilder().build();
        Task task = new TaskBuilder().build();
        taskList1.addTask(task);
        board.addTaskList(taskList1);
        board.addTaskList(taskList2);

        board.moveTaskById(task.getId(), taskList2.getId());

        assertEquals(0, taskList1.getTasks().size());
        assertEquals(1, taskList2.getTasks().size());
    }

    @Test
    @Description("Test if a tag can be added to a task")
    void addTagToTask() {
        Board board = new BoardBuilder().build();
        Task task = new TaskBuilder().build();
        TaskList taskList = new TaskListBuilder().build();
        board.addTaskList(taskList);
        taskList.addTask(task);
        Tag tag = new TagBuilder().build();

        board.addTagToTask(task.getId(), tag);

        assertEquals(1, task.getTags().size());
    }
}