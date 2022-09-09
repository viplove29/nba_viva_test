Feature: Unbilled Policies

  As A MAR user,
  I want the ability to use generate Unbilled Policies report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Unbilled Policies" Report Template


  @TC-364
  @sync-38737417
  @UnbilledPolicies
  Scenario: "Unbilled Policies" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Policy Number" under Filters
    And the user sets the Filter to start with "CL01"
    And the user clicks on the Apply button
    Then the user verifies that all the "Policy Number" values on the Report start with "CL01"

  @TC-363
  @sync-38737412
  @UnbilledPolicies
  Scenario: "Unbilled Policies" report sort option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "unbilledPoliciesTemplate"
    And the user saves the template
    And the user clicks the Ok button
    Then the user verifies that the values of "Division" are in descending order
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Division" are in ascending order
    And the user clicks on the Sort symbol
    And the user presses the back arrow
    And the user search for the template named "unbilledPoliciesTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-367
  @sync-38737426
  @UnbilledPolicies
  Scenario: Add Unbilled Policies summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Policy Count     |
      | Division         |
      | Type of Business |
      | Branch           |
    And the user names the summary "unbilledPoliciesSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "unbilledPoliciesSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "unbilledPoliciesSummaryTemplate"
    And the user selects "unbilledPoliciesSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "unbilledPoliciesSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "unbilledPoliciesSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-366
  @sync-38737425
  @UnbilledPolicies
  Scenario: Unbilled Policies Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Division                      |
      | Customer Name                 |
      | Policy Number                 |
      | Transaction Effective Date    |
      | Line of Business/Non-Premium  |
      | Parent Company                |
      | Policy Primary Executive      |
      | Policy Primary Representative |
      | Unbilled Premium/Non-Premium  |
      | Billed Premium/Non-Premium    |
      | Policy Expiration Date        |
      | Estimated Revenue             |
      | Estimated Revenue Percent     |
    And the user selects "Parent Company" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Parent Company |



