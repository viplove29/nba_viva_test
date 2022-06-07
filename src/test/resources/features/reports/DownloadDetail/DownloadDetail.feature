Feature: Download Detail

  As A MAR user,
  I want the ability to use generate Download Detail report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Download Detail" Report Template


  @TC-374
  @sync-38827194
  Scenario: "Download Detail" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Parent Company" under Filters
    And the user sets the Filter to start with "Chu"
    And the user clicks on the Apply button
    Then the user verifies that all the "Parent Company" values on the Report start with "Chu"
