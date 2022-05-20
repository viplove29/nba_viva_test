Feature: Customer Policy Extract
  
  As A MAR user,
  I want the ability to use generate Customer/Policy Extract report
  
  Background: 
    Given the user logs into MAR as "RegularUser"
    And the user selects "Customer/Policy Extract" Report Template

  @TC-523
  @sync-53071667
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

  @TC-522
  @sync-53071667
  @TC-526
  @sync-53079875
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