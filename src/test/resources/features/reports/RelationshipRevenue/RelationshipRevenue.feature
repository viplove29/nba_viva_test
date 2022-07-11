Feature: Relationship Revenue

  As A MAR user,
  I want the ability to use generate Relationship Revenue report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Relationship Revenue" Report Template


  @TC-350
  @sync-38729771
  Scenario: "Relationship Revenue" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Relationship Description" under Filters
    And the user sets the Filter to start with "Gr"
    And the user clicks on the Apply button
    Then the user verifies that all the "Relationship Description" values on the Report start with "Gr"


  @TC-352
  @sync-38729773
  Scenario: Add Relationship Revenue summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Total Premium    |
      | Division         |
      | Role Description |
      | Branch           |
    And the user names the summary "relationshipRevenueSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "relationshipRevenueSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "relationshipRevenueSummaryTemplate"
    And the user selects "relationshipRevenueSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "relationshipRevenueSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "relationshipRevenueSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-353
  @sync-38729776
  Scenario: Relationship Revenue Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name             |
      | Relationship Description  |
      | Role Description          |
      | Policy Number             |
      | Estimated Revenue         |
      | Estimated Revenue Percent |
      | Premium                   |
      | Revenue                   |
      | Annualized Premium        |
    And the user selects "Premium" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Premium |


