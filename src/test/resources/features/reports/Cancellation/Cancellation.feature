Feature: Cancellation Policies

  As A MAR user,
  I want the ability to use generate Cancellation reportz

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Cancellation" Report Template


  @TC-297
  @sync-38405343
  Scenario: "Cancellation" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to "12/31/2021"
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Policy Number" under Filters
    And the user sets the Filter to start with "CL01"
    And the user clicks on the Apply button
    Then the user verifies that all the "Policy Number" values on the Report start with "CL01"