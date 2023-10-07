Feature: NBA

  As a NBA customer,
  I should be able to get match details & book ticket
  So that customer can watch the show

  Background:
    Given the user opens the NBA warrior home page

  @TC-900
  @sync-38702114
  @NBA
  Scenario: Verify NBA warrior page functions properly
    Given the user opens the NBA warrior home page
    And the user enters below details to sign up pop up if exists
      | First Name | Viplove           |
      | Last Name  | Bisen             |
      | Email      | viplove@gmail.com |
      | ZipCode    | 12345             |
    And the user accepts the cookies if exists
    Then the user verifies below NBA teams location
      | New York     |
      | Toronto      |
      | Boston       |
      | Brooklyn     |
      | Philadelphia |
      | Chicago      |
      | Cleveland    |
      | Detroit      |
      | Indiana      |
      | Milwaukee    |
      | Atlanta      |
      | Charlotte    |
      | Miami        |
      | Orlando      |
      | Washington   |
    Then the user verifies that below tabs are displayed in the NBA homepage
      | TICKETS             |
      | SCHEDULE            |
      | TEAM                |
      | SHOP                |
      | CHASE CENTER        |
      | MY WARRIORS ACCOUNT |
    Then the user verifies that below tabs are displayed in the top navigation bar of NBA warrior homepage
      | G League        |
      | WNBA            |
      | BAL             |
      | NBA 2K          |
      | NBA Store       |
      | NBA League Pass |
      | NBA ID          |

  @TC-900
  @sync-38702114
  @NBA
  Scenario: Verify user is successfully navigated to corresponding page on clicking required tabs
    And the user enters below details to sign up pop up if exists
      | First Name | Viplove           |
      | Last Name  | Bisen             |
      | Email      | viplove@gmail.com |
      | ZipCode    | 12345             |
    And the user accepts the cookies if exists
    And the user clicks on 'Schedule' tab of NBA warrior homepage
    Then the user verifies that 'Schedule' page is loaded
    And the user clicks on 'Tickets' tab of NBA warrior homepage
    Then the user verifies that 'Tickets' page is loaded
    And the user clicks on 'Team' tab of NBA warrior homepage
    Then the user verifies that 'Roster' page is loaded

#  @TC-900
#  @sync-38702114
#  @NBA
#  Scenario: Verify the number of broken link in the NBA warrior homepage
#    And the user enters below details to sign up pop up if exists
#      | First Name | Viplove           |
#      | Last Name  | Bisen             |
#      | Email      | viplove@gmail.com |
#      | ZipCode    | 12345             |
#    And the user accepts the cookies if exists
#    Then the user verifies the number of broken link in the NBA warrior homepage


#    Then the user sets date range from "01/01/2010" to today
#    And the user generates the report with a random name
#    When the user navigates to the generated report
#    And the user selects "Detail View" tab
#    And the user clicks on the Filter symbol
#    And the user clicks on the Add Filters button
#    And the user selects "Division" under Filters
#    And the user sets the Filter to start with "Div"
#    And the user clicks on the Apply button
#    Then the user verifies that all the "Division" values on the Report start with "Div"


