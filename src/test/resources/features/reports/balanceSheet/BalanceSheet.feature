Feature: Balance Sheet

As a MAR user,
I want the ability to use generate balance sheets

Background:
  Given the user logs into MAR as "RegularUser"
  And the user selects "Balance Sheet" Report Template


  @TC-3
  @sync-30862900
  Scenario: Balance Sheet_Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the following columns are displayed in tab
    | Division       |
    | Level          |
    | Account        |
    | Description    |
    | Amount         |
    | Compare Amount |


  @TC-266
  @sync-38702114
  Scenario: "Balance Sheet" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to "12/31/2021"
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Description" under Filters
    And the user sets the Filter to start with "LIA"
    And the user clicks on the Apply button
    Then the user verifies that all the "Description" values on the Report start with "LIA"

