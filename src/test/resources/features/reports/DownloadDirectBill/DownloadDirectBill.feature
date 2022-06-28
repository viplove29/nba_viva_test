Feature: Download Direct Bill Statement

  As A MAR user,
  I want the ability to use generate Download Direct Bill Statement report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Download Direct Bill Statement" Report Template


  @TC-379
  @sync-38827957
  Scenario: "Download Direct Bill Statement" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Parent Company" under Filters
    And the user sets the Filter to start with "CNA"
    And the user clicks on the Apply button
    Then the user verifies that all the "Parent Company" values on the Report start with "CNA"

  @TC-378
  @sync-38827955
  Scenario: "Download Direct Bill Statement" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Statement Division" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Statement Division" are in ascending order
    And the user clicks the cancel button


  @TC-382
  @sync-38827963
  Scenario: Add Download Direct Bill summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Total Gross Amount|
      |Department|
      |Income Group|
      |Branch|
    And the user names the summary "downloadDirectBillSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "downloadDirectBillSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "downloadDirectBillSummaryTemplate"
    And the user selects "downloadDirectBillSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "downloadDirectBillSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "downloadDirectBillSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

