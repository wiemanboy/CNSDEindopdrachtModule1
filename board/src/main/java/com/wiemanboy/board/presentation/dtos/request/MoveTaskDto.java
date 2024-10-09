package com.wiemanboy.board.presentation.dtos.request;

import java.util.UUID;

public record MoveTaskDto(
        UUID taskId,
        UUID targetTaskListId
) {
}
