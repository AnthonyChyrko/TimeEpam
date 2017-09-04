package driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anton_Chyrko on 9/1/2017.
 */
public class Driver {
    private final static String PROPERTIES_FILE = "src/test/resources/driver.properties";
    private final RemoteWebDriver driver;
    private static Driver instance;
    private static int defaultImplicitWaitTimeOut;
    private static int defaultExplicitWaitTimeout;
    private static String url;

    public Driver() {
        this.driver = setUpDriver();
    }

    public static String getUrl() {
        return url;
    }

    public static int getDefaultImplicitWaitTimeOut() {
        return defaultImplicitWaitTimeOut;
    }

    public static int getDefaultExplicitWaitTimeout() {
        return defaultExplicitWaitTimeout;
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public static Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    private static void initProperties() {
        Properties properties;
        try (FileInputStream fileProp = new FileInputStream(PROPERTIES_FILE)) {
            properties = new Properties();
            properties.load(fileProp);
            defaultImplicitWaitTimeOut = Integer.parseInt(properties.getProperty("defaultImplicitWaitTimeout"));
            defaultExplicitWaitTimeout = Integer.parseInt(properties.getProperty("defaultExplicitWaitTimeout"));
            url = properties.getProperty("url");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RemoteWebDriver setUpDriver() {
        RemoteWebDriver driver;
        initProperties();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(defaultImplicitWaitTimeOut, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(defaultImplicitWaitTimeOut, TimeUnit.SECONDS);
        return driver;
    }

    public static void closeInstance(){
        if (instance.driver != null){
            instance.driver.quit();
        }
        instance = null;
    }

}
