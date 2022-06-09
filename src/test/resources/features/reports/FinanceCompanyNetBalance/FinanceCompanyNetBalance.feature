Feature: Finance Company Net Balance

  As A MAR user,
  I want the ability to use generate Finance Company Net Balance report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Finance Company Net Balance" Report Template


  @TC-94
  @sync-30863436
  Scenario: "Finance Company Net Balance" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Finance Company Name" under Filters
    And the user sets the Filter to start with "One"
    And the user clicks on the Apply button
    Then the user verifies that all the "Finance Company Name" values on the Report start with "One"
