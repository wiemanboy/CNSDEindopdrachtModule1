package com.wiemanboy.board.presentation.dtos.response;

import com.wiemanboy.board.domain.Board;

import java.util.List;
import java.util.UUID;

public record BoardDto(
        UUID id,
        String title,
        List<UUID> collaboratorIds,
        List<TaskListDto> taskLists
) {
    public static BoardDto from(Board board) {
        return new BoardDto(
                board.getId(),
                board.getTitle(),
                board.getCollaboratorIds(),
                TaskListDto.from(board.getTaskLists())
        );
    }

    public static List<BoardDto> from(List<Board> boards) {
        return boards.stream()
                .map(BoardDto::from)
                .toList();
    }
}
