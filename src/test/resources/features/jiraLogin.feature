Feature: Jira Login

  Scenario: Login to Jira
    Given I login to Atlassian
    Then I verify that user has logged in
    And I navigate to Jira Software
    Then I am on the project list page