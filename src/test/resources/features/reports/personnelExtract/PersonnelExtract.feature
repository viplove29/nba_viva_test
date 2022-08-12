Feature: Personnel Extract

  As A MAR user,
  I want the ability to use generate Personnel Extract report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Personnel Extract" Report Template

  @TC-531
  @sync-53235101
  Scenario: Policy Personnel Extract tab check columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Policy Personnel Extract" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                       |
      | Customer Number                     |
      | Policy Number                       |
      | Policy Id                           |
      | Current Personnel Name              |
      | Current Personnel Type              |
      | Type of Business                    |
      | Primary                             |
      | New Personnel Name (optional)       |
      | New Personnel Short Name (required) |

  @TC-532
  @sync-53235678
  Scenario: Customer Personnel Extract tab check columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Personnel Extract" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                       |
      | Customer Number                     |
      | Current Personnel Name              |
      | Current Personnel Type              |
      | Primary                             |
      | New Personnel Name (optional)       |
      | New Personnel Short Name (required) |


  @TC-530
  @sync-53235043
  @TC-534
  @sync-53237414
  Scenario: "Personnel Extract" report Filters option functions properly
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    And the user expands "Business Unit Association" filter
    And the user selects "Customer" association option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Personnel Extract" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Current Personnel Type" under Filters
    And the user sets the Filter to start with "Rep"
    And the user clicks on the Apply button
    Then the user verifies that all the "Current Personnel Type" values on the Report are equal to "Rep"

  @TC-533
  @sync-53237065
  Scenario Outline: "Personnel Extract" report export to Excel File Format Options
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Personnel Extract" tab
    And the user selects "Export Report" under Actions
    And use user exports the report with the following options
      | <File Format> | <Permission> | <CoverSheet> | Visible | Full Data Set |
    Then the exported report is a "<File Format>" file format
    Then the exported report has "<Permission>" permission if applicable
    Then the exported report has a "<CoverSheet>" cover sheet if applicable
    Then the exported report has the same columns as the web report

    Examples:
      | File Format | Permission | CoverSheet |
      | Excel       | Editable   | false      |
      | Excel       | Locked     | true       |

  @TC-561
  @sync-55872224
  @Version-22R2
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in Personnel Extract Report in MAR
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

  @TC-569
  @sync-56019105
  @Version-22R2
  Scenario: Data Validation Using New BU Active/Inactive Filters - Personnel Extract (MAR)
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
    And the user selects "Personnel Extract" Report Template
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
