package steps;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import pages.AtlassianLoginPage;
import pages.AtlassianStartPage;
import pages.JiraProjectsPage;
import system.PageRepository;

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

    @And("I click the Create Project button")
    public void iClickTheCreateProjectButton()
    {
        getPage(JiraProjectsPage.class).createNewProject();
    }

    @And("I select {string} project template")
    public void iSelectProjectTemplate(String projectTemplateName)
    {
        getPage(JiraProjectsPage.class).selectProjectTemplate(projectTemplateName);
    }

    @And("I use currently selected project template")
    public void iUseCurrentlySelectedProjectTemplate()
    {
        getPage(JiraProjectsPage.class).useProjectTemplate();
    }

    @And("I select team managed project type")
    public void iSelectTeamManagedProjectType()
    {
        getPage(JiraProjectsPage.class).selectTeamManagedProjectType();
    }

    @And("I select company managed project type")
    public void iSelectCompanyManagedProjectType()
    {
        getPage(JiraProjectsPage.class).selectCompanyManagedProjectType();
    }
}
