Feature: Policy Expiration

  As A MAR user,
  I want the ability to use generate a Policy Expiration report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Policy Expiration" Report Template


  @TC-343
  @sync-38702524
  Scenario: "Policy Expiration" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "107"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values start with "107"
