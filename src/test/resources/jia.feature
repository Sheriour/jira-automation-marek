Feature: Jira

  Scenario: Login to Jira
    Given I login to Atlassian
    Then I verify that user has logged in
    And I navigate to Jira Software
    Then I am on the project list page


  Scenario: Create a Project
    Given I log into Jira Software
    When I click the Create Project button
    And I select "Kanban" project template
    And I use currently selected project template
    And I select team managed project type
