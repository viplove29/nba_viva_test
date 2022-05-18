Feature: Relationship Revenue

  As A MAR user,
  I want the ability to use generate Relationship Revenue report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Relationship Revenue" Report Template


  @TC-350
  @sync-38729771
  Scenario: "Relationship Revenue" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Relationship Description" under Filters
    And the user sets the Filter to start with "Gr"
    And the user clicks on the Apply button
    Then the user verifies that all the "Relationship Description" values start with "Gr"