Feature: Go to URL Example - Delete this after adding Feature files for new projects

  As a browser User,
  I want the ability to go to a URL, and manipulate the browser
  So that I can verify my setup works

  Scenario: Go To URL
    Given I navigate to 'http://www.google.com'
    And I wait for '3' seconds
    And I set the window width to 1200 and the height to be 800
    And I wait for '3' seconds
    And I maximize the window
    And I wait for '3' seconds
    And I click the Im Feeling Lucky button
    And I wait for '3' seconds
    And I close the browser



