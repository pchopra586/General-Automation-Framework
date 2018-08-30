package com.sph.straittimes.page_objects;

import com.sph.straittimes.utilities.AndroidElements;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.sph.straittimes.Common.PropLocation;
import com.sph.straittimes.Common.ReadPropertiesValues;
import com.sph.straittimes.utilities.IOSElements;

import java.net.MalformedURLException;
import java.time.*;



public class Android {

    PropLocation file = new PropLocation();
    ReadPropertiesValues loc = new ReadPropertiesValues(file.location());
    ReadPropertiesValues elementLoc = new ReadPropertiesValues(file.elementLocation());
    Logger logger= Logger.getLogger(Android.class);
    
    @iOSXCUITFindBy(xpath = "\"//XCUIElementTypeButton[@id=\\\"\"" + IOSElements.TERMS_CONDITIONS + "\"]")
	@AndroidFindBy(id = AndroidElements.TERMS_AND_CONDITIONS)
	private MobileElement terms_conditions;
    
    @iOSXCUITFindBy(xpath = "\"//XCUIElementTypeButton[@id=\\\"\"" + IOSElements.TERMS_CONDITIONS + "\"]")
	@AndroidFindBy(id = AndroidElements.SKIP_TUTORIAL)
	private MobileElement skip_tutorials;

    static WebDriver driver;
    public Android(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        //PageFactory.initElements(this.driver, this);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public Android accept_terms_and_conditions() throws InterruptedException {
        Thread.sleep(10000);
        terms_conditions.click();
        logger.info("Terms and condition are accepted");
        return this;
    }

    public Android skip_tutorials() throws InterruptedException {
        Thread.sleep(5000);
        WebElement WelcomeText = this.driver.findElement(By.id(elementLoc.readProperty("welcome_text")));
        Dimension size = this.driver.manage().window ().getSize();
        int startX = (int) (size.width * 0.8);
        int startY = size.height / 2;
        int endX = (int) (size.width * 0.40);
        TouchAction action = new TouchAction ((PerformsTouchActions) driver);
        action.press(PointOption.point(startX,startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, startY)).release().perform();
        //driver.performTouchAction(action);
        Thread.sleep(8000);
        driver.findElement(By.id(elementLoc.readProperty("skip_tutorial_android"))).click();
        logger.info("Tutorials are skipped");
        return this;
    }
    public Android click_hamburger_menu() throws InterruptedException {
        Thread.sleep(10000);
        try {
            if (driver.findElement(By.className(elementLoc.readProperty("close_ad"))).isDisplayed()) {
                driver.findElement(By.className(elementLoc.readProperty("close_ad"))).click();
            }
        }
        catch (Exception e)
        {
            System.out.printf("Element Not Present");
        }

        Thread.sleep(5000);
        driver.findElement(By.className(elementLoc.readProperty("hamburger_menu"))).click();
        logger.info("Hamburger Menu is clicked");
        return this;
    }

    public Android enter_Login_Credentials() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id(elementLoc.readProperty("mobile_login_button"))).click();
        Thread.sleep(5000);
        driver.findElement(By.id(elementLoc.readProperty("app_login_id"))).sendKeys("Premium_access1");
        driver.findElement(By.id(elementLoc.readProperty("app_password"))).sendKeys("Password123");
        return this;
    }
    public Android click_continue_button() throws InterruptedException {
        driver.findElement(By.id(elementLoc.readProperty("continue_button_app"))).click();
        return this;
    }
    public Android verify_userName() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.className(elementLoc.readProperty("hamburger_menu"))).click();
        String User_Name =driver.findElement(By.id(elementLoc.readProperty("verify_username_app"))).getText();
        System.out.println("User is logged in: "+User_Name);
        Dimension size = this.driver.manage().window ().getSize();
        int startX = (int) (size.width * 0.8);
        int startY = size.height / 2;
        int endX = (int) (size.width * 0.20);
        //int endY = (int) (startY * 0.75 * -1);
        TouchAction action = new TouchAction ((PerformsTouchActions) this.driver);
        action.press(PointOption.point(startX,startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, startY)).release().perform();
        return this;
    }
    public Android click_latest_tab() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath(elementLoc.readProperty("latest_tab"))).click();
        //driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[@index='1'])")).click();

        return this;
    }
    public Android click_first_article() throws InterruptedException {
        Thread.sleep(5000);
        String Article_headline = driver.findElement(By.xpath(elementLoc.readProperty("app_article_headline"))).getText();
        System.out.println("Article Headline is" + Article_headline);
        driver.findElement(By.xpath(elementLoc.readProperty("app_article_headline"))).click();
        return this;
    }
    public Android verify__first_article() throws InterruptedException {
        Thread.sleep(10000);
        driver.switchTo().alert().accept();
        driver.findElement(By.id(elementLoc.readProperty("article_headline"))).click();
        WebElement Article_Headline_Text = driver.findElement(By.id(elementLoc.readProperty("article_headline")));
        String Read_Article_Headline=Article_Headline_Text.getText();
        System.out.println("Article Headline is: "+Read_Article_Headline);
        /*WebElement Article_Headline_Image = driver.findElement(By.className(elementLoc.readProperty("app_article_image")));
        Boolean ImagePresent_MainArticle_Verify = (Boolean)((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", Article_Headline_Image);
        if (!ImagePresent_MainArticle_Verify)
        {
            System.out.println("Image is not present");
        }
        else
        {
            System.out.println("Image is Present");
        }*/
        return this;
    }

    public Android back_button() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.id(elementLoc.readProperty("back_button_android"))).click();
        return this;
    }

    public Android logout_app() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.id(elementLoc.readProperty("log_out_android"))).click();
        return this;
    }
}
