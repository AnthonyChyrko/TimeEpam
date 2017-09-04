package test;

import driver.Driver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import page.TimeEpamPage;

import java.io.File;

/**
 * Created by Anton_Chyrko on 9/1/2017.
 */
public class TimeManagementTst {
    public TimeEpamPage page;

    @BeforeSuite
    public void setUp() {
        RemoteWebDriver driver = Driver.getInstance().getDriver();
        driver.get(Driver.getUrl());
        page = new TimeEpamPage();
    }

    @Test
    public void firstTst() {
        page.setTime();
        Assert.assertTrue(page.checkTime());
    }

    @AfterSuite
    public void tearDown(){
//        page.deleteTime();
        Driver.closeInstance();
    }
}
