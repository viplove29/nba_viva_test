Feature: Subsidiary Aged Ledger

  As A MAR user,
  I want the ability to use generate a Subsidiary Aged Ledger report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Subsidiary Aged Ledger" Report Template


  @TC-282
  @sync-38124631
  Scenario: "Subsidiary Aged Ledger" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Division" under Filters
    And the user sets the Filter to start with "Div"
    And the user clicks on the Apply button
    Then the user verifies that all the "Division" values start with "Div"
