Feature: Broker Aged Accounts Receivable

As A MAR user,
I want the ability to use generate Broker Aged Accounts Receivable report

Background:
Given the user logs into MAR as "RegularUser"
And the user selects "Broker Aged Accounts Receivable" Report Template


  @TC-338
  @sync-38702114
  @BrokerAgedAccountsReceivable
  Scenario: "Broker Aged Accounts Receivable" report Filters option functions properly
  And the user generates the report with a random name
  When the user navigates to the generated report
  And the user selects "Detail View" tab
  And the user clicks on the Filter symbol
  And the user clicks on the Add Filters button
  And the user selects "Broker Name" under Filters
  And the user sets the Filter to start with "BCD"
  And the user clicks on the Apply button
  Then the user verifies that all the "Broker Name" values on the Report start with "BCD"

  @TC-273
  @sync-37288426
  @BrokerAgedAccountsReceivable
  @SmokeTest
  Scenario: Broker Aged Accounts Receivable Add new Summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Aging Category|
      |Total Invoice Balance|
      |Invoice Representative|
    And the user names the summary "brokerAgedAccountsSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "BrokerAgedAccountsSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "BrokerAgedAccountsSummaryTemplate"
    And the user selects "BrokerAgedAccountsSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "brokerAgedAccountsSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "BrokerAgedAccountsSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item




  @TC-272
  @sync-37286304
  @BrokerAgedAccountsReceivable
  Scenario: "Broker Aged Accounts Receivable" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Broker Name" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Broker Name" are in ascending order
    And the user clicks the cancel button


  @TC-270
  @sync-37285637
  @BrokerAgedAccountsReceivable
  Scenario: Broker Ages Accounts Receivable Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Broker Name                 |
      | Customer Name               |
      | Invoice Executive           |
      | Invoice Representative      |
      | Policy Number               |
      | Invoice Number              |
      | Invoice Balance             |
      | Invoice Date Age            |
      | Invoice Effective Date Age  |
      | Aging Category              |
      | Late Charge                 |
      | Last Invoice Payment Date   |
      | Last Invoice Payment Amount |
      | Division                    |
      | Branch                      |
      | Department                  |
      | Group                       |
      | Account Balance             |
    And the user selects "Broker Name" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Broker Name                |


