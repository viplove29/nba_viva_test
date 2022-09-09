Feature: X-Date

  As A MAR user,
  I want the ability to use generate X-Date reports

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "X-Date" Report Template

  @TC-368
  @sync-38773261
  @X-Date
  Scenario: "X-Date" report sort option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Old-New" under Sort
    And the user clicks on the Apply button
    And the user clicks the cancel button
    Then the user verifies that the values of "X-Date" are in ascending date order
    And the user clicks on the Sort symbol
    And the user selects "New-Old" under Sort
    And the user clicks on the Apply button
    And the user clicks the cancel button
    Then the user verifies that the values of "X-Date" are in descending date order


  @TC-369
  @sync-38773262
  @X-Date
  Scenario: "X-Date" report Filters option functions properly
    Then the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Customer Name" under Filters
    And the user sets the Filter to start with "Firm"
    And the user clicks on the Apply button
    Then the user verifies that all the "Customer Name" values on the Report start with "Firm"


  @TC-372
  @sync-38773266
  @X-Date
  Scenario: Add X-date summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      | Customer Count |
      | Division       |
      | Current Agent  |
      | Branch         |
    And the user names the summary "x-dateSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "x-dateSummaryTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "x-dateSummaryTemplate"
    And the user selects "x-dateSummaryTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "x-dateSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "x-dateSummaryTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item


  @TC-371
  @sync-38773265
  @X-Date
  Scenario: X-Date Check Columns spelling and Show/Hide columns option
    When the user sets date range from "01/01/2010" to today
    And the user generates the report with a random name
    And the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | X-Date                                     |
      | Customer Name                              |
      | Line Of Business                           |
      | Current Agent                              |
      | Policy Number                              |
      | Parent Company                             |
      | Customer Account Size                      |
      | Interest Level                             |
      | Premium                                    |
      | Remarks                                    |
      | Renewal Contact Name                       |
      | Renewal Contact Phone Number - Residential |
      | Renewal Contact Phone Number - Business    |
      | Renewal Contact Email                      |
      | Customer Executive                         |
      | Customer Representative                    |
      | Broker Name                                |
      | Business With Agency                       |
    And the user selects "Premium" under the hide show icon
    Then the user verifies the following columns are not displayed in tab
      | Premium |



