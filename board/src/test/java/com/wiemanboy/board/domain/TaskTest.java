package com.wiemanboy.board.domain;

import com.wiemanboy.board.builders.TagBuilder;
import com.wiemanboy.board.builders.TaskBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    @Description("Test if a tag can be added to the task")
    void addTag() {
        Task task = new TaskBuilder().build();
        task.addTag(new TagBuilder().build());

        assertEquals(1, task.getTags().size());
    }

    @Test
    @Description("Test if a collaborator can be added to the task")
    void addCollaborator() {
        Task task = new TaskBuilder().build();
        task.addCollaborator(UUID.randomUUID());

        assertEquals(1, task.getCollaboratorIds().size());
    }
}