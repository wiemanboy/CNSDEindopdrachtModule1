# Created by Jochem at 11-10-2024
Feature: Create a board
    As a user

  Scenario: Client makes a Post request to / with valid data
    Given the client has valid data
    When the client makes a POST request to / with the title of their board
    Then the client receives status code of 201 for their registration
    And the client receives a response with the name of the board