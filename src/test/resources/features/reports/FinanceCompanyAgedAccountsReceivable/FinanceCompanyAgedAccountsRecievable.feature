Feature: Finance Company Aged Accounts Receivable

  As A MAR user,
  I want the ability to use generate a Finance Company Aged Accounts Receivable report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Finance Company Aged Accounts Receivable" Report Template


  @TC-322
  @sync-38659034
  Scenario: "Finance Company Aged Accounts Receivable" report sort option functions properly
  And the user generates the report with a random name
  When the user navigates to the generated report
  And the user selects "Detail View" tab
  And the user clicks on the Sort symbol
  And the user selects "Z-A" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Finance Company Name" are in descending order
  And the user clicks the cancel button
  And the user clicks on the Sort symbol
  And the user selects "A-Z" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Finance Company Name" are in ascending order
  And the user clicks the cancel button
