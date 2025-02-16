package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;
import static utils.WebElementUtils.*;

public class AtlassianStartPage {
    @FindBy(how = How.XPATH, using = "//*[text()='Home']/ancestor::button")
    WebElement homeButton;
    @FindBy(how = How.CSS, using = "a[href*='selectedProjectType=software']")
    WebElement jiraSoftwareButton;

    /**
     * Checks if home button is visible on Atlassian start page
     *
     * @return  True if home button was visible
     */
    public boolean isHomeButtonVisible(){
        getWait().until(ExpectedConditions.visibilityOf(homeButton));
        return true;
    }

    /**
     * Clicks the link which navigates to Jira Software within Atlassian
     */
    public void clickJiraLink(){
        waitAndClick(jiraSoftwareButton, "Could not navigate to Jira Software!");
    }
}
