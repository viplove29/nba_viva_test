Feature: Retention

  As A MAR user,
  I want the ability to use generate a Retention report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Retention" Report Template


  @TC-354
  @sync-38736483
  @Retention
  Scenario: "Retention" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "retentionTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user verifies that the values of "Customer Name" are in descending order
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    And the user verifies that the values of "Customer Name" are in ascending order
    And the user clicks on the Sort symbol
    And the user presses the back arrow
    And the user search for the template named "retentionTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-356
  @sync-38736486
  @Retention
  Scenario: Add Retention summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Policy Count     |
      | Division         |
      | Line of Business |
      | Branch           |
    And the user names the summary "retentionSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "retentionSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "retentionSummaryTemplate"
    And the user selects "retentionSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "retentionSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "retentionSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-357
  @sync-38736487
  @Retention
  Scenario: Retention Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                |
      | Division                     |
      | Branch                       |
      | Department                   |
      | Type of Business             |
      | Line of Business/Non-Premium |
      | Policy Number                |
      | Primary Executive            |
      | Primary Representative       |
      | Current Broker Name          |
      | Parent Company               |
      | Writing Company              |
      | Revenue                      |
      | Premium                      |
      | Annualized Premium           |
    And the user selects "Writing Company" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Writing Company |

