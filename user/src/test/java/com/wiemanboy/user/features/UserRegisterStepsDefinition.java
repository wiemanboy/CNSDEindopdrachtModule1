package com.wiemanboy.user.features;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRegisterStepsDefinition {

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();

    private ResponseEntity<String> response;

    @Given("^the client has valid data$")
    public void theClientHasValidData() {
    }

    @When("^the client makes a POST request to /$")
    public void theClientMakesAPOSTRequestTo() {
        response = testRestTemplate.postForEntity("http://localhost:8080/api/users/?username=henk", null, String.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCodeOf(int arg0) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));

    }

    @And("^the client receives a response with the name of the user$")
    public void theClientReceivesAResponseWithTheNameOfTheUser() {
        assertThat(response.getBody()).contains("henk");
    }
}
