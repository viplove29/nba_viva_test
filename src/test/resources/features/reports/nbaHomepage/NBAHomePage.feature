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
    Then the user verifies below details are entered in the sign up pop up if exists
      | First Name | Viplove           |
      | Last Name  | Bisen             |
      | Email      | viplove@gmail.com |
      | ZipCode    | 12345             |
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists

  @TC-901
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify NBA warrior page has all the required team location added under Teams drop down
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
  Scenario Outline: Verify on clicking tabs user is successfully navigated to corresponding page
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
    And the user clicks on '<tab name>' tab of NBA warrior homepage
    Then the user verifies that '<page name>' page is loaded
    Examples:
      | tab name | page name |
      | Schedule | Schedule  |
      | Tickets  | Tickets   |
      | Team     | Roster    |

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
  Scenario: Verify the schedule page has the lists of games that are played against other teams
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
    And the user clicks on 'Schedule' tab of NBA warrior homepage
    Then the user verifies that 'Schedule' page is loaded
    Then user verifies that a total of 85 scheduled matches is scheduled to play
    Then user verifies that matches are scheduled at below date and time
      | FRI OCT 13 7:00 pm PT |
      | SUN OCT 15 6:30 pm PT |
      | FRI OCT 27 7:00 pm PT |
      | SUN OCT 29 4:00 pm PT |
      | MON OCT 30 5:00 pm PT |
      | SUN NOV 5 3:00 pm PT  |
      | MON NOV 6 4:00 pm PT  |
      | WED NOV 8 7:00 pm PT  |

  @TC-906
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify the  the lists of players when user navigates to roster page on clicking Team tab
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
    And the user clicks on 'Team' tab of NBA warrior homepage
    Then the user verifies that 'Roster' page is loaded
    Then user verifies that a total of 21 players list is displayed
    Then user verifies that the roster page contains below player names
      | Javan Johnson      |
      | Jonathan Kuminga   |
      | Gary Payton II     |
      | Cory Joseph        |
      | Brandin Podziemski |
      | Chris Paul         |
      | Moses Moody        |
      | Kevon Looney       |

  @TC-907
  @sync-38702114
  @NBA
  @Version-23R3
  Scenario: Verify the number of broken link in the NBA warrior homepage
    And the user closes the sign up pop up if exists
    And the user accepts the cookies if exists
    Then the user verifies the number of broken link in the NBA warrior homepage