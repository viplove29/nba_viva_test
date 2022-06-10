Feature: Retention

  As A MAR user,
  I want the ability to use generate a Retention report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Retention" Report Template


  @TC-354
  @sync-38736483
  Scenario: "Retention" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user verifies that the values of "Customer Name" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    And the user verifies that the values of "Customer Name" are in ascending order
    And the user clicks the cancel button
