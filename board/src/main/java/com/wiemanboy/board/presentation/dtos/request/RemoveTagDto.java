package com.wiemanboy.board.presentation.dtos.request;

import java.util.UUID;

public record RemoveTagDto(UUID taskId,UUID tagId) {
}
