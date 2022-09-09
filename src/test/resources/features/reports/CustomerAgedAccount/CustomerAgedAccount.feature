Feature: Customer Aged Account

  As A MAR user,
  I want the ability to use generate Customer Aged Account report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Customer Aged Accounts Receivable" Report Template


  @TC-303
  @sync-38477963
  @CustomerAgedAccount
  Scenario: "Customer Aged Account" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Cust"
    And the user clicks on the Apply button
    And the user extracts all the report values in the grid
    Then the user verifies that all the "Customer Name" values on the Report start with "Cust"


  @TC-306
  @sync-38478006
  @CustomerAgedAccount
  Scenario: Add Customer Aged Account summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Total Invoice Balance|
      |Division|
      |Invoice Date Age|
      |Branch|
    And the user names the summary "customerAgedAccountSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "cAASummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "cAASummaryTemplate"
    And the user selects "cAASummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "customerAgedAccountSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "cAASummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-305
  @sync-38477992
  @CustomerAgedAccount
  Scenario: Customer Aged Account  Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                       |
      | Customer Phone Number - Residential |
      | Customer Phone Number - Business    |
      | Invoice Executive                   |
      | Invoice Representative              |
      | Policy Number                       |
      | Invoice Number                      |
      | Invoice Balance                     |
      | Invoice Date Age                    |
      | Invoice Effective Date Age          |
      | Aging Category                      |
      | Late Charge                         |
      | Last Invoice Payment Date           |
      | Last Invoice Payment Amount         |
      | Customer Balance                    |
    And the user selects "Customer Name" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Customer Name  |


