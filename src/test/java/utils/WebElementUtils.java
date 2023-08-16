package utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static system.DriverCoordinator.getWait;

@Slf4j
public class WebElementUtils {

    public static void waitAndClick(By elementBy, String failMessage){
        try {
            waitAndClick(elementBy);
        }
        catch (Exception e){
            log.error(failMessage);
            throw e;
        }
    }

    public static void waitAndClick(WebElement element, String failMessage){
        try {
            waitAndClick(element);
        }
        catch (Exception e){
            log.error(failMessage);
            throw e;
        }
    }

    public static void waitAndClick(By elementBy){
        getWait()
                .until(ExpectedConditions.elementToBeClickable(elementBy))
                .click();
    }

    public static void waitAndClick(WebElement element){
        getWait()
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    public static void waitAndFillField(WebElement element, String text, String failMessage){
        try {
            getWait().until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
        }
        catch (Exception e){
            log.error(failMessage);
            throw e;
        }
    }

    public static void waitAndFillField(By by, String text, String failMessage) {
        try {
            WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(by));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            log.error(failMessage);
            throw e;
        }
    }
}
