Feature: Active Clients

  As a MAR user,
  I want the ability to use generate Active Clients reports
  So that I can use that information

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects 'Active Clients' Report Template
    And the user generates the report with a random name

  @TC-90
  @sync-30863379
  Scenario: Quick Actions to Excel Exports Properly
    When the user clicks on the Quick Actions 'XLS' button
    And the user navigates to the generated report
    Then the excel report has the same number of rows as the web report

  @TC-289
  @sync-38373633
  Scenario Outline: Export to Excel/CSV File Format Options
    When the user navigates to the generated report
    And the user selects 'Export Report' under Actions
    And use user exports the report with the following options
      | <File Format> | <Permission> | <CoverSheet> | Visible | Full Data Set |
    Then the report is a '<File Format>'
    Then the report is '<Permission>' if applicable
    Then the report has a '<CoverSheet>' if applicable
    Then the report has the same columns as the web report

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
    And the user selects '<Options>' under the hide show icon
    Then the user verifies the '<Policies>' are displayed in the detail view tab

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
    And the user selects '<Options>' under the hide show icon
    Then the user verifies the '<Selected Policies>' are displayed in the detail view tab

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
    And the user selects 'Active Customer' under Filters
    And the user sets the Filter to start with 'I'
    And the user clicks on the Apply button
    Then the user verifies that all the Active Customer values are equal to 'Inactive'

