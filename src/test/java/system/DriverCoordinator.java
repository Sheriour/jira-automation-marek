package system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverCoordinator
{
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static boolean hasDriver(){
        return webDriver.get() != null;
    }

    public static WebDriver getWebDriver(){
        WebDriver driver = webDriver.get();
        if (driver == null) {
            driver = new ChromeDriver();
            webDriver.set(driver);
        }
        return driver;
    }

    public static WebDriverWait getWait(){
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
    }
}
