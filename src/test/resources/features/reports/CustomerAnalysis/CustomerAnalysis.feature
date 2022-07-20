Feature: Customer Analysis

  As A MAR user,
  I want the ability to use generate Customer Analysis report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Customer Analysis" Report Template


  @TC-308
  @sync-38527152
  Scenario: "Customer Analysis" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Accardi"
    And the user clicks on the Apply button
    And the user extracts all the report values in the grid
    Then the user verifies that all the "Customer Name" values on the Report start with "Accardi"


  @TC-311
  @sync-38527156
  Scenario: Add Customer Analysis summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Customer Count|
      |Division|
      |Broker Name|
      |Branch|
    And the user names the summary "customerAnalysisSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "customerAnalysisSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "customerAnalysisSummaryTemplate"
    And the user selects "customerAnalysisSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "customerAnalysisSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "customerAnalysisSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-310
  @sync-38527154
  Scenario: Customer Analysis Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                    |
      | Customer Number                  |
      | Customer Address 1               |
      | Customer Address 2               |
      | Customer City                    |
      | Customer State                   |
      | Active Customer                  |
      | Customer Email                   |
      | Customer Phone Number - Business |
      | Customer Added Date              |
      | Customer Type                    |
      | Known Since                      |
      | Multi-Entity Group               |
      | Customer Executive               |
      | Customer Representative          |
      | Broker Name                      |
      | Notation Description             |
      | Division                         |
      | Branch                           |
      |  Department                      |
    And the user selects "Customer Name" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Customer Name |

  @TC-558
  @sync-55872134
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in Customer Analysis Report in MAR
    And the user expands "Business Unit" filter
    Then the user searches for "JJ Inactive" in the "Division" section dropdown and verifies all of the statuses are equal to "Inactive"
    Then the user searches for "JJ Active" in the "Division" section dropdown and verifies all of the statuses are equal to "Active"
    And the user selects the "Active" option under the Status section
    Then the user searches for "JJ Inactive" in the "Division" section dropdown and verifies the name does not show
    Then the user searches for "JJ Active" in the "Division" section dropdown and verifies all of the statuses are equal to "Active"
    And the user selects "JJ Active" from "Division" category
    And the user selects the "Active" option under the Status section
    Then the user verifies all of the statuses in the "Branch" section dropdown are set to "Active"
    Then the user verifies all of the statuses in the "Department" section dropdown are set to "Active"
    Then the user verifies all of the statuses in the "Group" section dropdown are set to "Active"
    And the user selects the "Inactive" option under the Status section
    Then the user searches for "JJ Active" in the "Division" section dropdown and verifies the name does not show
    Then the user searches for "JJ Inactive" in the "Division" section dropdown and verifies all of the statuses are equal to "Inactive"
    And the user selects "JJ Inactive" from "Division" category
    And the user selects the "Inactive" option under the Status section
    Then the user verifies all of the statuses in the "Branch" section dropdown are set to "Inactive"
    Then the user verifies all of the statuses in the "Department" section dropdown are set to "Inactive"
    Then the user verifies all of the statuses in the "Group" section dropdown are set to "Inactive"
