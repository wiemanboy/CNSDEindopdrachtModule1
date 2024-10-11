package com.wiemanboy.board.application;

import com.wiemanboy.board.data.BoardRepository;
import com.wiemanboy.board.domain.Board;
import com.wiemanboy.board.domain.Task;
import com.wiemanboy.board.domain.TaskList;
import com.wiemanboy.board.domain.exceptions.BoardNotFoundException;
import com.wiemanboy.board.domain.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final TagService tagService;
    private final UserService userService;

    public BoardService(BoardRepository boardRepository, TagService tagService, UserService userService) {
        this.boardRepository = boardRepository;
        this.tagService = tagService;
        this.userService = userService;
    }

    public Board createBoard(String name) {
        Board board = new Board(name);
        boardRepository.save(board);
        return board;
    }

    public Board getBoardById(UUID boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(boardId));
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board addTaskList(UUID boardId, String title) {
        Board board = getBoardById(boardId);

        board.addTaskList(new TaskList(title));

        boardRepository.save(board);
        return board;
    }

    //TODO create test for service
    public Board updateTask(UUID boardId, UUID taskId, String title, String description) {
        Board board = getBoardById(boardId);
        Task task = board.getTaskById(taskId);

        task.setTitle(title);
        task.setDescription(description);

        boardRepository.save(board);
        return board;
    }

    public Board addTaskToTaskList(UUID boardId, UUID taskListId, String title, String description) {
        Board board = getBoardById(boardId);

        board.addTaskToTaskListById(taskListId, new Task(title, description));

        boardRepository.save(board);
        return board;
    }

    public Board moveTask(UUID boardId, UUID taskId, UUID targetTaskListId) {
        Board board = getBoardById(boardId);

        board.moveTaskById(taskId, targetTaskListId);

        boardRepository.save(board);
        return board;
    }

    public Board addTagToTask(UUID boardId, UUID taskId, UUID tagId) {
        Board board = getBoardById(boardId);
        Task task = board.getTaskById(taskId);

        task.addTag(tagService.getTagById(tagId));

        boardRepository.save(board);
        return board;
    }

    public Board addCollaborator(UUID boardId, UUID collaboratorId) {
        Board board = getBoardById(boardId);

        if(!userService.validateUser(collaboratorId)){
            throw new UserNotFoundException(collaboratorId);
        }

        board.addCollaborator(collaboratorId);

        boardRepository.save(board);
        return board;
    }

    //TODO create test for service
    public Board addCollaboratorToTask(UUID boardId, UUID taskId, UUID collaboratorId) {
        Board board = getBoardById(boardId);
        Task task = board.getTaskById(taskId);

        if(!userService.validateUser(collaboratorId)){
            throw new UserNotFoundException(collaboratorId);
        }

        task.addCollaborator(collaboratorId);

        boardRepository.save(board);
        return board;
    }

    public Board removeTagFromTask(UUID boardId, UUID taskId, UUID tagId) {
        Board board = getBoardById(boardId);
        Task task = board.getTaskById(taskId);

        task.removeTag(tagService.getTagById(tagId));

        boardRepository.save(board);
        return board;
    }
}
