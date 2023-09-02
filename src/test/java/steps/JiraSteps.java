package steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import models.JiraProject;
import org.testng.Assert;
import pages.*;
import system.EntityManager;
import system.JiraApi;
import system.PropertyManager;
import utils.GeneralUtils;

import static system.PageRepository.getPage;

@Slf4j
public class JiraSteps
{
    @ParameterType("Kanban|Scrum|Bug tracking")
    public String projectTemplate(String input) {
        return input;
    }

    @ParameterType("team-managed|company-managed")
    public String projectManagement(String input) {
        return input;
    }

    @Given("I login to Atlassian")
    public void iLoginToAtlassian()
    {
        String username = PropertyManager.GetInstance().getProperty("jira_api_username");;
        String password = PropertyManager.GetInstance().getProperty("jira_user_password");

        AtlassianLoginPage atlassianLoginPage = getPage(AtlassianLoginPage.class);
        atlassianLoginPage.visit();
        atlassianLoginPage.enterUsername(username);
        atlassianLoginPage.enterPassword(password);
    }

    @Given("I am on Atlassian login page")
    public void iAmOnAtlassianLoginPage()
    {
        getPage(AtlassianLoginPage.class).visit();
    }

    @When("I enter an invalid username")
    public void iEnterAnInvalidUsername()
    {
        getPage(AtlassianLoginPage.class).enterUsername("asinvalidastheycome");
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
        iLoginToAtlassian();
        iNavigateToJiraSoftware();
        iAmOnTheProjectListPage();
    }

    @Then("I don't see the password input")
    public void iDonTSeeThePasswordInput() {
        Assert.assertFalse(
                getPage(AtlassianLoginPage.class).isPasswordFieldVisible(),
                "Password field was visible when it was expected to be hidden!"
        );
    }

    @Then("I see a warning informing me that the password was invalid")
    public void iSeeAWarningInformingMeThatThePasswordWasInvalid() {
        Assert.assertTrue(
                getPage(AtlassianLoginPage.class).isPasswordWarningVisible(),
                "Invalid password warning was not visible!"
        );

    }

    @When("I enter a valid username and invalid password")
    public void iEnterAValidUsernameAndInvalidPassword() {
        String username = PropertyManager.GetInstance().getProperty("jira_api_username");;

        AtlassianLoginPage atlassianLoginPage = getPage(AtlassianLoginPage.class);
        atlassianLoginPage.visit();
        atlassianLoginPage.enterUsername(username);
        atlassianLoginPage.enterPassword("definitelywrong");
    }

    @And("I create a software {projectTemplate} {projectManagement} project")
    public void iCreateAProject(String projectTemplateName, String projectManagement)
    {
        JiraProjectCreationPage projectCreationPage = getPage(JiraProjectCreationPage.class);
        projectCreationPage.selectProjectTemplateGroup("Software development");
        projectCreationPage.selectProjectTemplate(projectTemplateName);
        projectCreationPage.useProjectTemplate();

        if (projectManagement.equals("team-managed"))
            projectCreationPage.selectTeamManagedProjectType();
        else
            projectCreationPage.selectCompanyManagedProjectType();

        JiraProject project = new JiraProject();
        project.Name = JiraProject.getRandomName();
        EntityManager.registerProject(project);

        projectCreationPage.provideProjectName(project.Name);
        projectCreationPage.finishCreatingProject();

        //This was displaying for a while in the org, but no longer seems to pop up
        //projectCreationPage.skipIntegration();
    }

    @And("I create a Bug Tracking project")
    public void iCreateABugTrackingProject()
    {
        JiraProjectCreationPage projectCreationPage = getPage(JiraProjectCreationPage.class);
        projectCreationPage.selectProjectTemplate("Bug tracking");
        projectCreationPage.useProjectTemplate();

        JiraProject project = new JiraProject();
        project.Name = JiraProject.getRandomName();
        EntityManager.registerProject(project);

        projectCreationPage.provideProjectName(project.Name);
        projectCreationPage.finishCreatingProject();
    }

    @And("I start creating a new Project")
    public void iStartCreatingANewProject()
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

    @And("I select {string} project template group")
    public void iSelectProjectTemplateGroup(String projectTemplateGroup) {
        getPage(JiraProjectCreationPage.class).selectProjectTemplateGroup(projectTemplateGroup);
    }
}
