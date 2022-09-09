Feature: Finance Company Aged Accounts Receivable

  As A MAR user,
  I want the ability to use generate a Finance Company Aged Accounts Receivable report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Finance Company Aged Accounts Receivable" Report Template


  @TC-322
  @sync-38659034
  @FinanceCompanyAgedAccountsReceivable
  Scenario: "Finance Company Aged Accounts Receivable" report sort option functions properly
  And the user generates the report with a random name
  When the user navigates to the generated report
  And the user selects "Detail View" tab
  And the user clicks on the Sort symbol
  And the user selects "Z-A" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Finance Company Name" are in descending order
  And the user clicks the cancel button
  And the user clicks on the Sort symbol
  And the user selects "A-Z" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Finance Company Name" are in ascending order
  And the user clicks the cancel button


  @TC-326
  @sync-38659104
  @FinanceCompanyAgedAccountsReceivable
  Scenario: Add Finance Company Aged Accounts Receivable summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Total Invoice Balance|
      |Branch               |
      |Customer Name        |
    And the user names the summary "financeCompanyAgedAccountsSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "financeCompanyAgedAccountsTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "financeCompanyAgedAccountsTemplate"
    And the user selects "financeCompanyAgedAccountsTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "financeCompanyAgedAccountsSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "financeCompanyAgedAccountsTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-325
  @sync-38659084
  @FinanceCompanyAgedAccountsReceivable
  Scenario: Finance Company Aged Accounts Receivable Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Finance Company Name       |
      | Company Phone              |
      | Company Email              |
      | Customer Name              |
      | Invoice Executive          |
      | Invoice Representative     |
      | Policy Number              |
      | Invoice Number             |
      | Invoice Balance            |
      | Invoice Date Age           |
      | Invoice Effective Date Age |
      | Aging Category             |
      | Account Balance            |
    And the user selects "Customer Name" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Customer Name  |

