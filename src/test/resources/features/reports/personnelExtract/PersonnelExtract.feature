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
      | Customer Name          |
      | Customer Number        |
      | Policy Number          |
      | Policy Id              |
      | Current Personnel Name |
      | Current Personnel Type |
      | Type of Business       |
      | Primary                |
      | New Personnel Name     |
      | New Personnel Type     |

  @TC-532
  @sync-53235678
  Scenario: Customer Personnel Extract tab check columns spelling and Show/Hide columns option
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Personnel Extract" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name          |
      | Customer Number        |
      | Current Personnel Name |
      | Current Personnel Type |
      | Primary                |
      | New Personnel Name     |
      | New Personnel Type     |


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
    Then the user verifies that all the Current Personnel Type values are equal to "Rep"

  @TC-533
  @sync-53237065
  Scenario Outline: "Personnel Extract" report export to Excel File Format Options
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Customer Personnel Extract" tab
    And the user selects "Export Report" under Actions
    And use user exports the report with the following options
      | <File Format> | <Permission> | <CoverSheet> | Visible | Full Data Set |
    Then the report is a "<File Format>"
    Then the report is "<Permission>" if applicable
    Then the report has a "<CoverSheet>" if applicable
    Then the report has the same columns as the web report

    Examples:
      | File Format | Permission | CoverSheet |
      | Excel       | Editable   | false      |
      | Excel       | Locked     | true       |

