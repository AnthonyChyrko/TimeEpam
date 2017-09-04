package element;

import driver.Driver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class BaseElement {
    final static Logger logger = LogManager.getLogger(BaseElement.class.getName());
    protected final RemoteWebDriver driver;
    private By locator;
    protected WebElement element;
    private final static String STYLE = "arguments[0].style.border='3px solid red'";
    private List<WebElement> list;

    private BaseElement() {
        driver = Driver.getInstance().getDriver();
    }

    public BaseElement(By locator) {
        this();
        this.locator = locator;
        isPresent();
    }

    public BaseElement(WebElement element) {
        this();
        this.element = element;
    }

    public WebElement getElement() {
        if (element == null) {
            init();
        }
        return element;
    }

    public String getText() {
        WebDriverWait wait = new WebDriverWait(driver, Driver.getDefaultExplicitWaitTimeout());
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    element.getText();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        });

        return element.getText();
    }

    public String getValue() {
        WebDriverWait wait = new WebDriverWait(driver, Driver.getDefaultExplicitWaitTimeout());
        wait.until((ExpectedCondition<Boolean>) webDriver -> {
            try {
                element.getAttribute("value");
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println(element.getAttribute("value"));
        return element.getAttribute("value");
    }

    public List<WebElement> getList() {
        return list;
    }

    public boolean isPresent() {
        return isPresent(Driver.getDefaultImplicitWaitTimeOut());
    }

    public boolean isPresent(int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            waitForIsElementPresent(wait);

        } catch (Exception e) {
            logger.error(e);
            return false;
        }

        try {
            driver.manage().timeouts().implicitlyWait(Driver.getDefaultImplicitWaitTimeOut(), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    private void waitForIsElementPresent(WebDriverWait wait){
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    if (!BaseElement.this.waitForJSandJQueryToLoad()) {
                        return false;
                    }
                    list = driver.findElements(locator);
                    for (WebElement el : list) {
                        if (el instanceof RemoteWebElement && el.isDisplayed()) {
                            element = el;
                            element.getText();
                            return element.isDisplayed();
                        }
                    }
                    element = driver.findElement(locator);
                } catch (Exception e) {
                    return false;
                }
                return element.isDisplayed();
            }
        });

    }

    private boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, Driver.getDefaultExplicitWaitTimeout());

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) this.driver.executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> this.driver.executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    void init() {
        if (element != null) {
            driver.executeScript(STYLE, element);
        }

    }

}
