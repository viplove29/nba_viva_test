Feature: Personnel Extract

  As A MAR user,
  I want the ability to use generate Personnel Extract report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Unbilled Policies" Report Template


  @TC-364
  @sync-38737417
  Scenario: "Unbilled Policy" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to "12/31/2021"
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol on the Unbilled Policy report
    And the user clicks on the Add Filters button on the Unbilled Policy Report
    And the user selects "Policy Number" under Filters on the Unbilled Policy Report
    And the user sets the Filter to start with "CL01" on the Unbilled Policy Report
    And the user clicks on the Apply button on the Unbilled Policy Report
    Then the user verifies that all the Policy Number values on the Unbilled Policy start with "CL01"
