Feature: Balance Sheet

  As a MAR user,
  I want the ability to use generate balance sheets

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Balance Sheet" Report Template
    And the user generates the report with a random name

  @TC-3
  @sync-30862900
  Scenario: Balance Sheet_Check Columns spelling and Show/Hide columns option
    When the user navigates to the generated report
    Then the user verifies the following columns are displayed:
      | Division       |
      | Level          |
      | Account        |
      | Description    |
      | Amount         |
      | Compare Amount |