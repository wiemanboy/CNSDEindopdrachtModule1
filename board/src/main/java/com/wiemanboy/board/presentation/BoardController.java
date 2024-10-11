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

    @GetMapping("/{boardId}")
    public BoardDto getBoard(@PathVariable UUID boardId) {
        return BoardDto.from(boardService.getBoardById(boardId));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto createBoard(@RequestBody CreateBoardDto createBoardDto) {
        return BoardDto.from(boardService.createBoard(createBoardDto.title()));
    }

    @PostMapping("/{boardId}/add-task-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto addTaskList(@PathVariable UUID boardId, @RequestBody CreateTaskListDto createTaskListDto) {
        return BoardDto.from(boardService.addTaskList(boardId, createTaskListDto.title()));
    }

    @PostMapping("/{boardId}/add-tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public BoardDto addTaskToTaskList(@PathVariable UUID boardId, @RequestBody CreateTaskDto createTaskListDto) {
        return BoardDto.from(boardService.addTaskToTaskList(
                boardId,
                createTaskListDto.taskListId(),
                createTaskListDto.title(),
                createTaskListDto.description()
        ));
    }
    //TODO Create tests for endpoint
    @PutMapping("/{boardId}/update-task")
    public BoardDto updateTask(@PathVariable UUID boardId, @RequestBody UpdateTaskDto updateTaskDto) {
        return BoardDto.from(boardService.updateTask(
                boardId,
                updateTaskDto.taskId(),
                updateTaskDto.title(),
                updateTaskDto.description()
        ));
    }

    @PutMapping("/{boardId}/move-task")
    public BoardDto moveTask(@PathVariable UUID boardId, @RequestBody MoveTaskDto moveTaskDto) {
        return BoardDto.from(boardService.moveTask(boardId, moveTaskDto.taskId(), moveTaskDto.targetTaskListId()));
    }

    @PostMapping("/{boardId}/add-tag")
    public BoardDto addTagToTask(@PathVariable UUID boardId, @RequestBody AddTagDto addTagDto) {
        return BoardDto.from(boardService.addTagToTask(boardId, addTagDto.taskId(), addTagDto.tagId()));
    }

    @PostMapping("/{boardId}/add-collaborator")
    public BoardDto addCollaborator(@PathVariable UUID boardId, @RequestBody AddCollaboratorDto addCollaboratorDto) {
        return BoardDto.from(boardService.addCollaborator(boardId, addCollaboratorDto.collaboratorId()));
    }
}
