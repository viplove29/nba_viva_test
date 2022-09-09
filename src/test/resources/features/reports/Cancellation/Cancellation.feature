Feature: Cancellation Policies

  As A MAR user,
  I want the ability to use generate Cancellation reportz

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Cancellation" Report Template


  @TC-297
  @sync-38405343
  @Cancellation
  Scenario: "Cancellation" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Policy Number" under Filters
    And the user sets the Filter to start with "BE"
    And the user clicks on the Apply button
    Then the user verifies that all the "Policy Number" values on the Report start with "BE"

  @TC-296
  @sync-38405342
  @Cancellation
  Scenario: "Cancellation" report sort option functions properly
    And the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "cancellationReportTemplate"
    And the user saves the template
    And the user clicks the Ok button
    Then the user verifies that the values of "Customer Name" are in descending order
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Customer Name" are in ascending order
    And the user clicks on the Sort symbol
    And the user presses the back arrow
    And the user search for the template named "cancellationReportTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-300
  @sync-38406567
  @Cancellation
  Scenario: Add Cancellation summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Total Cost|
      |Division|
      |Cancel Type|
      |Branch|
    And the user names the summary "cancellationSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "cancellationSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "cancellationSummaryTemplate"
    And the user selects "cancellationSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "cancellationSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "cancellationSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-299
  @sync-38405345
  @Cancellation
  Scenario: Cancellation Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                 |
      | Policy Number                 |
      | Parent Company                |
      | Policy Primary Executive      |
      | Policy Primary Representative |
      | Policy Type                   |
      | Cancel Type                   |
      | Cancel Date                   |
      | Cancel Description            |
      | Cancel Reason                 |
      | Cancel Premium                |
      | Total Cost                    |
      | Policy Full Term Premium      |
      | Active Customer               |
      | Company Type                  |
    And the user selects "Total Cost" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Total Cost  |

