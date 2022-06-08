Feature: New Business

  As A MAR user,
  I want the ability to use generate New Business report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "New Business" Report Template


  @TC-338
  @sync-38702114
  Scenario: "New Business" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Division" under Filters
    And the user sets the Filter to start with "Div"
    And the user clicks on the Apply button
    Then the user verifies that all the "Division" values on the Report start with "Div"
