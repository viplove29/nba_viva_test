Feature: Transaction

  As A MAR user,
  I want the ability to use generate Transaction report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Transaction" Report Template


  @TC-359
  @sync-38737417
  Scenario: "Transaction" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "1"
    And the user clicks on the Apply button
    And the user extracts all the report values in the grid
    And the user verifies that all the "Customer Name" values on the Report start with "1"

  @TC-358
  @sync-38737188
  Scenario: "Transaction" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Customer Name" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Customer Name" are in ascending order
    And the user clicks the cancel button
