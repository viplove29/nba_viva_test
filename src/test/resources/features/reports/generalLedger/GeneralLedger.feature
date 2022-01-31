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

  @TC-537
  @sync-53675629
  Scenario: Verify Range of Accounts option from Account Numbers/Sub-ledgers section
    Then the user selects "Range of Accounts" from "Numbers" dropdown under Account Numbers/Sub-ledgers section
    And the user selects "11120000 - Premium Trust Account" from "Ending" dropdown under Account Numbers/Sub-ledgers section
    And the user selects "91000000 - Tax Detail" from "Beginning" dropdown under Account Numbers/Sub-ledgers section
    And the user verifies an error message with "Beginning GL number is greater than ending GL Number" text displays when selecting Beginning number greater than Ending number