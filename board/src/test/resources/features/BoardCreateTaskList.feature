# Created by Jochem at 11-10-2024
Feature: Create a task list
    As a user

  Scenario: Client makes a Post request to /{boardId}/add-task-lists with valid data
    Given the client has valid data containing their boardId
    When the client makes a POST request to /{boardId}/add-task-lists with the title of their task list
    Then the client receives status code of 201 for their registration of the task list
    And the client receives a response with the name of the task list