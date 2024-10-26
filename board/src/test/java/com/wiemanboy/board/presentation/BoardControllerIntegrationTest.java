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

import static java.lang.String.format;
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

        mockMvc.perform(post(format("/api/boards/%s/task-lists/", board.getId()))
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
        CreateTaskDto createTaskDto = new CreateTaskDto("Task 1", "Description 1");

        mockMvc.perform(post(format("/api/boards/%s/task-lists/%s/tasks/", board.getId(), taskList.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTaskDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void addTaskToTaskListNotFound() throws Exception {
        Board board = boardRepository.save(new BoardBuilder().build());
        CreateTaskDto createTaskDto = new CreateTaskDto("Task 1", "Description 1");

        mockMvc.perform(post(format("/api/boards/%s/task-lists/%s/tasks/", board.getId(), UUID.randomUUID()))
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

        mockMvc.perform(put(format("/api/boards/%s/task-lists/%s/tasks/%s", board.getId(), taskList2.getId(), task.getId())))
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

        mockMvc.perform(put(format("/api/boards/%s/task-lists/%s/tasks/%s", board.getId(), taskList2.getId(), UUID.randomUUID())))

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

        mockMvc.perform(post(format("/api/boards/%s/tasks/%s/tags/%s", board.getId(), task.getId(), tag.getId())))
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

        mockMvc.perform(post(format("/api/boards/%s/tasks/%s/tags/%s", board.getId(), task.getId(), UUID.randomUUID())))
                .andExpect(status().isNotFound());
    }

    @Test
    void addTagToTaskTagTaskNotFound() throws Exception {
        Board board = boardRepository.save(new BoardBuilder().build());
        Tag tag = tagRepository.save(new TagBuilder().build());

        mockMvc.perform(post(format("/api/boards/%s/tasks/%s/tags/%s", board.getId(), UUID.randomUUID(), tag.getId())))
                .andExpect(status().isNotFound());
    }

    @Test
    void addCollaborator() throws Exception {
        Board board = new BoardBuilder().build();
        board = boardRepository.save(board);
        UUID collaboratorId = UUID.randomUUID();

        mockServer.expect(requestTo(format("http://localhost:8080/api/users/%s/exists", collaboratorId)))
                .andRespond(withSuccess("true", MediaType.APPLICATION_JSON));

        mockMvc.perform(post(format("/api/boards/%s/collaborators/%s", board.getId(), collaboratorId)))
                .andExpect(status().isOk());
    }
}