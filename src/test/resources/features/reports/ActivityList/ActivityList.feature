Feature: Activity List

  As a MAR user,
  I want the ability to use generate Activity List reports
  So that I can use that information

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Activity List" Report Template

  @TC-16376
  @sync-120853720
  @ActivityList
  Scenario: Activity List Report
    When the user expands the center section
    And the user expands the customer business section
    And the user clicks the division select multiple button
    And the user clicks the division select multiple dropdown button
    And the user selects division all
    And the user expands policy section
    And the user expands transaction type section
    And the user expands transaction type section option multiple
    And the user expands transaction type section option all
    And the user expands type of business section
    And the user presses the type of business option multiple
    And the user expands company section
    And the user expands date selection section
    And the user enters 'one' into number of days
    And verify the number of days is '90'
    And the user expands action type section
    And the user expands entered by section
    And the user expands additional filters section
    And the user generates the report with a random name
    And the user navigates to the generated report
    And the user clicks on the Sort symbol
    And the user selects "Action" under drop down
    And the user selects "Z-A" under Sort
    And the user clicks on the Apply button
    And the user names the template "activityListTemplate"
    And the user saves the template
    And the user clicks the Ok button
    Then the user verifies that the values of "Action" are in descending order





