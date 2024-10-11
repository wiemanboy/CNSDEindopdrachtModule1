package com.wiemanboy.board.presentation.dtos.request;

import java.util.UUID;

public record UpdateTaskDto(UUID taskId, String title, String description) {
}
