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

  @TC-539
  @sync-53688478
  Scenario Outline: Verify Specific Account Number new filters
    Then the user selects "Specific Account Number" from "Numbers" dropdown under Account Numbers/Sub-ledgers section
    And the user selects random value from "Specific Number" dropdown under Account Numbers/Sub-ledgers section
    And the user selects "<Option>" from "Sub-ledgers" dropdown under Account Numbers/Sub-ledgers section
    Then the user verifies the "<Dropdown>" dropdown is displayed

    Examples:
      | Option                     | Dropdown |
      | (All)                      |          |
      | Specific Broker            | Broker   |
      | Specific Brokerage Company | Company  |
      | Specific Employee          | Employee |
      | Specific Fee Company       | Company  |
      | Specific Finance Company   | Company  |
      | Specific Insurance Company | Company  |
      | Specific Vendor            | Vendor   |


  @TC-327
  @sync-38660858
  Scenario: "General Ledger" report sort option functions properly
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

  @TC-331
  @sync-38660867
  Scenario: Add General Ledger summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Total Debit |
      | Division    |
      | Department  |
      | Branch      |
    And the user names the summary "generalLedgerSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "generalLedgerSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "generalLedgerSummaryTemplate"
    And the user selects "generalLedgerSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "generalLedgerSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "generalLedgerSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

  @TC-330
  @sync-38660865
  Scenario: General Ledger Check Columns spelling and Show/Hide columns option
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | GL Number                 |
      | Account Name              |
      | Division                  |
      | Branch                    |
      | Department                |
      | Subledger Name            |
      | GL Date                   |
      | Description               |
      | Beginning Running Balance |
      | Debit                     |
      | Credit                    |
      | Ending Running Balance    |
      | Type                      |
      | GL Code                   |
      | Source Reference Number   |
    And the user selects "Department" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Department |

  @TC-562
  @sync-55872259
  Scenario: Verify Functionality of Active/Inactive/All BU Filters in General Ledger Report in MAR
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

