Feature: Finance Company Net Balance

  As A MAR user,
  I want the ability to use generate Finance Company Net Balance report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Finance Company Net Balance" Report Template


  @TC-94
  @sync-30863436
  @FinanceCompanyNetBalance
  Scenario: "Finance Company Net Balance" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Finance Company Name" under Filters
    And the user sets the Filter to start with "One"
    And the user clicks on the Apply button
    Then the user verifies that all the "Finance Company Name" values on the Report start with "One"


  @TC-281
  @sync-37630839
  @FinanceCompanyNetBalance
  Scenario: "Finance Company Net Balance" report sort option functions properly
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


  @TC-320
  @sync-38590249
  @FinanceCompanyNetBalance
  Scenario: Add Finance Company Net Balance summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Total Net Balance |
      | Age               |
      | Department        |
      | Branch            |
    And the user names the summary "companyNetBalanceSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "companyNetBalanceSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "companyNetBalanceSummaryTemplate"
    And the user selects "companyNetBalanceSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "companyNetBalanceSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "companyNetBalanceSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-280
  @sync-37629972
  @FinanceCompanyNetBalance
  Scenario: Finance Company Net Balance Check Columns spelling and Show/Hide columns option Receivable Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Finance Company Name        |
      | Customer Number             |
      | Customer Name               |
      | Policy Number               |
      | Policy Type                 |
      | Policy Effective Date       |
      | Division                    |
      | Branch                      |
      | Department                  |
      | Group                       |
      | Invoice General Ledger Date |
      | Invoice Number              |
      | Invoice Effective Date      |
      | Type                        |
      | Description                 |
      | Age                         |
      | Amount                      |
      | Finance Company Balance     |
      | Current Invoice Balance     |
    And the user selects "Department" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Department |

