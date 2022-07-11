Feature: Customer Net Balance Policies

  As A MAR user,
  I want the ability to use generate Customer Net Balance reports

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Customer Net Balance" Report Template



  @TC-276
  @sync-37443848
  Scenario: "Customer Net Balance" report sort option functions properly
  And the user generates the report with a random name
  When the user navigates to the generated report
  And the user selects "Detail View" tab
  And the user clicks on the Sort symbol
  And the user selects "Policy Type" under drop down
  And the user selects "Z-A" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Policy Type" are in descending order
  And the user clicks the cancel button
  And the user clicks on the Sort symbol
  And the user selects "Policy Type" under drop down
  And the user selects "A-Z" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Policy Type" are in ascending order
  And the user clicks the cancel button

  @TC-313
  @sync-38582373
  Scenario: "Customer Net Balance" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Division" under Filters
    And the user sets the Filter to start with "Div"
    And the user clicks on the Apply button
    Then the user verifies that all the "Division" values on the Report start with "Div"


  @TC-316
  @sync-38582376
  Scenario: Add Customer Net Balance summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Total Net Balance|
      |Age|
      |Department|
      |Branch|
    And the user names the summary "customerNetBalanceSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "customerNetBalanceSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "customerNetBalanceSummaryTemplate"
    And the user selects "customerNetBalanceSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "customerNetBalanceSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "customerNetBalanceSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-315
  @sync-38582375
  Scenario: Customer Net Balance Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Number             |
      | Customer Name               |
      | Policy Number               |
      | Policy Type                 |
      | Policy Effective Date       |
      | Policy Expiration Date      |
      | Division                    |
      | Branch                      |
      | Department                  |
      | Invoice General Ledger Date |
      | Invoice Number              |
      | Invoice Effective Date      |
      | Type                        |
      | Description                 |
      | Age                         |
      | Amount                      |
      | Customer Balance            |
      | Current Invoice Balance     |
    And the user selects "Age" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Age |


