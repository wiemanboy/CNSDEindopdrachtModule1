package com.wiemanboy.board.builders;

import com.wiemanboy.board.domain.TaskList;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class TaskListBuilder {
    private String title = "Task List Title";

    public TaskList build() {
        return new TaskList(title);
    }
}
