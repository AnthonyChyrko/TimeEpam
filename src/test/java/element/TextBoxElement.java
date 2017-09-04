package element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TextBoxElement extends BaseElement {
    public TextBoxElement(By locator) {
        super(locator);
    }

    public TextBoxElement(WebElement element) {
        super(element);
    }

    public void type(String text) {
        init();
        element.clear();
        element.sendKeys(text);

    }


    public void typeText(String text) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys("\b");
        actions.click();
        actions.sendKeys(text);
        actions.build().perform();
    }

}
