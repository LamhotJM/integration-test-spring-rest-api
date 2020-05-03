Feature: Get student by id
  Scenario: User calls web service to get a student by id
    Given a student exists with an id of 13
    When a user retrieves the student by id
    Then the status code is 200