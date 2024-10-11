# Created by Jochem at 11-10-2024
Feature: The user can validate

  Scenario: Client makes a GET request to /exists with valid data
    Given the client has valid data and is registered
    When the client makes a GET request to /exists
    Then the client receives status code of 200 for their validation
    And the client receives a response with the boolean value of the user's existence