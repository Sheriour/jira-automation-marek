package steps;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import pages.AtlassianLoginPage;
import pages.AtlassianStartPage;
import pages.JiraProjectsPage;
import system.JiraApi;

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
        iLoginToAtlassian();
        iNavigateToJiraSoftware();
        iAmOnTheProjectListPage();
    }



    @Then("I do API stuff")
    public void iDoApiStuff()
    {
        JiraApi.GetInstance().sendDelete("/3/project/10002");
    }
}
