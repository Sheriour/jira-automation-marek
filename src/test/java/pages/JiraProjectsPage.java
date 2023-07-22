package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;

public class JiraProjectsPage extends  AbstractPage {

    @FindBy(how = How.XPATH, using = "//h1[text()='Projects']")
    WebElement projectPageHeader;


    private By getProjectNameSpanBy(String projectName){
        return By.xpath("//tbody//a/span[contains(text(), '"+projectName+"')]");
    }

    public boolean isProjectPageHeaderVisible(){
        getWait().until(ExpectedConditions.visibilityOf(projectPageHeader));
        return true;
    }

    public boolean isProjectVisibleOnList(String projectName){
        getWait().until(ExpectedConditions.visibilityOfElementLocated(getProjectNameSpanBy(projectName)));
        return true;
    }
}
