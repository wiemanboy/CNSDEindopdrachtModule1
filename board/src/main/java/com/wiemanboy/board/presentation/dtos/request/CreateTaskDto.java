package com.wiemanboy.board.presentation.dtos.request;

import java.util.UUID;

public record CreateTaskDto(
        UUID taskListId,
        String title,
        String description
) {
}
