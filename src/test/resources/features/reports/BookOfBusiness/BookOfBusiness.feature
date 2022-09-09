Feature: Book of Business

  As A MAR user,
  I want the ability to use generate Book of Business report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Book of Business" Report Template

  @TC-557
  @sync-55872102
  @Version-22R2
  @BookOfBusiness
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in Book of Business Report in MAR
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

  @TC-564
  @sync-56015237
  @Version-22R2
  @BookOfBusiness
  Scenario: Data Validation Using New BU Active/Inactive Filters - Book of Business (MAR)
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
    And the user selects "Book of Business" Report Template
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
