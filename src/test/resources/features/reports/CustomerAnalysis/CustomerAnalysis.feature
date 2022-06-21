Feature: Customer Analysis

  As A MAR user,
  I want the ability to use generate Customer Analysis report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Customer Analysis" Report Template


  @TC-308
  @sync-38527152
  Scenario: "Customer Analysis" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Accardi"
    And the user clicks on the Apply button
    And the user extracts all the report values in the grid
    Then the user verifies that all the "Customer Name" values on the Report start with "Accardi"
