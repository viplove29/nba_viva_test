Feature: Active Clients

  As a MAR user,
  I want the ability to use generate Active Clients reports
  So that I can use that information

  Background:
    Given the user logs into MAR
    And the user selects 'Active Clients' Report Template

  @TC-90
  @sync-30863379
  Scenario: Quick Actions to Excel Exports Properly
    When the user generates the report with a random name
    And the user clicks on the Quick Actions 'XLS' button
    And the user navigates to the generated report
    And the user opens the downloaded excel report
    Then the excel report has the same number of rows as the web report



