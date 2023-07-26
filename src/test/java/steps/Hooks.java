package steps;

import io.cucumber.java.*;
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
    public void teardown(){
        if (DriverCoordinator.hasDriver())
            DriverCoordinator.quitWebDriver();
        PageRepository.deleteAllPages();
    }
}
