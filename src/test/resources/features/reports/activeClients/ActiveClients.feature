Feature: Active Clients

  As a MAR user,
  I want the ability to use generate Active Clients reports
  So that I can use that information

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Active Clients" Report Template
    And the user generates the report with a random name

  @TC-90
  @sync-30863379
  Scenario: Quick Actions to Excel Exports Properly
    When the user clicks on the Quick Actions "XLS" button
    And the user navigates to the generated report
    Then the excel report has the same number of rows as the web report

  @TC-289
  @sync-38373633
  Scenario Outline: Export to Excel/CSV File Format Options
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user selects "Export Report" under Actions
    And use user exports the report with the following options
      | <File Format> | <Permission> | <CoverSheet> | Visible | Full Data Set |
    Then the exported report is a "<File Format>" file format
    Then the exported report has "<Permission>" permission if applicable
    Then the exported report has a "<CoverSheet>" cover sheet if applicable
    Then the exported report has the same columns as the web report

    Examples:
      | File Format | Permission | CoverSheet |
      | CSV         |            |            |
      | Excel       | Editable   | false      |
      | Excel       | Locked     | true       |
      | Excel       | Both       | false      |

  @TC-97
  @sync-30863439
  Scenario Outline: Business Unit columns appear together in the proper order
    When the user navigates to the generated report
    And the user selects "<Options>" under the hide show icon
    Then the user verifies the "<Policies>" are displayed in the detail view tab

    Examples:
      | Options           | Policies   |
      | Policy Division   | Division   |
      | Policy Branch     | Branch     |
      | Policy Department | Department |
      | Policy Group      | Group      |

  @TC-152
  @sync-30863494
  Scenario Outline: Branch and Group are not available when not enabled in source database
    When the user navigates to the generated report
    And the user selects "<Options>" under the hide show icon
    Then the user verifies the "<Selected Policies>" are displayed in the detail view tab

    Examples:
      | Options           | Selected Policies |
      | Policy Department | Department        |
      | Policy Group      | Group             |

  @TC-288
  @sync-38373567
  Scenario: Active Clients Filters option functions properly
    When the user navigates to the generated report
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Active Customer" under Filters
    And the user sets the Filter to start with "I"
    And the user clicks on the Apply button
    Then the user verifies that all the "Active Customer" values on the Report are equal to "Inactive"

  @TC-287
  @sync-38373492
  Scenario: "Active Clients" report sort option functions properly
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user verifies that the values of "Customer Name" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    And the user verifies that the values of "Customer Name" are in ascending order
    And the user clicks the cancel button

  @TC-301
  @sync-38406567
  Scenario: Active Clients Add new Summary View tab in the report
    When the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Customer Executive|
      |Policy Count      |
      |Bill Method       |
      |Branch            |
    And the user names the summary "testSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "testSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "testSummaryTemplate"
    And the user selects "testSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "testSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "testSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item





  @TC-551
  @sync-55481207
  Scenario: Verify Functionality of BU Filters in Active Client Report in MAR
    And the user selects "Active Clients" Report Template
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    And the user selects the "All" option under the Status section
    Then the user verifies all the options in the dropdown from the "Branch" category are in the "April Division" division
    Then the user verifies all the options in the dropdown from the "Department" category are in the "April Division" division
    Then the user verifies all the options in the dropdown from the "Group" category are in the "April Division" division
