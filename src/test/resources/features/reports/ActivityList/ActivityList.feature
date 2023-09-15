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
    When the user expands the center dropdown


