Feature: Download Unassigned Accounting Transactions

  As A MAR user,
  I want the ability to use generate Feature: Download Unassigned Accounting Transactions

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Download Unassigned Accounting Transactions" Report Template


  @TC-318
  @sync-38582955
  Scenario: "Download Unassigned Accounting Transactions" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Sean"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values on the Report start with "Sean"

  @TC-317
  @sync-38582954
  Scenario: "Download Unassigned Accounting Transactions" report sort option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "downloadUnassignedAccountingTransactionsTemplate"
    And the user saves the template
    And the user clicks the Ok button
    Then the user verifies that the values of "Parent Company" are in descending order
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Parent Company" are in ascending order
    And the user clicks on the Sort symbol
    And the user presses the back arrow
    And the user search for the template named "downloadUnassignedAccountingTransactionsTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-319
  @sync-38582956
  Scenario: Add Download Unassigned Accounting Transactions summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Total Amount|
      |Status|
      |Writing Company|
      |Master Agent|
    And the user names the summary "downloadUnassignedAccountingTransactionsSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "downloadUnassignedAccountingTransactionsTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "downloadUnassignedAccountingTransactionsTemplate"
    And the user selects "downloadUnassignedAccountingTransactionsTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "downloadUnassignedAccountingTransactionsSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "downloadUnassignedAccountingTransactionsTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-62
  @sync-30863351
  Scenario: Download Unassigned Accounting Transactions Check Columns spelling and Show/Hide columns option
    When the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Parent Company   |
      | Customer Name    |
      | Policy Number    |
      | Status           |
      | Sequence Number  |
      | Transaction Type |
      | Expiration Date  |
      | Policy Type      |
      | Writing Company  |
      | Master Agent     |
      | Sub Agent        |
      | Statement Date   |
      | Entered Date     |
      | Amount           |
    And the user selects "Entered Date" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Entered Date |

