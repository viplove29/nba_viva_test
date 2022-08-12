Feature: Subsidiary Aged Ledger

  As A MAR user,
  I want the ability to use generate a Subsidiary Aged Ledger report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Subsidiary Aged Ledger" Report Template


  @TC-282
  @sync-38124631
  Scenario: "Subsidiary Aged Ledger" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Division" under Filters
    And the user sets the Filter to start with "Div"
    And the user clicks on the Apply button
    Then the user verifies that all the "Division" values on the Report start with "Div"

  @TC-283
  @sync-38124666
  Scenario: "Subsidiary Aged Ledger" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Large-Small" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "GL Number" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "Small-Large" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "GL Number" are in ascending order
    And the user clicks the cancel button

  @TC-550
  @sync-55355857
  @Version-22R2
  Scenario: Verify Functionality of BU Filters in Subsidiary Aged Ledger Report in MAR
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    And the user selects the "All" option under the Status section
    Then the user verifies all the options in the dropdown from the "Branch" category are in the "April Division" division
    Then the user verifies all the options in the dropdown from the "Department" category are in the "April Division" division
    Then the user verifies all the options in the dropdown from the "Group" category are in the "April Division" division

  @TC-219
  @sync-30863620
  Scenario: Subsidiary Aged Ledger Check Columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | GL Number                     |
      | Account Name                  |
      | Subledger Name                |
      | Division                      |
      | Branch                        |
      | Department                    |
      | Customer Name                 |
      | Policy Number                 |
      | Invoice Number                |
      | Invoice Date                  |
      | Invoice Date Age              |
      | Invoice Effective Date        |
      | Invoice Effective Date Age    |
      | Aging Category                |
      | Vendor Payment Amount Balance |
      | Vendor Invoice Amount         |
      | Transaction Amount            |
      | GL Balance                    |
    And the user selects "Aging Category" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Aging Category |

  @TC-549
  @sync-55355856
  @Version-22R2
  Scenario: Data Validation Using New BU Filters for the Subsidiary Aged Ledger in MAR
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    And the user selects the "All" option under the Status section
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "April Division"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "April Division"

  @TC-563
  @sync-55872262
  @Version-22R2
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in Subsidiary Aged Ledger Report in MAR
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

  @TC-571
  @sync-56019136
  @Version-22R2
  Scenario: Data Validation Using New BU Active/Inactive Filters - Subsidiary Aged Ledger (MAR)
    And the user expands "Business Unit" filter
    And the user selects the "All" option under the Status section
    And the user selects "JJ Active" from "Division" category and closes the dropdown
    And the user selects "JJ Active 1" from "Branch" category and closes the dropdown
    And the user selects "JJ Active 1" from "Department" category and closes the dropdown
    And the user selects "JJ Active 1" from "Group" category and closes the dropdown
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Active"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Active 1"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Active"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Active 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Active 1"
    And the user selects "Subsidiary Aged Ledger" Report Template
    And the user expands "Business Unit" filter
    And the user selects the "Inactive" option under the Status section
    And the user selects "JJ Inactive" from "Division" category and closes the dropdown
    And the user selects "JJ Inactive 1" from "Branch" category and closes the dropdown
    And the user selects "JJ Inactive 1" from "Department" category and closes the dropdown
    And the user selects "JJ Inactive 1" from "Group" category and closes the dropdown
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Inactive"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Inactive 1"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "JJ Inactive"
    Then the user verifies all of the data under the "Branch" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Department" header in the file from the generated report is set to "JJ Inactive 1"
    Then the user verifies all of the data under the "Group" header in the file from the generated report is set to "JJ Inactive 1"