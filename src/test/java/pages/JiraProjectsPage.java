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
    @FindBy(how = How.XPATH, using = "//*[text()='Use template']//ancestor::button")
    WebElement useTemplateButton;
    @FindBy(how = How.CSS, using = "")
    WebElement selectCompanyManagedButton;
    @FindBy(how = How.CSS, using = "button[data-testid*='button-team-managed']")
    WebElement selectTeamManagedButton;

    private By getProjectTemplateButtonBy(String projectTemplateName){
        return By.cssSelector("button[aria-label='"+projectTemplateName+"']");
    }

    private By getProjectNameSpanBy(String projectName){
        return By.xpath("//tbody//a/span[contains(text(), '"+projectName+"')]");
    }

    public boolean isProjectPageHeaderVisible(){
        try {
            getWait().until(ExpectedConditions.visibilityOf(projectPageHeader));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void createNewProject(){
        waitAndClick(createProjectButton, "Could not click the Create Project button!");
    }

    public void useProjectTemplate(){
        waitAndClick(useTemplateButton, "Could not use a project template!");
    }

    public void selectProjectTemplate(String projectTemplateName){
        By templateBy = getProjectTemplateButtonBy(projectTemplateName);
        waitAndClick(
                templateBy, "Could not select a Software Development project template " + projectTemplateName
        );
    }

    public void selectTeamManagedProjectType(){
        waitAndClick(selectTeamManagedButton, "Could not select a Team Managed project type");
    }

    public void selectCompanyManagedProjectType(){
        waitAndClick(selectCompanyManagedButton, "Could not select a Company Managed project type");
    }

    public boolean isProjectVisibleOnList(String projectName){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(getProjectNameSpanBy(projectName)));
        return true;
    }
}
