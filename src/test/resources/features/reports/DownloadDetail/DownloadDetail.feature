Feature: Download Detail

  As A MAR user,
  I want the ability to use generate Download Detail report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Download Detail" Report Template


  @TC-374
  @sync-38827194
  Scenario: "Download Detail" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Parent Company" under Filters
    And the user sets the Filter to start with "Chu"
    And the user clicks on the Apply button
    Then the user verifies that all the "Parent Company" values on the Report start with "Chu"

  @TC-373
  @sync-38827188
  Scenario: "Download Detail" report sort option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "downloadDetailTemplate"
    And the user saves the template
    And the user clicks the Ok button
    Then the user verifies that the values of "Parent Company" are in descending order
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Parent Company" are in ascending order
    And the user clicks on the Sort symbol
    And the user presses the back arrow
    And the user search for the template named "downloadDetailTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-377
  @sync-38827325
  Scenario: Add Download Detail summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Transaction Status Count |
      | Master Agent Code        |
      | Sub Agent Code           |
      | Branch                   |
    And the user names the summary "downloadDetailSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "downloadDetailSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "downloadDetailSummaryTemplate"
    And the user selects "downloadDetailSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "downloadDetailSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "downloadDetailSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-376
  @sync-38827301
  Scenario: Download Detail Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Parent Company              |
      | Process Date                |
      | Message Sequence Number     |
      | Transaction Sequence Number |
      | Customer Name               |
      | Policy Number               |
      | Policy Effective Date       |
      | Policy Expiration Date      |
      | Line of Business            |
      | Primary Executive           |
      | Primary Representative      |
      | Division                    |
      | Branch                      |
      | Department                  |
      | Group                       |
      | Transaction Effective Date  |
      | Transaction Description     |
      | Transaction Status          |
      | Report Category             |
      | Message                     |
    And the user selects "Transaction Status" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Transaction Status |



