package steps;

import io.cucumber.java.*;
import system.DriverCoordinator;

public class Hooks
{
    @After
    public void quitDriver(){
        if (DriverCoordinator.hasDriver())
            DriverCoordinator.getWebDriver().quit();
    }
}
