package steps;

import io.cucumber.java.en.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import pages.JiraLoginPage;
import pages.JiraStartPage;

@Slf4j
public class JiraSteps
{

    @Given("I login to Atlassian")
    public void iLoginToAtlassian()
    {
        JiraLoginPage page = new JiraLoginPage();
        page.visit();
        page.enterUsername("sheriour@gmail.com");
        page.enterPassword("c*C@94a7DIMl");
    }

    @Then("I verify that user has logged in")
    public void iVerifyUserHasLoggedIn()
    {
        JiraStartPage page = new JiraStartPage();
        Assert.assertTrue(page.isHomeButtonVisible(), "Home button was not visible!");
    }
}
