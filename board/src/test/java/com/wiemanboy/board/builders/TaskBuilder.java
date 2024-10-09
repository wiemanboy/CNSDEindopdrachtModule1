package com.wiemanboy.board.builders;

import com.wiemanboy.board.domain.Task;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Accessors(chain = true)
public class TaskBuilder {
    private UUID id = UUID.randomUUID();
    private String title = "Task Title";
    private String description = "Task Description";

    public Task build() {
        Task task = new Task(title, description);
        task.setId(id);
        return task;
    }
}
