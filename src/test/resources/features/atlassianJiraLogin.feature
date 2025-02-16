Feature: Atlassian and Jira Login

  @Run
  Scenario: Successfully login to Jira
    Given I login to Atlassian
    Then I verify that user has logged in

  @Run
  Scenario: Attempt to login with an invalid username
    Given I am on Atlassian login page
    When I enter an invalid username
    Then I don't see the password input

  @Run
  Scenario: Attempt to login with an incorrect password
    Given I am on Atlassian login page
    When I enter a valid username and invalid password
    Then I see a warning informing me that the password was invalid
