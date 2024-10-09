package com.wiemanboy.board.application;

import com.wiemanboy.board.builders.BoardBuilder;
import com.wiemanboy.board.builders.TagBuilder;
import com.wiemanboy.board.builders.TaskBuilder;
import com.wiemanboy.board.builders.TaskListBuilder;
import com.wiemanboy.board.data.BoardRepository;
import com.wiemanboy.board.domain.Board;
import com.wiemanboy.board.domain.Task;
import com.wiemanboy.board.domain.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

    private BoardRepository boardRepository;
    private TagService tagService;
    private BoardService boardService;

    @BeforeEach
    void setUp() {
        boardRepository = Mockito.mock(BoardRepository.class);
        tagService = Mockito.mock(TagService.class);
        boardService = new BoardService(boardRepository, tagService);
    }

    @Test
    void createBoard() {
        String name = "name";

        boardService.createBoard(name);

        Mockito.verify(boardRepository).save(Mockito.any());
    }

    @Test
    void getBoardById() {
        Board board = new BoardBuilder().build();
        Mockito.when(boardRepository.findById(Mockito.any())).thenReturn(Optional.of(board));

        assertEquals(board, boardService.getBoardById(UUID.randomUUID()));
        Mockito.verify(boardRepository).findById(Mockito.any());
    }

    @Test
    void getAllBoards() {
        List<Board> boards = List.of(new BoardBuilder().build(), new BoardBuilder().build());
        Mockito.when(boardRepository.findAll()).thenReturn(boards);

        assertEquals(boards, boardService.getAllBoards());
        Mockito.verify(boardRepository).findAll();

    }

    @Test
    void addTaskList() {
        Mockito.when(boardRepository.findById(Mockito.any())).thenReturn(Optional.of(new BoardBuilder().build()));

        Board board = boardService.addTaskList(UUID.randomUUID(), "title");

        assertEquals(1, board.getTaskLists().size());
        Mockito.verify(boardRepository).save(Mockito.any());
    }

    @Test
    void addTaskToTaskList() {
        Board board = new BoardBuilder().build();
        TaskList taskList = new TaskListBuilder().build();
        board.addTaskList(taskList);

        Mockito.when(boardRepository.findById(Mockito.any())).thenReturn(Optional.of(board));

        Board boardResult = boardService.addTaskToTaskList(UUID.randomUUID(), taskList.getId(), "title", "description");

        assertEquals(1, boardResult.getTaskLists().getFirst().getTasks().size());
        Mockito.verify(boardRepository).save(Mockito.any());
    }

    @Test
    void moveTask() {
        Board board = new BoardBuilder().build();
        TaskList taskList1 = new TaskListBuilder().build();
        TaskList taskList2 = new TaskListBuilder().build();
        Task task = new TaskBuilder().build();

        board.addTaskList(taskList1);
        board.addTaskList(taskList2);
        board.addTaskToTaskList(taskList1, task);

        Mockito.when(boardRepository.findById(Mockito.any())).thenReturn(Optional.of(board));

        Board boardResult = boardService.moveTask(board.getId(), task.getId(), taskList2.getId());

        assertEquals(0, boardResult.getTaskLists().get(0).getTasks().size());
        assertEquals(1, boardResult.getTaskLists().get(1).getTasks().size());
        Mockito.verify(boardRepository).save(Mockito.any());
    }

    @Test
    void addTagToTask() {
        Board board = new BoardBuilder().build();
        TaskList taskList = new TaskListBuilder().build();
        Task task = new TaskBuilder().build();

        board.addTaskList(taskList);
        taskList.addTask(task);

        Mockito.when(boardRepository.findById(Mockito.any())).thenReturn(Optional.of(board));
        Mockito.when(tagService.getTagById(Mockito.any())).thenReturn(new TagBuilder().build());

        Board boardResult = boardService.addTagToTask(board.getId(), task.getId(), UUID.randomUUID());

        assertEquals(1, boardResult.getTaskLists().getFirst().getTasks().getFirst().getTags().size());
        Mockito.verify(boardRepository).save(Mockito.any());
    }

    @Test
    void addCollaborator() {
        Mockito.when(boardRepository.findById(Mockito.any())).thenReturn(Optional.of(new BoardBuilder().build()));

        Board board = boardService.addCollaborator(UUID.randomUUID(), UUID.randomUUID());

        assertEquals(1, board.getCollaboratorIds().size());
        Mockito.verify(boardRepository).save(Mockito.any());
    }
}