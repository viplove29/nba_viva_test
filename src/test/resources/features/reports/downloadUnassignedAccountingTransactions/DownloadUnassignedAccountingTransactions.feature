Feature: Download Unassigned Accounting Transactions

  As A MAR user,
  I want the ability to use generate Feature: Download Unassigned Accounting Transactions

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Download Unassigned Accounting Transactions" Report Template


  @TC-318
  @sync-38582955
  Scenario: "Download Unassigned Accounting Transactions" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Sean"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values on the Report start with "Sean"
