package com.wiemanboy.board.presentation;

import com.wiemanboy.board.application.BoardService;
import com.wiemanboy.board.presentation.dtos.request.*;
import com.wiemanboy.board.presentation.dtos.response.BoardDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public List<BoardDto> getBoards() {
        return BoardDto.from(boardService.getAllBoards());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto createBoard(@RequestBody CreateBoardDto createBoardDto) {
        return BoardDto.from(boardService.createBoard(createBoardDto.title()));
    }

    @GetMapping("/{boardId}")
    public BoardDto getBoard(@PathVariable UUID boardId) {
        return BoardDto.from(boardService.getBoardById(boardId));
    }

    @PostMapping("/{boardId}/collaborators/{collaboratorId}")
    public BoardDto addCollaborator(@PathVariable UUID boardId, @PathVariable UUID collaboratorId) {
        return BoardDto.from(boardService.addCollaborator(boardId, collaboratorId));
    }

    @PostMapping("/{boardId}/task-lists/")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto addTaskList(@PathVariable UUID boardId, @RequestBody CreateTaskListDto createTaskListDto) {
        return BoardDto.from(boardService.addTaskList(boardId, createTaskListDto.title()));
    }

    @PostMapping("/{boardId}/task-lists/{taskListId}/tasks/")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto addTaskToTaskList(@PathVariable UUID boardId, @PathVariable UUID taskListId, @RequestBody CreateTaskDto createTaskListDto) {
        return BoardDto.from(boardService.addTaskToTaskList(
                boardId,
                taskListId,
                createTaskListDto.title(),
                createTaskListDto.description()
        ));
    }

    @PutMapping("/{boardId}/task-lists/{taskListId}/tasks/{taskId}")
    public BoardDto moveTask(@PathVariable UUID boardId, @PathVariable UUID taskListId, @PathVariable UUID taskId) {
        return BoardDto.from(boardService.moveTask(boardId, taskId, taskListId));
    }

    //TODO Create tests for endpoint
    @PutMapping("/{boardId}/tasks/{taskId}")
    public BoardDto updateTask(@PathVariable UUID boardId, @PathVariable UUID taskId, @RequestBody UpdateTaskDto updateTaskDto) {
        return BoardDto.from(boardService.updateTask(
                boardId,
                taskId,
                updateTaskDto.title(),
                updateTaskDto.description()
        ));
    }

    @PostMapping("/{boardId}/tasks/{taskId}/tags/{tagId}")
    public BoardDto addTagToTask(@PathVariable UUID boardId, @PathVariable UUID taskId, @PathVariable UUID tagId) {
        return BoardDto.from(boardService.addTagToTask(boardId, taskId, tagId));
    }

    // TODO: Create tests for endpoint
    @DeleteMapping("/{boardId}/tasks/{taskId}/tags/{tagId}")
    public BoardDto removeTagFromTask(@PathVariable UUID boardId, @PathVariable UUID taskId, @PathVariable UUID tagId) {
        return BoardDto.from(boardService.removeTagFromTask(boardId, taskId, tagId));
    }

    //TODO Create tests for endpoint
    @PutMapping("/{boardId}/tasks/{taskId}/collaborators/{collaboratorId}")
    public BoardDto addCollaboratorToTask(@PathVariable UUID boardId, @PathVariable UUID taskId, @PathVariable UUID collaboratorId) {
        return BoardDto.from(boardService.addCollaboratorToTask(boardId, taskId, collaboratorId));
    }
}
