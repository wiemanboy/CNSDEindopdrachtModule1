package com.wiemanboy.board.features;

import com.wiemanboy.board.presentation.dtos.request.CreateBoardDto;
import com.wiemanboy.board.presentation.dtos.request.CreateTaskListDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardCreateTaskListStepsDefinition {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private ResponseEntity<String> response;

    private UUID boardId;

    @Given("the client has valid data containing their boardId")
    public void theClientHasValidDataContainingTheirBoardId() {
        CreateBoardDto createBoardDto = new CreateBoardDto("My Board");
        ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:8080/api/boards/", createBoardDto, String.class);
        String responseBody = response.getBody();
        assertThat(responseBody).contains("id");
        String idString = responseBody.substring(responseBody.indexOf("\"id\":\"") + 6, responseBody.indexOf("\",", responseBody.indexOf("\"id\":\"")));
        boardId = UUID.fromString(idString);
    }

    @When("the client makes a POST request to \\/\\{boardId}\\/add-task-lists with the title of their task list")
    public void theClientMakesAPOSTRequestToBoardIdAddTaskListsWithTheTitleOfTheirTaskList() {
        CreateTaskListDto createTaskListDto = new CreateTaskListDto("My Task List");
        response = testRestTemplate.postForEntity("http://localhost:8080/api/boards/" + boardId + "/add-task-lists", createTaskListDto, String.class);
    }

    @Then("the client receives status code of {int} for their registration of the task list")
    public void theClientReceivesStatusCodeOfForTheirRegistrationOfTheTaskList(int arg0) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(arg0));
    }

    @And("the client receives a response with the name of the task list")
    public void theClientReceivesAResponseWithTheNameOfTheTaskList() {
        assertThat(response.getBody()).contains("My Task List");
    }
}
