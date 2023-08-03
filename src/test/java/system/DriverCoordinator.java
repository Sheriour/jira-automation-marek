package system;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
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

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            webDriver.set(driver);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitWebDriver(){
        getWebDriver().quit();
        webDriver.remove();
    }

    public static WebDriverWait getWait(){
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(10));
    }
}
