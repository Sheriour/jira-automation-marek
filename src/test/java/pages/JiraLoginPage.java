package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;
import static system.DriverCoordinator.getWebDriver;

public class JiraLoginPage extends AbstractPage
{
    @FindBy(how = How.ID, using = "username")
    WebElement usernameInput;
    @FindBy(how = How.ID, using = "password")
    WebElement passwordInput;
    @FindBy(how = How.ID, using = "login-submit")
    WebElement submit; //Username/Password are both handled by the same button

    public void visit(){
        getWebDriver().get("https://id.atlassian.com/");
    }

    public void enterUsername(String username){
        getWait()
                .until(ExpectedConditions.elementToBeClickable(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);
        getWait()
                .until(ExpectedConditions.elementToBeClickable(submit))
                .click();
    }

    public void enterPassword(String username){
        getWait()
                .until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(username);
        getWait()
                .until(ExpectedConditions.elementToBeClickable(submit))
                .click();
    }
}
