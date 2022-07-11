Feature: Policy Expiration

  As A MAR user,
  I want the ability to use generate a Policy Expiration report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Policy Expiration" Report Template


  @TC-343
  @sync-38702524
  Scenario: "Policy Expiration" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "107"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values on the Report start with "107"

  @TC-342
  @sync-38702522
  Scenario: "Policy Expiration" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Customer Name" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Customer Name" are in ascending order
    And the user clicks the cancel button


  @TC-346
  @sync-38702533
  Scenario: Add Policy Expiration summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Total Cost          |
      | Policy Branch       |
      | Writing Company     |
      | Company Underwriter |
    And the user names the summary "policyExpirationSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "policyExpirationSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "policyExpirationSummaryTemplate"
    And the user selects "policyExpirationSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "policyExpirationSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "policyExpirationSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-345
  @sync-38702532
  Scenario: Policy Expiration Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                              |
      | Renewal Contact Name                       |
      | Renewal Contact Phone Number - Residential |
      | Renewal Contact Phone Number - Business    |
      | Renewal Contact Email                      |
      | Policy Number                              |
      | Policy Effective Date                      |
      | Policy Expiration Date                     |
      | Parent Company                             |
      | Primary Executive                          |
      | Primary Representative                     |
      | Policy Status                              |
      | Policy Type LOB                            |
      | Bill Method                                |
      | Business With Agency                       |
      | Total Cost                                 |
      | Policy Full Term Premium                   |
      | Line Of Business                           |
    And the user selects "Policy Type LOB" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Policy Type LOB |



