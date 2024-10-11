# Created by Jochem at 11-10-2024
Feature: Create a tag
  as a user

  Scenario: Client creates a tag by sending a POST request to the server
    Given the client has a tag with name "tag1"
    When the client sends a POST request to /tags/
    Then the response status code is 201
    And the response body is a tag with name "tag1"