package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static system.DriverCoordinator.getWebDriver;
import static utils.WebElementUtils.waitAndClick;
import static utils.WebElementUtils.waitAndFillField;

public class AtlassianLoginPage {
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
        waitAndFillField(usernameInput, username);
        waitAndClick(submit);
    }

    public void enterPassword(String password){
        waitAndFillField(passwordInput, password);
        waitAndClick(submit);
    }
}
