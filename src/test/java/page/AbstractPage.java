package page;

import driver.Driver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Created by Anton_Chyrko on 9/1/2017.
 */
public class AbstractPage {
    private final RemoteWebDriver driver;

    protected AbstractPage() {
        this.driver = Driver.getInstance().getDriver();
    }

    public void refresh() {
        driver.navigate().refresh();
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

}
