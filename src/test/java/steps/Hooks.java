package steps;

import io.cucumber.java.*;
import system.DriverCoordinator;
import system.EntityManager;

public class Hooks
{
    @After
    public void cleanup(){
        EntityManager.deleteEntities();
    }

    @After
    public void quitDriver(){
        if (DriverCoordinator.hasDriver())
            DriverCoordinator.getWebDriver().quit();
    }
}
