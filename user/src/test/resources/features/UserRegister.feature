# Created by Jochem at 11-10-2024
Feature: The user can register

  Scenario: Client makes a POST request to / with valid data
    Given the client has valid data
    When the client makes a POST request to /
    Then the client receives status code of 200
    And the client receives a response with the name of the user