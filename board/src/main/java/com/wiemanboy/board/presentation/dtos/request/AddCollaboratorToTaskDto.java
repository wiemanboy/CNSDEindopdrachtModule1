package com.wiemanboy.board.presentation.dtos.request;

import java.util.UUID;

public record AddCollaboratorToTaskDto(UUID taskId, UUID collaboratorId) {
}
