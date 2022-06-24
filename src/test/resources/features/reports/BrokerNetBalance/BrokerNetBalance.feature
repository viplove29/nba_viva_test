Feature: Broker Net Balance

  As A MAR user,
  I want the ability to use generate Broker Net Balance reportz

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Broker Net Balance" Report Template


  @TC-292
  @sync-38392818
  Scenario: "Broker Net Balance" report Filters option functions properly
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user clicks on the Filter symbol
    And the user clicks on the Add Filters button
    And the user selects "Broker Name" under Filters
    And the user sets the Filter to start with "D"
    And the user clicks on the Apply button
    Then the user verifies that all the "Broker Name" values on the Report start with "D"

  @TC-291
  @sync-38392817
  Scenario: "Broker Net Balance" report sort option functions properly
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


  @TC-295
  @sync-38393252
  Scenario: Broker Net Balance Add new Summary View tab in the report
    When the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks the add summary tab
    And the user selects the following checkboxes on the summary page
      |Age|
      |Total Net Balance|
      |Description|
    And the user names the summary "brokerNetBalanceSummary"
    And the user clicks the add summary button
    And the user clicks the action button
    And the user clicks the Save as a New Template menu item
    And the user names the template "brokerNetBalanceTemplate"
    And the user saves the template
    And the user clicks the Ok button
    And the user presses the back arrow
    And the user search for the template named "brokerNetBalanceTemplate"
    And the user selects "brokerNetBalanceTemplate" Report Template
    And the user generates the report with a random name
    And the user navigates to the generated report
    Then the user verifies the "brokerNetBalanceSummary" tab exists
    And the user presses the back arrow
    And the user search for the template named "brokerNetBalanceTemplate"
    And the user presses the templates action menu
    And the user selects the delete menu item

