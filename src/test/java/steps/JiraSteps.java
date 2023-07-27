package steps;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import models.JiraProject;
import org.testng.Assert;
import pages.*;
import system.EntityManager;
import system.JiraApi;
import utils.GeneralUtils;

import static system.PageRepository.getPage;

@Slf4j
public class JiraSteps
{
    @Given("I login to Atlassian")
    public void iLoginToAtlassian()
    {
        AtlassianLoginPage atlassianLoginPage = getPage(AtlassianLoginPage.class);
        atlassianLoginPage.visit();
        atlassianLoginPage.enterUsername("sheriour@gmail.com");
        atlassianLoginPage.enterPassword("c*C@94a7DIMl");
    }

    @Then("I verify that user has logged in")
    public void iVerifyUserHasLoggedIn()
    {
        AtlassianStartPage atlassianStartPage = getPage(AtlassianStartPage.class);
        Assert.assertTrue(atlassianStartPage.isHomeButtonVisible(), "Home button was not visible!");
    }

    @And("I navigate to Jira Software")
    public void iNavigateToJiraSoftware()
    {
        AtlassianStartPage atlassianStartPage = getPage(AtlassianStartPage.class);
        atlassianStartPage.clickJiraLink();
    }

    @Then("I am on the project list page")
    public void iAmOnTheProjectListPage()
    {
        JiraProjectsPage projectsPage = getPage(JiraProjectsPage.class);
        Assert.assertTrue(projectsPage.isProjectPageHeaderVisible(), "Could not navigate to Jira Software!");
    }

    @Given("I log into Jira Software")
    public void iLogIntoJiraSoftware()
    {
        //Compound step
        iLoginToAtlassian();
        iNavigateToJiraSoftware();
        iAmOnTheProjectListPage();
    }

    @And("I click the Create Project button on Projects page")
    public void iClickTheCreateProjectButtonOnProjectsPage()
    {
        getPage(JiraProjectsPage.class).createNewProject();
    }

    @And("I select {string} project template")
    public void iSelectProjectTemplate(String projectTemplateName)
    {
        getPage(JiraProjectCreationPage.class).selectProjectTemplate(projectTemplateName);
    }

    @And("I use currently selected project template")
    public void iUseCurrentlySelectedProjectTemplate()
    {
        getPage(JiraProjectCreationPage.class).useProjectTemplate();
    }

    @And("I select team managed project type")
    public void iSelectTeamManagedProjectType()
    {
        getPage(JiraProjectCreationPage.class).selectTeamManagedProjectType();
    }

    @And("I select company managed project type")
    public void iSelectCompanyManagedProjectType()
    {
        getPage(JiraProjectCreationPage.class).selectCompanyManagedProjectType();
    }

    @And("I provide a randomised project name")
    public void iProvideARandomisedProjectName() {
        JiraProject project = new JiraProject();
        project.Name = JiraProject.getRandomName();
        EntityManager.registerProject(project);

        getPage(JiraProjectCreationPage.class).provideProjectName(project.Name);
    }

    @And("I finish creating the project")
    public void iFinishCreatingTheProject() {
        getPage(JiraProjectCreationPage.class).finishCreatingProject();
    }

    @And("I abandon previewed project template")
    public void iAbandonPreviewedProjectTemplate() {
        getPage(JiraProjectCreationPage.class).abandonTemplate();
    }

    @And("I change project template")
    public void iChangeProjectTemplate() {
        getPage(JiraProjectCreationPage.class).changeTemplate();
    }


    @Then("I see project header contains created project name")
    public void iSeeProjectHeaderContainsCreatedProjectName() {
        Assert.assertTrue(
                getPage(JiraProjectBoardPage.class).projectHeaderContainsName(EntityManager.getProjectName()),
                "Could not see project name on the project board page!"
        );
    }

    @Then("I see project type is {string}")
    public void iSeeProjectTypeIs(String projectType) {
        Assert.assertTrue(
                getPage(JiraProjectBoardPage.class).displayedProjectTypeIs(projectType),
                "Could not see project type as "+projectType+" on the project board page!"
        );
    }

    @Then("I see project board belongs to a Scrum project")
    public void iSeeProjectBoardBelongsToAScrumProject() {
        Assert.assertTrue(
                getPage(JiraProjectBoardPage.class).isProjectBoardForScrum(),
                "Could not verify the project as Scrum on the project board page!"
        );
    }

    @Then("I see project board belongs to a Kanban project")
    public void iSeeProjectBoardBelongsToAKanbanProject() {
        Assert.assertTrue(
                getPage(JiraProjectBoardPage.class).isProjectBoardForKanban(),
                "Could not verify the project as Kanban on the project board page!"
        );
    }

    @Then("I see project board belongs to a Bug tracking project")
    public void iSeeProjectBoardBelongsToABugTrackingProject() {
        Assert.assertTrue(
                getPage(JiraProjectBoardPage.class).isProjectBoardForBugTracking(),
                "Could not verify the project as Bug tracking on the project board page!"
        );
    }

    @And("I wait {int} seconds")
    public void iWaitSeconds(int seconds)  {
        GeneralUtils.waitForSeconds(seconds);
    }

    @Given("I try stuff")
    public void iTryStuff() {
        JiraApi.GetInstance().getProjectIdByName("Marek Automation");
    }
}
