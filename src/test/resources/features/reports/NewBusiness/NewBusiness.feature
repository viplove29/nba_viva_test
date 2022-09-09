Feature: New Business

  As A MAR user,
  I want the ability to use generate New Business report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "New Business" Report Template


  @TC-338
  @sync-38702114
  Scenario: "New Business" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Division" under Filters
    And the user sets the Filter to start with "Div"
    And the user clicks on the Apply button
    Then the user verifies that all the "Division" values on the Report start with "Div"


  @TC-337
  @sync-38702112
  Scenario: "New Business" report sort option functions properly
    And the user selects Current year to date date range from list
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Parent Company" under drop down
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "newBusinessTemplate"
    And the user saves the template
    And the user clicks the Ok button
    Then the user verifies that the values of "Parent Company" are in descending order
    And the user clicks on the Sort symbol
    And the user selects "Parent Company" under drop down
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Parent Company" are in ascending order
    And the user clicks on the Sort symbol
    And the user presses the back arrow
    And the user search for the template named "newBusinessTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-341
  @sync-38702126
  Scenario: Add New Business summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Total Billed Premium |
      | Division             |
      | Cancel Reason        |
      | Branch               |
    And the user names the summary "newBusinessSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "newBusinessSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "newBusinessSummaryTemplate"
    And the user selects "newBusinessSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "newBusinessSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "newBusinessSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-340
  @sync-38702124
  Scenario: New Business Check Columns spelling and Show/Hide columns option
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
      | Customer Added Date          |
      | Primary Executive            |
      | Primary Representative       |
      | Current Broker Name          |
      | Parent Company               |
      | Writing Company              |
      | Revenue                      |
      | Premium                      |
      | Annualized Premium           |
    And the user selects "Revenue" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Revenue |

  @TC-587
  @sync-116430385
  @NewBusiness
  Scenario: "New Business" report displaying correct data after exporting to excel
    Then the user sets date range from "01/01/2022" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Policy Effective Date" under Filters
    And the user sets the Filter to range from "01/01/2022" to today
    And the user clicks on the Apply button
    And the user selects "Export Report" under Actions
    And use user exports the report with the following options
      | Excel | Editable | False | Visible | Full Data Set |
    Then the exported report is a "Excel" file format
    Then the exported report has the same columns as the web report
    And the user selects "Export Report" under Actions
    And use user exports the report with the following options
      | CSV |        |        | Visible | Full Data Set |
    Then the exported report is a "CSV" file format
    Then the exported report has the same columns as the web report
