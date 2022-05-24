Feature: Broker Net Balance

  As A MAR user,
  I want the ability to use generate Broker Net Balance reportz

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Broker Net Balance" Report Template


  @TC-292
  @sync-38392818
  Scenario: "Broker Net Balance" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Broker Name" under Filters
    And the user sets the Filter to start with "D"
    And the user clicks on the Apply button
    Then the user verifies that all the "Broker Name" values on the Report start with "D"