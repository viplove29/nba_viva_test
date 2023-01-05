Feature: Policy Locations
  As A MAR user,
  I want the ability to use generate a Policy Locations report

  Background:
    Given the user logs into MAR as "RegularUser"
    And the user selects "Policy Locations" Report Template

  @TC-594
  @TC-598
  @sync-116809441
  @sync-116863858
  @Version-22R2
  @PolicyLocations
  Scenario: "Policy Locations" report successfully completed with default columns
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    Then the user verifies the following columns are displayed in tab
      | Customer Name                    |
      | Customer Number                  |
      | Customer Division                |
      | Customer Branch                  |
      | Customer Department              |
      | Customer Group                   |
      | Customer Executive               |
      | Customer Representative          |
      | Customer Broker Name             |
      | Customer Email                   |
      | Customer Phone Number - Business |
      | Customer Address 1               |
      | Customer City                    |
      | Customer State                   |
      | Customer Zip Code                |
      | Customer Type                    |
      | Policy Number                    |
      | Policy Effective Date            |
      | Policy Expiration Date           |
      | Policy Type                      |
    And the user selects "Export Report" under Actions
    And use user exports the report with the following options
      | Excel | Editable | False | Visible | Full Data Set |
    Then the exported report is a "Excel" file format
    Then the exported report has the same columns as the web report
    And the user selects "Export Report" under Actions
    And use user exports the report with the following options
      | CSV |  |  | Visible | Full Data Set |
    Then the exported report is a "CSV" file format
    Then the exported report has the same columns as the web report

  @TC-589
  @sync-116598247
  @Version-22R2
  @PolicyLocations
  Scenario: "Policy Locations" report should show hidden columns when user selects to show
    And the user generates the report with a random name
    When the user navigates to the generated report
    And the user selects "Detail View" tab
    And the user selects the following columns to show using the hide show icon
      | Customer Address 2                   |
      | Customer Email 2                     |
      | Customer Notation                    |
      | Customer Phone Number - Cell         |
      | Customer Phone Number - Residential  |
      | Customer Preferred Contact Method    |
      | DBA                                  |
      | Location Address 1                   |
      | Location Address 2                   |
      | Location City                        |
      | Location Number                      |
      | Location Zip Code                    |
      | Policy Broker                        |
      | Policy Executive                     |
      | Policy First Named Insured           |
      | Policy First Named Insured Address 1 |
      | Policy First Named Insured Address 2 |
      | Policy First Named Insured City      |
      | Policy First Named Insured State     |
      | Policy First Named Insured Zip Code  |
      | Policy Id                            |
      | Policy Notation                      |
      | Policy Representative                |
    Then the user verifies the following columns are displayed in the report by scrolling to the right
      | Customer Name                        |
      | Customer Number                      |
      | Customer Division                    |
      | Customer Branch                      |
      | Customer Department                  |
      | Customer Group                       |
      | Customer Executive                   |
      | Customer Representative              |
      | Customer Broker Name                 |
      | Customer Notation                    |
      | Customer Email                       |
      | Customer Email 2                     |
      | Customer Phone Number - Business     |
      | Customer Phone Number - Residential  |
      | Customer Phone Number - Cell         |
      | Customer Address 1                   |
      | Customer Address 2                   |
      | Customer City                        |
      | Customer State                       |
      | Customer Zip Code                    |
      | Customer Type                        |
      | Customer Preferred Contact Method    |
      | DBA                                  |
      | Policy Number                        |
      | Policy Id                            |
      | Policy Effective Date                |
      | Policy Expiration Date               |
      | Policy Broker                        |
      | Policy Executive                     |
      | Policy Representative                |
      | Policy First Named Insured           |
      | Policy First Named Insured Address 1 |
      | Policy First Named Insured Address 2 |
      | Policy First Named Insured City      |
      | Policy First Named Insured State     |
      | Policy First Named Insured Zip Code  |
      | Policy Notation                      |
      | Policy Type                          |
      | Location Number                      |
      | Location Address 1                   |
      | Location Address 2                   |
      | Location City                        |
      | Location Zip Code                    |
