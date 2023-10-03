Feature: save  note.
  As a user, I want to be able to create and save notes to remind me of pending tasks.

  Scenario: Create a new note successfully
    Given that I am a user on the notes page
    When I create a new note with the following details: Id 1, Title "Doing tasks", Description "Doing software quality tasks"
    Then the note is successfully created
    And the status of the note is "Iniciada"