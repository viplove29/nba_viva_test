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
    Then the user verifies that all the "Account Name" values on the Report start with "Petty"

  @TC-276
  @sync-37443848
  Scenario: "Chart Of Accounts" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Large-Small" under Sort
    And the user clicks on the Apply button
    And the user verifies that the values of "GL Number" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "Small-Large" under Sort
    And the user clicks on the Apply button
    And the user verifies that the values of "GL Number" are in ascending order
    And the user clicks the cancel button