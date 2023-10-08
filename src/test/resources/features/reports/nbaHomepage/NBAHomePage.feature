Feature: NBA

  As a NBA customer,
  I should be able to get match details & book ticket
  So that customer can watch the show

  Background:
    Given the user opens the NBA warrior home page

  @TC-900
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify NBA warrior page sign up pop up fields are enable and allow user to enter value
    And the user enters below details to sign up pop up if exists
      | First Name | Viplove           |
      | Last Name  | Bisen             |
      | Email      | viplove@gmail.com |
      | ZipCode    | 12345             |
    And the user accepts the cookies if exists

  @TC-901
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify NBA warrior page has all the required team added under Teams drop down
    And the user closes the sign up pop up if exists
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
    Then the user verifies that NBA Home page should have total 30 teams city added in the Teams dropdown

  @TC-902
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify NBA warrior page has all the required tabs available
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
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

  @TC-903
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify user is successfully navigated to corresponding page on clicking required tabs
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
    And the user clicks on 'Schedule' tab of NBA warrior homepage
    Then the user verifies that 'Schedule' page is loaded
    And the user clicks on 'Tickets' tab of NBA warrior homepage
    Then the user verifies that 'Tickets' page is loaded
    And the user clicks on 'Team' tab of NBA warrior homepage
    Then the user verifies that 'Roster' page is loaded

  @TC-904
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify that 8 new items are loaded on clicking Load More button at the bottom of NBA warrior homepage
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
    And the user saves current items count and clicks on Load More button
    Then the user verifies that 8 more new items are loaded in the bottom of NBA warrior homepage
    And the user saves current items count and clicks on Load More button
    Then the user verifies that 8 more new items are loaded in the bottom of NBA warrior homepage

  @TC-905
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify the number of broken link in the NBA warrior homepage
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
    Then the user verifies the number of broken link in the NBA warrior homepage


