Feature: Chart Of Accounts Policies

  As A MAR user,
  I want the ability to use generate Chart Of Accounts report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Chart of Accounts" Report Template


  @TC-277
  @sync-37443980
  @ChartOfAccounts
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
  @ChartOfAccounts
  Scenario: "Chart Of Accounts" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Large-Small" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "GL Number" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "Small-Large" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "GL Number" are in ascending order
    And the user clicks the cancel button


  @TC-278
  @sync-37444609
  @ChartOfAccounts
  Scenario: Chart of Accounts Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | GL Number          |
      | Account Name       |
      | Type               |
      | Level Number       |
      | Subledger Name     |
      | Zero Balance       |
    And the user selects "Level Number" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Level Number       |

