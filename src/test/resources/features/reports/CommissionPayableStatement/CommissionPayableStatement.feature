Feature: Commission Payable Statement

  As A MAR user,
  I want the ability to use generate Commission Payable Statement report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Commission Payable Statement" Report Template


  @TC-279
  @sync-37490509
  Scenario: "Commission Payable Statement" report sort option functions properly
  Then the user sets date range from "01/01/2010" to today
  And the user generates the report with a random name
  When the user navigates to the generated report
  And the user selects "Detail View" tab
  And the user clicks on the Sort symbol
  And the user selects "Invoice Type" under drop down
  And the user selects "Z-A" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Invoice Type" are in descending order
  And the user clicks the cancel button
  And the user clicks on the Sort symbol
  And the user selects "Invoice Type" under drop down
  And the user selects "A-Z" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Invoice Type" are in ascending order
  And the user clicks the cancel button