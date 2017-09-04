package page;

import element.ClickableElement;
import element.LabelElement;
import element.SelectElement;
import element.TextBoxElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Anton_Chyrko on 9/1/2017.
 */
public class TimeEpamPage extends AbstractPage {
    private By currentDay;
    private final static By SITE_LOGO_LOCATOR = By.xpath("//*[@class='logo-container']");
    private final static By PN_NON_PROJECT_ACTIVITIES_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]//div/section[1]/div[1]/i");
    private final static By TODAY_LOCATOR = By.xpath("//*[@class='journal-day cell today']/span[1]");
    private final static By SWITCH_ITEM_WEEK_LOCATOR = By.xpath("//*[@id='choose-project-select']/following-sibling::div/a[1]");
    private final static By ADD_ACTIVITY_LOCATOR = By.xpath("//span[@title='ECS - EPAM Community Source']/parent::div/following-sibling::div/div");
    private final static By CHECK_TIME_LOCATOR  = By.xpath(".//div[@class='table-activity-cell cell today']/input");


    private final static By MON_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]/section/div/section/div/div/section[2]/div[1]/div[1]");
    private final static By TUE_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]/section/div/section/div/div/section[2]/div[1]/div[2]");
    private final static By WED_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]/section/div/section/div/div/section[2]/div[1]/div[3]");
    private final static By THU_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]/section/div/section/div/div/section[2]/div[1]/div[4]");
    private final static By FRI_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]/section/div/section/div/div/section[2]/div[1]/div[5]");
    private final static By SAT_LOCATOR = By.xpath("//*[@class='journal-project-assignments']/div[2]/section/div/section/div/div/section[2]/div[1]/div[6]");

    private final static By SAVE_CHANGES_BUTTON_LOCATOR = By.xpath("//*[@class='bottom-controller show']/button[@class='green button']");
    final static By SITE_LOGO_sLOCATOR = By.xpath("//*[@id=\"root\"]/div/main/section/section/div/div[2]/div[2]/div/section[1]/div[1]/i");

    public TimeEpamPage() {
        super();
        new LabelElement(SITE_LOGO_LOCATOR).isPresent();
    }

    public void setTime() {
        new ClickableElement(SWITCH_ITEM_WEEK_LOCATOR).click();
        new ClickableElement(PN_NON_PROJECT_ACTIVITIES_LOCATOR).click();
        enterTime();
        new ClickableElement(SAT_LOCATOR).click();
        new ClickableElement(SAVE_CHANGES_BUTTON_LOCATOR).click();
    }

    public boolean checkTime(){
        new ClickableElement(PN_NON_PROJECT_ACTIVITIES_LOCATOR).click();
//        new ClickableElement(CHECK_TIME_LOCATOR).click();
        if(new ClickableElement(CHECK_TIME_LOCATOR).getValue().equals("8")){
            return true;
        }else{
            return false;
        }
    }

    private By getTodayLocator(){
        switch (new LabelElement(TODAY_LOCATOR).getText()) {
            case "MON":
                return MON_LOCATOR;
            case "TUE":
                return TUE_LOCATOR;
            case "WED":
                return WED_LOCATOR;
            case "THU":
                return THU_LOCATOR;
            case "FRI":
                return FRI_LOCATOR;
            case "SAT":
            case "SUN":
        }
        return null;
    }

    private TimeEpamPage enterTime() {
        switch (new LabelElement(TODAY_LOCATOR).getText()) {
            case "MON":
                new ClickableElement(ADD_ACTIVITY_LOCATOR).click();
                new TextBoxElement(MON_LOCATOR).typeText("8");
                break;
            case "TUE":
                new TextBoxElement(TUE_LOCATOR).typeText("8");
                break;
            case "WED":
                new TextBoxElement(WED_LOCATOR).typeText("8");
                break;
            case "THU":
                new TextBoxElement(THU_LOCATOR).typeText("8");
                break;
            case "FRI":
                new TextBoxElement(FRI_LOCATOR).typeText("8");
                break;
            case "SAT":break;
            case "SUN":break;
        }
        return this;
    }

    public void deleteTime() {
        new ClickableElement(PN_NON_PROJECT_ACTIVITIES_LOCATOR).click();//херня а не решение
        new ClickableElement(PN_NON_PROJECT_ACTIVITIES_LOCATOR).click();
        new TextBoxElement(FRI_LOCATOR).typeText("0");
        new ClickableElement(SAT_LOCATOR).click();
        new ClickableElement(SAVE_CHANGES_BUTTON_LOCATOR).click();
    }
}
