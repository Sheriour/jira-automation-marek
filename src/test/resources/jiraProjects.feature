Feature: Jira Projects

  Background:
    Given I log into Jira Software

  Scenario: Create a team managed kanban project
    When I create a Kanban team-managed project
    Then I see project header contains created project name
    And I see project type is "team-managed"
    And I see project board belongs to a Kanban project

  Scenario: Create a company managed scrum project
    When I create a Scrum company-managed project
    Then I see project header contains created project name
    And I see project type is "company-managed"
    And I see project board belongs to a Scrum project

  Scenario: Create a Bug tracking Project
    When I create a Bug Tracking project
    Then I see project header contains created project name
    And I see project board belongs to a Bug tracking project

  Scenario: Switch project template after initial preview
    When I click the Create Project button on Projects page
    And I select "Kanban" project template
    And I abandon previewed project template
    And I select "Bug tracking" project template
    And I use currently selected project template
    And I provide a randomised project name
    And I finish creating the project
    Then I see project header contains created project name

  Scenario: Switch project template while previewing project type
    When I click the Create Project button on Projects page
    And I select "Kanban" project template
    And I use currently selected project template
    And I change project template
    And I select "Bug tracking" project template
    And I use currently selected project template
    And I provide a randomised project name
    And I finish creating the project
    Then I see project header contains created project name

  Scenario: Switch project template while providing project
    When I click the Create Project button on Projects page
    And I select "Kanban" project template
    And I use currently selected project template
    And I select team managed project type
    And I change project template
    And I select "Bug tracking" project template
    And I use currently selected project template
    And I provide a randomised project name
    And I finish creating the project
    Then I see project header contains created project name
