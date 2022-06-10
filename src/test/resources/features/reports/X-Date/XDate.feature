Feature: X-Date

  As A MAR user,
  I want the ability to use generate X-Date reports

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "X-Date" Report Template

  @TC-368
  @sync-38773261
  Scenario: "X-Date" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Old-New" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "X-Date" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "New-Old" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "X-Date" are in ascending order
    And the user clicks the cancel button