package com.wiemanboy.user.features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserValidationStepsDefintion {

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

    private ResponseEntity<String> response;

    private UUID id;

    @Given("^the client has valid data and is registered$")
    public void theClientHasValidDataAndIsRegistered() {
        ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:8080/api/users/?username=henk", null, String.class);
        String responseBody = response.getBody();
        assertThat(responseBody).contains("id");
        String idString = responseBody.substring(responseBody.indexOf("\"id\":\"") + 6, responseBody.indexOf("\",", responseBody.indexOf("\"id\":\"")));
        id = UUID.fromString(idString);
    }

    @When("^the client makes a GET request to /exists$")
    public void theClientMakesGETRequestToExists() {
        response = testRestTemplate.getForEntity("http://localhost:8080/api/users/" + id + "/exists", String.class);
    }

    @And("^the client receives a response with the boolean value of the user's existence$")
    public void theClientReceivesAResponseWithTheBooleanValueOfTheUserExistence() {
        assertThat(response.getBody()).contains("true");
    }
}
