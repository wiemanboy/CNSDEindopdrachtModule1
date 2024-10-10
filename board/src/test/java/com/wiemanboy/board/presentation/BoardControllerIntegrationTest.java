package com.wiemanboy.board.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wiemanboy.board.builders.BoardBuilder;
import com.wiemanboy.board.builders.TagBuilder;
import com.wiemanboy.board.data.BoardRepository;
import com.wiemanboy.board.data.TagRepository;
import com.wiemanboy.board.domain.Board;
import com.wiemanboy.board.domain.Tag;
import com.wiemanboy.board.domain.Task;
import com.wiemanboy.board.domain.TaskList;
import com.wiemanboy.board.presentation.dtos.request.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BoardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RestTemplate restTemplate;

    MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        boardRepository.deleteAll();
        mockServer = MockRestServiceServer.createServer(restTemplate);

    }

    @Test
    void getBoards() throws Exception {
        boardRepository.save(new BoardBuilder().build());
        boardRepository.save(new BoardBuilder().build());

        mockMvc.perform(get("/api/boards/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getBoard() throws Exception {
        Board board = boardRepository.save(new BoardBuilder().build());

        mockMvc.perform(get("/api/boards/" + board.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(board.getId().toString()));
    }

    @Test
    void getBoardNotFound() throws Exception {
        mockMvc.perform(get("/api/boards/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBoard() throws Exception {
        CreateBoardDto createBoardDto = new CreateBoardDto("Board 1");

        mockMvc.perform(post("/api/boards/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBoardDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void addTaskList() throws Exception {
        Board board = boardRepository.save(new BoardBuilder().build());
        CreateTaskListDto createTaskListDto = new CreateTaskListDto("Task List 1");

        mockMvc.perform(post("/api/boards/" + board.getId() + "/add-task-lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTaskListDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void addTaskToTaskList() throws Exception {
        Board board = new BoardBuilder().build();
        TaskList taskList = new TaskList("Task List 1");
        board.addTaskList(taskList);
        board = boardRepository.save(board);
        CreateTaskDto createTaskDto = new CreateTaskDto(taskList.getId(), "Task 1", "Description 1");

        mockMvc.perform(post("/api/boards/" + board.getId() + "/add-tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTaskDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void addTaskToTaskListNotFound() throws Exception {
        Board board = boardRepository.save(new BoardBuilder().build());
        CreateTaskDto createTaskDto = new CreateTaskDto(UUID.randomUUID(), "Task 1", "Description 1");

        mockMvc.perform(post("/api/boards/" + board.getId() + "/add-tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTaskDto))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void moveTask() throws Exception {
        Board board = new BoardBuilder().build();
        TaskList taskList1 = new TaskList("Task List 1");
        TaskList taskList2 = new TaskList("Task List 2");
        Task task = new Task("Task 1", "Description 1");
        board.addTaskList(taskList1);
        board.addTaskList(taskList2);
        board.addTaskToTaskList(taskList1, task);
        board = boardRepository.save(board);
        MoveTaskDto moveTaskDto = new MoveTaskDto(task.getId(), taskList2.getId());

        mockMvc.perform(put("/api/boards/" + board.getId() + "/move-task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moveTaskDto))
                )
                .andExpect(status().isOk());
    }

    @Test
    void moveTaskNotFound() throws Exception {
        Board board = new BoardBuilder().build();
        TaskList taskList1 = new TaskList("Task List 1");
        TaskList taskList2 = new TaskList("Task List 2");
        board.addTaskList(taskList1);
        board.addTaskList(taskList2);
        board = boardRepository.save(board);
        MoveTaskDto moveTaskDto = new MoveTaskDto(UUID.randomUUID(), taskList2.getId());

        mockMvc.perform(put("/api/boards/" + board.getId() + "/move-task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moveTaskDto))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void addTagToTask() throws Exception {
        Board board = new BoardBuilder().build();
        TaskList taskList = new TaskList("Task List 1");
        Task task = new Task("Task 1", "Description 1");
        board.addTaskList(taskList);
        board.addTaskToTaskList(taskList, task);
        board = boardRepository.save(board);
        Tag tag = tagRepository.save(new TagBuilder().build());
        AddTagDto addTagDto = new AddTagDto(task.getId(), tag.getId());

        mockMvc.perform(post("/api/boards/" + board.getId() + "/add-tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addTagDto))
                )
                .andExpect(status().isOk());
    }

    @Test
    void addTagToTaskTagNotFound() throws Exception {
        Board board = new BoardBuilder().build();
        TaskList taskList = new TaskList("Task List 1");
        Task task = new Task("Task 1", "Description 1");
        board.addTaskList(taskList);
        board.addTaskToTaskList(taskList, task);
        board = boardRepository.save(board);
        AddTagDto addTagDto = new AddTagDto(task.getId(), UUID.randomUUID());

        mockMvc.perform(post("/api/boards/" + board.getId() + "/add-tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addTagDto))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void addTagToTaskTagTaskNotFount() throws Exception {
        Board board = boardRepository.save(new BoardBuilder().build());
        Tag tag = tagRepository.save(new TagBuilder().build());
        AddTagDto addTagDto = new AddTagDto(UUID.randomUUID(), tag.getId());

        mockMvc.perform(post("/api/boards/" + board.getId() + "/add-tag")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addTagDto))
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void addCollaborator() throws Exception {
        Board board = new BoardBuilder().build();
        board = boardRepository.save(board);
        AddCollaboratorDto addCollaboratorDto = new AddCollaboratorDto(UUID.randomUUID());

        mockServer.expect(requestTo("http://localhost:8080/api/users/" + addCollaboratorDto.collaboratorId() + "/exists"))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON));

        mockMvc.perform(post("/api/boards/" + board.getId() + "/add-collaborator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCollaboratorDto))
                )
                .andExpect(status().isOk());
    }
}