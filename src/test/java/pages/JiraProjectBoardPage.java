package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;

public class JiraProjectBoardPage {

    @FindBy (how = How.CSS, using = "h2[data-item-title='true']")
    WebElement projectHeader;
    @FindBy (how = How.XPATH, using = "//span[text()='Go to Backlog']//parent::a")
    WebElement goToBacklogScrumButton;
    @FindBy (how = How.CSS, using = "[title='Kanban board']")
    WebElement kanbanBoardHeader;
    @FindBy (how = How.XPATH, using = "//h1/span[text()='Issues']")
    WebElement bugTrackingHeader;

    /**
     * Get a By for specific project type message
     *
     * @param projectType   Type of the project (team-managed or company-managed)
     * @return              By locator for the message
     */
    private By getProjectTypeInfoBy(String projectType){
        return By.xpath("//span[contains(text(), 'in a "+projectType+" project')]");
    }

    /**
     * Checks that the project board contains given project name in the header
     *
     * @param projectName   Name of the project
     * @return              True if name was found in the header
     */
    public boolean projectHeaderContainsName(String projectName){
        try {
            getWait().until(ExpectedConditions.textToBePresentInElement(projectHeader, projectName));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Checks if the page displays "You're in a xyz project" with value either team-managed or company-managed
     *
     * @param projectType   Type of the project (team-managed or company-managed)
     * @return              True if text was visible
     */
    public boolean displayedProjectTypeIs(String projectType){
        try {
            By projectTypeBy = getProjectTypeInfoBy(projectType);
            getWait().until(ExpectedConditions.visibilityOfElementLocated(projectTypeBy));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Checks if current project shows the kanban board header
     *
     * @return  True if kanban board header was present
     */
    public boolean isProjectBoardForKanban(){
        try {
            getWait().until(ExpectedConditions.visibilityOf(kanbanBoardHeader));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Checks if current project shows the scrum button "Go to backlog"
     *
     * @return  True if the button was present
     */
    public boolean isProjectBoardForScrum(){
        try {
            getWait().until(ExpectedConditions.visibilityOf(goToBacklogScrumButton));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Checks if current project shows the Issues board header
     *
     * @return  True if Issues board header was present
     */
    public boolean isProjectBoardForBugTracking(){
        try {
            getWait().until(ExpectedConditions.visibilityOf(bugTrackingHeader));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}

