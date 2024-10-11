package com.wiemanboy.board.features;

import com.wiemanboy.board.presentation.dtos.request.CreateTagDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TagCreateStepsDefinition {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private ResponseEntity<String> response;

    @Given("the client has a tag with name {string}")
    public void theClientHasATagWithName(String arg0) {
        System.out.println("Given the client has a tag with name " + arg0);
    }

    @When("the client sends a POST request to \\/tags\\/")
    public void theClientSendsAPOSTRequestToTags() {
        CreateTagDto createTagDto = new CreateTagDto("tag1", "#ff0000");
        response = testRestTemplate.postForEntity("http://localhost:8080/api/tags/", createTagDto, String.class);
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int arg0) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(arg0));
    }

    @And("the response body is a tag with name {string}")
    public void theResponseBodyIsATagWithName(String arg0) {
        assertThat(response.getBody()).contains(arg0);
    }
}
