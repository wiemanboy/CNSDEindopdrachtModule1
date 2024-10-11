package com.wiemanboy.board.features;

import com.wiemanboy.board.presentation.dtos.request.CreateBoardDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardCreateStepsDefinition {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private ResponseEntity<String> response;

    @Given("the client has valid data")
    public void theClientHasValidData() {
    }

    @When("the client makes a POST request to \\/ with the title of their board")
    public void theClientMakesAPOSTRequestToWithTheTitleOfTheirBoard() {
        CreateBoardDto createBoardDto = new CreateBoardDto("My Board");
        response = testRestTemplate.postForEntity("http://localhost:8080/api/boards/", createBoardDto, String.class);
    }

    @Then("the client receives status code of {int} for their registration")
    public void theClientReceivesStatusCodeOfForTheirRegistration(int arg0) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(arg0));
    }

    @And("the client receives a response with the name of the board")
    public void theClientReceivesAResponseWithTheNameOfTheBoard() {
        assertThat(response.getBody()).contains("My Board");
    }
}
