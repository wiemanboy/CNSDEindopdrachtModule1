package com.wiemanboy.board.builders;

import com.wiemanboy.board.domain.Task;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class TaskBuilder {
    private String title = "Task Title";
    private String description = "Task Description";

    public Task build() {
        return new Task(title, description);
    }
}
