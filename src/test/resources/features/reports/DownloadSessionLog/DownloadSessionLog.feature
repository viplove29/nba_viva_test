Feature: Download Session Log

  As A MAR user,
  I want the ability to use generate Download Session Log report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Download Session Log" Report Template


  @TC-384
  @sync-38853862
  Scenario: "Download Session Log" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Log Message" under Filters
    And the user sets the Filter to start with "Store"
    And the user clicks on the Apply button
    Then the user verifies that all the "Log Message" values on the Report start with "Store"