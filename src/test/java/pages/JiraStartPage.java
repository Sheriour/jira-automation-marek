package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;

public class JiraStartPage extends AbstractPage
{
    @FindBy(how = How.XPATH, using = "//*[text()='Home']/ancestor::button")
    WebElement homeButton;
    @FindBy(how = How.XPATH, using = " //*[text()='Jira Software']/ancestor::button")
    WebElement jiraSoftwareButton;

    public boolean isHomeButtonVisible(){
        getWait().until(ExpectedConditions.visibilityOf(homeButton));
        return true;
    }
}
