package com.wiemanboy.board.presentation.dtos.request;

import java.util.UUID;

public record AddTagDto(
        UUID taskId,
        UUID tagId
) {
}
