Feature: Lost Business

  As A MAR user,
  I want the ability to use generate Lost Business report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Lost Business" Report Template


  @TC-333
  @sync-38666740
  Scenario: "Lost Business" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to "12/31/2022"
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Customer"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values on the Report start with "Customer"