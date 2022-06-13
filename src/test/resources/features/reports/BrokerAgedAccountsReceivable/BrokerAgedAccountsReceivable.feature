Feature: Broker Aged Accounts Receivable

As A MAR user,
I want the ability to use generate Broker Aged Accounts Receivable report

Background:
Given the user logs into MAR as "RegularUser"
And the user selects "Broker Aged Accounts Receivable" Report Template


  @TC-338
  @sync-38702114
  Scenario: "Broker Aged Accounts Receivable" report Filters option functions properly
  And the user generates the report with a random name
  When the user navigates to the generated report
  And the user selects "Detail View" tab
  And the user clicks on the Filter symbol
  And the user clicks on the Add Filters button
  And the user selects "Broker Name" under Filters
  And the user sets the Filter to start with "BCD"
  And the user clicks on the Apply button
  Then the user verifies that all the "Broker Name" values on the Report start with "BCD"


  @TC-272
  @sync-37286304
  Scenario: "Broker Aged Accounts Receivable" report sort option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Sort symbol
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Broker Name" are in descending order
    And the user clicks the cancel button
    And the user clicks on the Sort symbol
    And the user selects "A-Z" under Sort
    And the user clicks on the Apply button
    Then the user verifies that the values of "Broker Name" are in ascending order
    And the user clicks the cancel button

