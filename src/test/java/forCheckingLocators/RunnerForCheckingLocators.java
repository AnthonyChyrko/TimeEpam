package forCheckingLocators;
import driver.Driver;
import element.ClickableElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

/**
 * Created by Anton_Chyrko on 9/4/2017.
 */
public class RunnerForCheckingLocators {
    public static void main(String[] args) {
        By PN_NON_PROJECT_ACTIVITIES_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]//div/section[1]/div[1]/i");
        By ADD_ACTIVITY_LOCATOR = By.xpath("//span[@title='ECS - EPAM Community Source']/parent::div//following-sibling::div/div");
        By CHECK_TIME_LOCATOR  = By.xpath(".//div[@class='table-activity-cell cell today']/input");
        RemoteWebDriver driver = Driver.getInstance().getDriver();
        driver.get(Driver.getUrl());
        new ClickableElement(PN_NON_PROJECT_ACTIVITIES_LOCATOR).click();
        new ClickableElement(CHECK_TIME_LOCATOR).click();
        List<WebElement> webElement = driver.findElements(CHECK_TIME_LOCATOR);
        for (WebElement el: webElement) {
            System.out.println("el.getText() = " + el.getAttribute("value"));
        }

    }
}
