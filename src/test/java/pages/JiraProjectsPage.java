package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;

@Slf4j
public class JiraProjectsPage extends  AbstractPage {

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
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(createProjectButton)).click();
        }
        catch (Exception e){
            log.error("Could not click the Create Project button!");
            throw e;
        }
    }

    public void useProjectTemplate(){
        try {
            getWait()
                    .until(ExpectedConditions.elementToBeClickable(useTemplateButton))
                    .click();
        }
        catch (Exception e){
            log.error("Could not use a project template!");
            throw e;
        }
    }

    public void selectProjectTemplate(String projectTemplateName){
        try {
            By templateBy = getProjectTemplateButtonBy(projectTemplateName);
            getWait()
                    .until(ExpectedConditions.elementToBeClickable(templateBy))
                    .click();
        }
        catch (Exception e){
            log.error("Could not select a Software Development project template " + projectTemplateName);
            throw e;
        }
    }

    public void selectTeamManagedProjectType(){
        try {
            getWait()
                    .until(ExpectedConditions.elementToBeClickable(selectTeamManagedButton))
                    .click();
        }
        catch (Exception e){
            log.error("Could not select a Team Managed project type");
            throw e;
        }
    }

    public void selectCompanyManagedProjectType(){
        try {
            getWait()
                    .until(ExpectedConditions.elementToBeClickable(selectCompanyManagedButton))
                    .click();
        }
        catch (Exception e){
            log.error("Could not select a Company Managed project type");
            throw e;
        }
    }



    public boolean isProjectVisibleOnList(String projectName){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(getProjectNameSpanBy(projectName)));
        return true;
    }
}
