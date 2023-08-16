package pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;
import static utils.WebElementUtils.waitAndClick;
import static utils.WebElementUtils.waitAndFillField;

@Slf4j
public class JiraProjectCreationPage {

    @FindBy(how = How.XPATH, using = "//*[text()='Use template']//ancestor::button")
    WebElement useTemplateButton;
    @FindBy(how = How.CSS, using = "button[data-testid*='button-company-managed']")
    WebElement selectCompanyManagedButton;
    @FindBy(how = How.CSS, using = "button[data-testid*='button-team-managed']")
    WebElement selectTeamManagedButton;
    @FindBy(how = How.ID, using = "project-create.create-form.name-field.input")
    WebElement newProjectNameInput;
    @FindBy(how = How.CSS, using = "[data-testid*='submit-button'] > button")
    WebElement createProjectFinishButton;
    @FindBy(how = How.CSS, using = " [id*='key-field.input']")
    WebElement projectKeyInput;
    @FindBy(how = How.XPATH, using = "//span[@aria-label='close']//ancestor::button")
    WebElement abandonTemplateButton;
    @FindBy(how = How.XPATH, using = "//span[text()='Change template']//parent::button")
    WebElement changeTemplateButton;
    @FindBy(how = How.XPATH, using = "//span[text()='Skip']//parent::button")
    WebElement skipIntegrationButton;

    /**
     * Returns a By for a given project template on the list
     *
     * @param projectTemplateName   Name of the template
     * @return                      By locator for given template button
     */
    private By getProjectTemplateButtonBy(String projectTemplateName){
        return By.cssSelector("button[aria-label='"+projectTemplateName+"']");
    }

    /**
     * Selects a given project template from the list
     *
     * @param projectTemplateName   Name of the project template
     */
    public void selectProjectTemplate(String projectTemplateName){
        By templateBy = getProjectTemplateButtonBy(projectTemplateName);
        waitAndClick(
                templateBy, "Could not select a Software Development project template " + projectTemplateName
        );
    }

    /**
     * Confirms the choice of a previously selected project template
     */
    public void useProjectTemplate(){
        waitAndClick(useTemplateButton, "Could not use a project template!");
    }

    /**
     * Selects "team managed" project type
     */
    public void selectTeamManagedProjectType(){
        waitAndClick(selectTeamManagedButton, "Could not select a Team Managed project type");
    }

    /**
     * Selects "company managed" project type
     */
    public void selectCompanyManagedProjectType(){
        waitAndClick(selectCompanyManagedButton, "Could not select a Company Managed project type");
    }

    /**
     * Types given project name in the top input field
     *
     * @param name  Name of the project to be created
     */
    public void provideProjectName(String name){
        waitAndFillField(
                newProjectNameInput,
                name,
                "Could not fill out project name field!");
    }

    /**
     * Waits for the project key to auto-generate and clicks the Create Project button.
     */
    public void finishCreatingProject() {
        getWait().until(ExpectedConditions.attributeToBeNotEmpty(projectKeyInput, "value"));
        waitAndClick(
                createProjectFinishButton,
                "Could not click Create Project button to finalise project creation!"
        );
    }

    /**
     * Presses the X button visible while confirming use of a template
     */
    public void abandonTemplate() {
        waitAndClick(
                abandonTemplateButton,
                "Could not click the X button to abandon previewed template!"
        );
    }

    /**
     * Presses the Change Template button visible while selecting project type or while providing project name
     */
    public void changeTemplate() {
        waitAndClick(
                changeTemplateButton,
                "Could not click the Change Template button while choosing project type!"
        );
    }

    /**
     * Presses the Skip button visible while selecting integration with other Atlassian products
     */
    public void skipIntegration() {
        waitAndClick(
                skipIntegrationButton,
                "Could not click the Skip button to skip Atlassian product integration for a project!"
        );
    }
}
