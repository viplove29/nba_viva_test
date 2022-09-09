Feature: Customer Policy Extract
  
  As A MAR user,
  I want the ability to use generate Customer/Policy Extract report
  
  Background: 
    Given the user logs into MAR as "RegularUser"
    And the user selects "Customer/Policy Extract" Report Template

  @TC-523
  @sync-53071667
  @CustomerPolicyExtract
  Scenario: Customer Extract tab check columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Extract" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                |
      | Active Customer              |
      | Customer Number              |
      | Customer Id                  |
      | Customer Revenue (Earned)    |
      | Customer Revenue (Estimated) |
      | Customer Executive           |
      | Customer Representative      |
      | Broker Name                  |
      | Relationship                 |
      | Customer Type                |
      | Current Customer Division    |
      | Current Customer Branch      |
      | Current Customer Department  |
      | Current Customer Group       |
      | New Customer Division        |
      | New Customer Branch          |
      | New Customer Department      |
      | New Customer Group           |

  @TC-524
  @sync-53071667
  @CustomerPolicyExtract
  Scenario: Policy Extract tab check columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Policy Extract" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                |
      | Customer Number              |
      | Policy Revenue (Earned)      |
      | Policy Revenue (Estimated)   |
      | Policy Number                |
      | Line Of Business             |
      | Policy Effective Date        |
      | Policy Expiration Date       |
      | Type of Business             |
      | Policy Status                |
      | Policy Executive             |
      | Policy Representative        |
      | Policy Broker                |
      | Policy Id                    |
      | Current Policy Division      |
      | Current Policy Branch        |
      | Current Policy Department    |
      | Current Policy Group         |
      | New Policy Division          |
      | New Policy Branch            |
      | New Policy Department        |
      | New Policy Group             |

  @TC-525
  @sync-53079560
  @CustomerPolicyExtract
  Scenario Outline: "Customer/Policy Extract" report export to Excel File Format Options
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Extract" tab
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

  @TC-560
  @sync-55872146
  @Version-22R2
  @CustomerPolicyExtract
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in Customer/Policy Extract Report in MAR
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

  @TC-567
  @sync-56018850
  @Version-22R2
  @CustomerPolicyExtract
  Scenario: Data Validation Using New BU Active/Inactive Filters - Customer Policy Extract (MAR)
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
    And the user selects "Customer/Policy Extract" Report Template
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

  @TC-522
  @sync-53071667
  @TC-526
  @sync-53079875
  @CustomerPolicyExtract
  Scenario: "Customer/Policy Extract" report Filters option functions properly
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    And the user expands "Business Unit Association" filter
    And the user selects "Customer" association option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Extract" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Type" under Filters
    And the user sets the Filter to equal to "Master"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Type" values on the Report are equal to "Master"

