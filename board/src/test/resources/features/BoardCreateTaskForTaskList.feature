# Created by Jochem at 11-10-2024
Feature: Create Task for Task List
    As a user
    I want to create a task for a task list

  Scenario: Client makes a Post request to /{boardId}/add-tasks with valid data
    Given the client has valid data containing their boardId that contains a task list
    When the client makes a POST request to /{boardId}/add-tasks with the title of their task
    Then the client receives status code of 201 for their registration of the task
    And the client receives a response with the name of the task