package com.wiemanboy.board.builders;

import com.wiemanboy.board.domain.TaskList;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Setter
@Accessors(chain = true)
public class TaskListBuilder {
    private UUID id = UUID.randomUUID();
    private String title = "Task List Title";

    public TaskList build() {
        TaskList taskList = new TaskList(title);
        taskList.setId(id);
        return taskList;
    }
}
