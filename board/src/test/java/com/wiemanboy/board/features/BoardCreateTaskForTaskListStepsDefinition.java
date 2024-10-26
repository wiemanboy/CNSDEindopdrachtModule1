package com.wiemanboy.board.features;

import com.wiemanboy.board.presentation.dtos.request.CreateBoardDto;
import com.wiemanboy.board.presentation.dtos.request.CreateTaskDto;
import com.wiemanboy.board.presentation.dtos.request.CreateTaskListDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardCreateTaskForTaskListStepsDefinition {

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

    private ResponseEntity<String> response;

    private UUID boardId;

    private UUID taskListId;

    @Given("the client has valid data containing their boardId that contains a task list")
    public void theClientHasValidDataContainingTheirBoardIdThatContainsATaskList() throws JSONException {
        CreateBoardDto createBoardDto = new CreateBoardDto("My Board");
        ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:8080/api/boards/", createBoardDto, String.class);
        String responseBody = response.getBody();
        assert responseBody != null;
        String idString = responseBody.substring(responseBody.indexOf("\"id\":\"") + 6, responseBody.indexOf("\",", responseBody.indexOf("\"id\":\"")));
        boardId = UUID.fromString(idString);

        CreateTaskListDto createTaskListDto = new CreateTaskListDto("My Task List");
        ResponseEntity<String> response2 = testRestTemplate.postForEntity(format("http://localhost:8080/api/boards/%s/task-lists/", boardId), createTaskListDto, String.class);
        String responseBody2 = response2.getBody();

        JSONObject jsonObject = new JSONObject(responseBody2);
        JSONArray taskLists = jsonObject.getJSONArray("taskLists");
        if (taskLists.length() > 0) {
            JSONObject taskList = taskLists.getJSONObject(0);
            String taskListIdString = taskList.getString("id");
            taskListId = UUID.fromString(taskListIdString);
        }


        assertThat(responseBody2).contains("id", "My Task List");
    }

    @When("the client makes a POST request to \\/\\{boardId}\\/add-tasks with the title of their task")
    public void theClientMakesAPOSTRequestToBoardIdAddTasksWithTheTitleOfTheirTask() {
        CreateTaskDto createTaskDto = new CreateTaskDto("My Task", "My Task Description");
        response = testRestTemplate.postForEntity(format("http://localhost:8080/api/boards/%s/task-lists/%s/tasks/", boardId, taskListId), createTaskDto, String.class);
    }

    @Then("the client receives status code of {int} for their registration of the task")
    public void theClientReceivesStatusCodeOfForTheirRegistrationOfTheTask(int arg0) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(arg0));
    }

    @And("the client receives a response with the name of the task")
    public void theClientReceivesAResponseWithTheNameOfTheTask() {
        assertThat(response.getBody()).contains("My Task");
    }
}
