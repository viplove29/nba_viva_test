Feature: Customer Net Balance Policies

  As A MAR user,
  I want the ability to use generate Customer Net Balance reports

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Customer Net Balance" Report Template



  @TC-276
  @sync-37443848
  Scenario: "Customer Net Balance" report sort option functions properly
  And the user generates the report with a random name
  When the user navigates to the generated report
  And the user selects "Detail View" tab
  And the user clicks on the Sort symbol
  And the user selects "Policy Type" under drop down
  And the user selects "Z-A" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Policy Type" are in descending order
  And the user clicks the cancel button
  And the user clicks on the Sort symbol
  And the user selects "Policy Type" under drop down
  And the user selects "A-Z" under Sort
  And the user clicks on the Apply button
  Then the user verifies that the values of "Policy Type" are in ascending order
  And the user clicks the cancel button

  @TC-313
  @sync-38582373
  Scenario: "Customer Net Balance" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Division" under Filters
    And the user sets the Filter to start with "Div"
    And the user clicks on the Apply button
    Then the user verifies that all the "Division" values on the Report start with "Div"