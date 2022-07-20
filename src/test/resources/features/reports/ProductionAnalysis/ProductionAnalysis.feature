Feature: Production Analysis

  As A MAR user,
  I want the ability to use generate Feature: Production Analysis

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Production Analysis" Report Template


  @TC-285
  @sync-38233623
  Scenario: "Production Analysis" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Division" under Filters
    And the user sets the Filter to start with "Div"
    And the user clicks on the Apply button
    Then the user verifies that all the "Division" values on the Report start with "Div"

  @TC-284
  @sync-38233581
  Scenario: "Production Analysis" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Division" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Division" are in ascending order
    And the user clicks the cancel button

  @TC-240
  @sync-30863642
  Scenario: Production Analysis Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Date Range                    |
      | Division                      |
      | Branch                        |
      | Department                    |
      | Customer Name                 |
      | Policy Number                 |
      | Policy Status                 |
      | Premium                       |
      | Revenue                       |
      | Gross Profit                  |
      | Parent Company                |
      | Customer Business Origin Code |
      | Invoice GL Month              |
    And the user selects "Premium" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Premium |

  @TC-559
  @sync-55872138
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in Production Analysis Report in MAR
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