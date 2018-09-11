package com.sph.pageObjects;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.AndroidElements;
import com.sph.utilities.IOSElements;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import java.net.MalformedURLException;
import java.time.*;


public class Mobile {
	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(Mobile.class);

    @iOSXCUITFindBy(accessibility = IOSElements.TERMS_CONDITIONS)
    @AndroidFindBy(id = AndroidElements.TERMS_AND_CONDITIONS)
    private MobileElement terms_conditions;

    @iOSXCUITFindBy(accessibility = IOSElements.SKIP_TUTORIALS)
    @AndroidFindBy(id = AndroidElements.SKIP_TUTORIAL)
    private MobileElement skip_tutorials;

    @iOSXCUITFindBy(accessibility = IOSElements.WELCOME_TEXT)
    @AndroidFindBy(id = AndroidElements.WELCOME_TEXT)
    private MobileElement welcome_text;

    @iOSXCUITFindBy(accessibility = IOSElements.CLOSE_INTERSTITIAL_AD_ID)
    @AndroidFindBy(className = AndroidElements.CLOSE_AD)
    private MobileElement close_ad;

    @iOSXCUITFindBy(accessibility = IOSElements.HAMBURGER_MENU)
    @AndroidFindBy(className = AndroidElements.HAMBURGER_MENU)
    private MobileElement hamburger_menu;

    @iOSXCUITFindBy(accessibility = IOSElements.LOG_IN)
    @AndroidFindBy(id = AndroidElements.LOG_IN)
    private MobileElement log_in;

    @iOSXCUITFindBy(className = IOSElements.LOGIN_USERNAME_LOCATOR)
    @AndroidFindBy(id = AndroidElements.USER_NAME)
    private MobileElement userName;

    @iOSXCUITFindBy(className = IOSElements.LOGIN_PASSWORD_LOCATOR)
    @AndroidFindBy(id = AndroidElements.PASSWORD)
    private MobileElement passWord;

    @iOSXCUITFindBy(accessibility = IOSElements.LOGIN_CONTINUE_LABEL)
    @AndroidFindBy(id = AndroidElements.CONTINUE)
    private MobileElement continue_button;

    @iOSXCUITFindBy(accessibility = IOSElements.LOGIN_CONTINUE_LABEL)
    @AndroidFindBy(id = AndroidElements.TITLE_ARTICLE)
    private MobileElement title_article;

    @iOSXCUITFindBy(accessibility = IOSElements.LOGIN_CONTINUE_LABEL)
    @AndroidFindBy(xpath = AndroidElements.LATEST_TAB)
    private MobileElement latest_tab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.LOGIN_CONTINUE_LABEL)
    @AndroidFindBy(xpath = AndroidElements.LOGOUT_BUTTON)
    private MobileElement logout_button;

    @iOSXCUITFindBy(accessibility = IOSElements.NAVIGATION_TITLE)
    //@AndroidFindBy(xpath = AndroidElements.LOGOUT_BUTTON)
    private MobileElement navigation_title;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"" + IOSElements.ST_ICON_ID + "\"]/following-sibling::XCUIElementTypeStaticText")
	@AndroidFindBy(id =  AndroidElements.LOGGED_IN_USER_ID)
	private MobileElement loggedInUser;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"" + IOSElements.ST_ICON_ID + "\"]/following-sibling::XCUIElementTypeStaticText")
	@AndroidFindBy(id =  AndroidElements.LOGOUT_BUTTON)
	private MobileElement logoutMenu;
	
    private WebDriver driver;
    private WebDriverWait wait;

    public Mobile(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public Mobile accept_terms_and_conditions() throws InterruptedException, MalformedURLException {
        System.out.println("Browser is " + browserName);
        if (browserName.equalsIgnoreCase("IOSREMOTE")||browserName.equalsIgnoreCase("IOSLOCAL")) {
            try {
                if (!terms_conditions.isDisplayed()) {
                    try {
                        if (!hamburger_menu.isDisplayed()) {
                            driver.switchTo().alert().accept();
                            logger.info("Allowed the Permissions for ST App Default Notification");
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Hamburger menu not found");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("Not found");
            }
        }
        try {
            if (terms_conditions.isDisplayed()) {
                terms_conditions.click();
            }
        } catch (Exception e) {
            System.out.printf("Term and conditions Not Found");
        }
        logger.info("Terms and condition are accepted");
        return this;
    }
    public Mobile skip_tutorials() throws InterruptedException {
        try {
            if (welcome_text.isDisplayed()) {
                System.out.printf("Welcome text is " + welcome_text.getText());
                Dimension size = this.driver.manage().window ().getSize();
                int startY = (int) (size.height /2);
                int startX = (int) (size.width * 0.90);
                int endX = (int) (size.width * 0.05);
                TouchAction action = new TouchAction ((PerformsTouchActions) driver);
                action.press(PointOption.point(startX,startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                        .moveTo(PointOption.point(endX, startY)).release().perform();
            }
        }
        catch (Exception e)
        {
            System.out.printf("Element Not Found");
        }

        try {
            if (skip_tutorials.isDisplayed()) {
                skip_tutorials.click();
            }
        }
        catch (Exception e)
        {
            System.out.printf("Element Not Found");
        }
        logger.info("Tutorials are skipped");
        return this;
    }
    public Mobile click_hamburger_menu() throws InterruptedException {
        try {
            if (close_ad.isDisplayed()) {
                close_ad.click();
            }
        }
        catch (Exception e)
        {
            System.out.printf("Element Not Present");
        }

        hamburger_menu.click();
        logger.info("Hamburger Menu is clicked");
        return this;
    }

    public Mobile enter_Login_Credentials(String username, String password) throws InterruptedException {
        try {
            if (log_in.isDisplayed()) {
            		log_in.click();
	            userName.sendKeys(username);
	            passWord.sendKeys(password);
            }
        }
        catch (Exception e)
        {
            System.out.println("User is already logged in");
        }
        return this;
    }
    public Mobile click_continue_button() throws InterruptedException {
        continue_button.click();
        return this;
    }
    
    public Mobile verifyLoggedInUser(String username)  throws InterruptedException {
    		hamburger_menu.click();
    		String loggedInText = "";
    		try {
            if (loggedInUser.isDisplayed()) {
            		loggedInText = loggedInUser.getText();
            		System.out.println("LoggedInUser Text: "+ loggedInText);
            		Assert.assertTrue(loggedInText.contains(username),"Invalid Login " + loggedInText);
            }
    		}catch(Exception e)
        {
            System.out.println("LoggedIn user can't be retrieved");
        }
        return this;
	}

    public Mobile read_title_article_on_home_page() throws InterruptedException {
        System.out.println("Today's main article at home page is: "+title_article.getText());
        return this;
    }

    public Mobile navigate_to_latest_tab() throws InterruptedException {
        latest_tab.click();
        System.out.println("Today's main article at latest tab is: "+title_article.getText());
        return this;
    }

    public Mobile logout_app() throws InterruptedException {
        logoutMenu.click();
        System.out.println("Logged out the user successfully");
        return this;
    }

}
