package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.*;
import static utils.WebElementUtils.waitAndClick;
import static utils.WebElementUtils.waitAndFillField;

public class AtlassianLoginPage {
    @FindBy(how = How.ID, using = "username")
    WebElement usernameInput;
    @FindBy(how = How.ID, using = "password")
    WebElement passwordInput;
    @FindBy(how = How.ID, using = "login-submit")
    WebElement submit; //Username/Password are both handled by the same button
    @FindBy(how = How.CSS, using = "section[data-testid='form-error']")
    WebElement invalidPasswordWarning;

    /**
     * Navigate to Atlassian login page
     */
    public void visit(){
        getWebDriver().get("https://id.atlassian.com/");
    }

    /**
     * Enter username in Atlassian login page
     *
     * @param username  Username of the user
     */
    public void enterUsername(String username){
        waitAndFillField(usernameInput, username, "Could not fill out username!");
        waitAndClick(submit);
    }

    /**
     * Enter password in Atlassian login page
     *
     * @param password  Password of the user
     */
    public void enterPassword(String password){
        waitAndFillField(passwordInput, password, "Could not fill out password!");
        waitAndClick(submit);
    }

    /**
     * Check if password input becomes visible over next 5 seconds
     *
     * @return True if password input was visible, false otherwise
     */
    public boolean isPasswordFieldVisible(){
        try {
            getWait(5).until(ExpectedConditions.visibilityOf(passwordInput));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Check if invalid password warning over next 5 seconds
     *
     * @return True if password warning was visible, false otherwise
     */
    public boolean isPasswordWarningVisible(){
        try {
            getWait(5).until(ExpectedConditions.visibilityOf(invalidPasswordWarning));
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
