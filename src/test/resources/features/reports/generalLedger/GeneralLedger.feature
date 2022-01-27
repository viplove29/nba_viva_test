Feature: General Ledger

  As a MAR user,
  I want the ability to use generate General Ledger reports

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "General Ledger" Report Template

  @TC-536
  @sync-53674482
  Scenario: Verify updated General ledger modal
    Then the user sets date range from "01/01/2021" to "12/31/2021"
    And the user expands "Business Unit" filter
    And the user selects "April Division" from "Division" category
    Then the user verifies the following options are displayed in Numbers dropdown
      | (All)                     |
      | Balance Sheet Accounts    |
      | Income & Expense Accounts |
      | Range of Accounts         |
      | Specific Account Number   |