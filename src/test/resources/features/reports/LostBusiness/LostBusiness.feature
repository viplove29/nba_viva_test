Feature: Lost Business

  As A MAR user,
  I want the ability to use generate Lost Business report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Lost Business" Report Template


  @TC-333
  @sync-38666740
  Scenario: "Lost Business" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Customer"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values on the Report start with "Customer"


  @TC-332
  @sync-38666738
  Scenario: "Lost Business" report sort option functions properly
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


  @TC-336
  @sync-38666745
  Scenario: Add Lost Business summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Total Premium|
      |Division|
      |Cancel Reason|
      |Branch|
    And the user names the summary "lostBusinessSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "lostBusinessSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "lostBusinessSummaryTemplate"
    And the user selects "lostBusinessSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "lostBusinessSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "lostBusinessSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


