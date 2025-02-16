Feature: Jira Project Creation

  Background:
    Given I log into Jira Software
    When I start creating a new Project

  @Run
  Scenario: Create a team managed kanban project
    And I create a software Kanban team-managed project
    Then I see project header contains created project name
    And I see project type is "team-managed"
    And I see project board belongs to a Kanban project

  @Run
  Scenario: Create a company managed scrum project
    And I create a software Scrum company-managed project
    Then I see project header contains created project name
    And I see project type is "company-managed"
    And I see project board belongs to a Scrum project

  @Run
  Scenario: Create a Bug tracking Project
    And I select "Software development" project template group
    And I create a Bug Tracking project
    Then I see project header contains created project name
    And I see project board belongs to a Bug tracking project

  @Run
  Scenario: Switch project template after initial preview
    And I select "Software development" project template group
    And I select "Kanban" project template
    And I abandon previewed project template
    And I select "Software development" project template group
    And I create a Bug Tracking project
    Then I see project header contains created project name

  @Run
  Scenario: Switch project template while previewing project type
    And I select "Software development" project template group
    And I select "Kanban" project template
    And I use currently selected project template
    And I change project template
    And I select "Software development" project template group
    And I create a Bug Tracking project
    Then I see project header contains created project name

  @Run
  Scenario: Switch project template while providing project
    And I select "Software development" project template group
    And I select "Kanban" project template
    And I use currently selected project template
    And I select team managed project type
    And I change project template
    And I select "Software development" project template group
    And I create a Bug Tracking project
    Then I see project header contains created project name
