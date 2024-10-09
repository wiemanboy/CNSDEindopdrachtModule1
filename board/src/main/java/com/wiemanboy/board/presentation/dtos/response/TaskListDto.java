package com.wiemanboy.board.presentation.dtos.response;

import com.wiemanboy.board.domain.TaskList;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        List<TaskDto> tasks
) {
    public static TaskListDto from(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                TaskDto.from(taskList.getTasks())
        );
    }

    public static List<TaskListDto> from(List<TaskList> taskLists) {
        return taskLists.stream()
                .map(TaskListDto::from)
                .toList();
    }
}
