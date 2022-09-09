Feature: Balance Sheet

As a MAR user,
I want the ability to use generate balance sheets

Background:
  Given the user logs into MAR as "RegularUser"
  And the user selects "Balance Sheet" Report Template


  @TC-3
  @sync-30862900
  Scenario: Balance Sheet_Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the following columns are displayed in tab
    | Division       |
    | Level          |
    | Account        |
    | Description    |
    | Amount         |
    | Compare Amount |


  @TC-266
  @sync-38702114
  Scenario: "Balance Sheet" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Description" under Filters
    And the user sets the Filter to start with "LIA"
    And the user clicks on the Apply button
    Then the user verifies that all the "Description" values on the Report start with "LIA"

  @TC-265
  @sync-36868106
  Scenario: "Balance Sheet" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Division" under drop down
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "balanceSheetTemplate"
    And the user saves the template
    And the user clicks the Ok button
    Then the user verifies that the values of "Division" are in descending order
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Division" are in ascending order
    And the user clicks on the Sort symbol
    And the user presses the back arrow
    And the user search for the template named "balanceSheetTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-3
  @sync-30862900
  Scenario: Balance Sheet Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Division        |
      | Level           |
      | Account         |
      | Description     |
      | Amount          |
      | Compare Amount  |
    And the user selects "Description" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Description     |

