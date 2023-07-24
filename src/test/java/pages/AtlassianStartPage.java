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

    public boolean isHomeButtonVisible(){
        getWait().until(ExpectedConditions.visibilityOf(homeButton));
        return true;
    }

    public void clickJiraLink(){
        waitAndClick(jiraSoftwareButton, "Could not navigate to Jira Software!");
    }
}
