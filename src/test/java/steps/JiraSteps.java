package steps;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import pages.AtlassianLoginPage;
import pages.AtlassianStartPage;
import pages.JiraProjectsPage;
import system.PageRepository;

@Slf4j
public class JiraSteps
{
    @Given("I login to Atlassian")
    public void iLoginToAtlassian()
    {
        AtlassianLoginPage page = new AtlassianLoginPage();
        page.visit();
        page.enterUsername("sheriour@gmail.com");
        page.enterPassword("c*C@94a7DIMl");
    }

    @Then("I verify that user has logged in")
    public void iVerifyUserHasLoggedIn()
    {
        AtlassianStartPage page = new AtlassianStartPage();
        Assert.assertTrue(page.isHomeButtonVisible(), "Home button was not visible!");
    }

    @And("I navigate to Jira Software")
    public void iNavigateToJiraSoftware()
    {
        AtlassianStartPage atlassianStartPage = new AtlassianStartPage();
        Assert.assertTrue(atlassianStartPage.clickJiraLink(), "Could not navigate to Jira Software!");
    }

    @Then("I am on the project list page")
    public void iAmOnTheProjectListPage()
    {
        JiraProjectsPage projectsPage = new JiraProjectsPage();
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
        PageRepository.getPage(JiraProjectsPage.class).createNewProject();
    }

    @And("I select {string} project template")
    public void iSelectProjectTemplate(String projectTemplateName)
    {
        PageRepository.getPage(JiraProjectsPage.class).selectProjectTemplate(projectTemplateName);
    }

    @And("I use currently selected project template")
    public void iUseCurrentlySelectedProjectTemplate()
    {
        PageRepository.getPage(JiraProjectsPage.class).useProjectTemplate();
    }

    @And("I select team managed project type")
    public void iSelectTeamManagedProjectType()
    {
        PageRepository.getPage(JiraProjectsPage.class).selectTeamManagedProjectType();
    }

    @And("I select company managed project type")
    public void iSelectCompanyManagedProjectType()
    {
        PageRepository.getPage(JiraProjectsPage.class).selectCompanyManagedProjectType();
    }
}
