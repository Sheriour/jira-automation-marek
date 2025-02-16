package steps;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import system.DriverCoordinator;
import system.EntityManager;
import system.PageRepository;

public class Hooks
{
    @After
    public void cleanup(){
        EntityManager.deleteEntities();
    }

    @After
    public void teardown(Scenario scenario){
        if (DriverCoordinator.hasDriver()) {
            if (scenario.isFailed()) {
                // Take a screenshot...
                final byte[] screenshot = ((TakesScreenshot)DriverCoordinator.getWebDriver()).getScreenshotAs(OutputType.BYTES);
                // embed it in the report.
                scenario.attach(screenshot, "image/png", "screenshot");
            }
            DriverCoordinator.quitWebDriver();
        }
        PageRepository.deleteAllPages();
    }
}
