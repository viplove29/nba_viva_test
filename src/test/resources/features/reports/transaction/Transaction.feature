Feature: Transaction

  As A MAR user,
  I want the ability to use generate Transaction report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Transaction" Report Template


  @TC-359
  @sync-38737417
  Scenario: "Transaction" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to "12/31/2021"
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol of the Transaction report
    And the user clicks on the Add Filters button of the Transaction Report
    And the user selects "Customer Name" under Filters of the Transaction Report
    And the user sets the Filter to start with "1" of the Transaction Report
    And the user clicks on the Apply button of the Transaction Report
    And the user extracts all the report values in the grid
    And the user verifies that all the Customer Name values on the Transaction Report start with "1"
