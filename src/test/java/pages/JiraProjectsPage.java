package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;
import static utils.WebElementUtils.*;

@Slf4j
public class JiraProjectsPage {

    @FindBy(how = How.XPATH, using = "//h1[text()='Projects']")
    WebElement projectPageHeader;
    @FindBy(how = How.CSS, using = "button[data-testid*='create-projects-button']")
    WebElement createProjectButton;

    /**
     * Gets the span element for given project on the list
     *
     * @param projectName   Name of the given project
     * @return              By locator for the element
     */
    private By getProjectNameSpanBy(String projectName){
        return By.xpath("//tbody//a/span[contains(text(), '"+projectName+"')]");
    }

    /**
     * Checks if project page header is visible
     *
     * @return  True if header was visible
     */
    public boolean isProjectPageHeaderVisible(){
        try {
            getWait().until(ExpectedConditions.visibilityOf(projectPageHeader));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Clicks the "Create Project" button to initiate project creation process
     */
    public void createNewProject(){
        waitAndClick(createProjectButton, "Could not click the Create Project button!");
    }

    /**
     * Checks if a given project is visible on the project list
     *
     * @param projectName   Name of the project
     * @return              True if project was visible on the list
     */
    public boolean isProjectVisibleOnList(String projectName){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(getProjectNameSpanBy(projectName)));
        return true;
    }
}
