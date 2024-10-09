package com.wiemanboy.board.presentation.dtos.response;

import com.wiemanboy.board.domain.Task;

import java.util.List;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        List<UUID> collaboratorIds,
        List<TagDto> tags
) {
    public static TaskDto from(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCollaboratorIds(),
                TagDto.from(task.getTags())
        );
    }

    public static List<TaskDto> from(List<Task> tasks) {
        return tasks.stream()
                .map(TaskDto::from)
                .toList();
    }
}
