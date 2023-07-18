package pages;

import org.openqa.selenium.support.PageFactory;
import system.DriverCoordinator;

public abstract class AbstractPage extends PageFactory
{
    public AbstractPage(){
        initElements(DriverCoordinator.getWebDriver(), this);
    }
}
