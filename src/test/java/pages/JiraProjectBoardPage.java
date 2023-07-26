package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;

public class JiraProjectBoardPage {

    @FindBy (how = How.CSS, using = "h2[data-item-title='true']")
    WebElement projectHeader;

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
}

