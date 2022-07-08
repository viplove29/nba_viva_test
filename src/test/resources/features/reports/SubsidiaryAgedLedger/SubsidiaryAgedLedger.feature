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
  Scenario: Verify Functionality of BU Filters in Subsidiary Aged Ledger Report in MAR
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    And the user selects the "All" option under the Status section
    Then the user verifies all the options in the dropdown from the "Branch" category are in the "April Division" division
    Then the user verifies all the options in the dropdown from the "Department" category are in the "April Division" division
    Then the user verifies all the options in the dropdown from the "Group" category are in the "April Division" division

  @TC-549
  @sync-55355856
  Scenario: Data Validation Using New BU Filters for the Subsidiary Aged Ledger in MAR
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    And the user selects the "All" option under the Status section
    And the user generates the report with a random name
    And the user clicks on the Quick Actions "CSV" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "April Division"
    And the user clicks on the Quick Actions "XLS" button
    Then the user verifies all of the data under the "Division" header in the file from the generated report is set to "April Division"