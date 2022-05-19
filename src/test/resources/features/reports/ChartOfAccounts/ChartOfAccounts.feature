Feature: Chart Of Accounts Policies

  As A MAR user,
  I want the ability to use generate Chart Of Accounts report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Chart of Accounts" Report Template


  @TC-277
  @sync-37443980
  Scenario: "Chart Of Accounts" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Account Name" under Filters
    And the user sets the Filter to start with "Petty"
    And the user clicks on the Apply button
    Then the user verifies that all the "Account Name" values start with "Petty"