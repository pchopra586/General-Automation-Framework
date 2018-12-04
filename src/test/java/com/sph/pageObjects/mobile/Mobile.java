package com.sph.pageObjects.mobile;

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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.time.*;


public class Mobile {
	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(Mobile.class);

    @iOSXCUITFindBy(accessibility = IOSElements.TERMS_CONDITIONS)
    @AndroidFindBy(id = AndroidElements.TERMS_AND_CONDITIONS)
    private MobileElement terms_conditions;

    @iOSXCUITFindBy(accessibility = IOSElements.SKIP_TUTORIALS)
    @AndroidFindBy(id = AndroidElements.SKIP_TUTORIAL)
    private MobileElement skip_tutorials;

    @iOSXCUITFindBy(accessibility = IOSElements.WELCOME_TEXT)
    @AndroidFindBy(id = AndroidElements.WELCOME_TEXT)
    private MobileElement welcome_text;

    @AndroidFindBy(xpath = AndroidElements.CLOSE_AD)
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
    @AndroidFindBy(xpath = AndroidElements.LOGOUT_BUTTON)
    private MobileElement logoutButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"" + IOSElements.ST_ICON_ID + "\"]/following-sibling::XCUIElementTypeStaticText")
	@AndroidFindBy(id =  AndroidElements.LOGGED_IN_USER_ID)
	private MobileElement loggedInUser;
	
    @iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_LOGOUT_ID)
	@AndroidFindBy(id =  AndroidElements.LOGOUT_BUTTON)
	private MobileElement logoutMenu;
	
	@iOSXCUITFindBy(id = IOSElements.SETTINGS_MENU_ID)
	@AndroidFindBy(id = AndroidElements.SETTINGS_MENU_ID)
	private MobileElement settingsMenu;
	
	@iOSXCUITFindBy(id = IOSElements.ACCOUNT_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_SETTINGS_ID)
	private MobileElement accountSettings;
	
    private WebDriver driver;
    WebDriverWait wait;

    public Mobile(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public Mobile accept_terms_and_conditions() throws InterruptedException, MalformedURLException {
//    		driver.switchTo().alert().accept();
    		log.info("Browser is " + browserName);
        try {
            if (terms_conditions.isDisplayed()) {
                terms_conditions.click();
            }
        } catch (Exception e) {
            System.out.printf("Term and conditions Not Found");
        }
        log.info("Terms and condition are accepted");
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
        log.info("Tutorials are skipped");
        return this;
    }
    public Mobile click_hamburger_menu() throws InterruptedException {
    		hamburger_menu.click();
    		try {
        		if (browserName.equalsIgnoreCase("ANDROIDREMOTE")||browserName.equalsIgnoreCase("ANDROIDLOCAL")) {
	            if (close_ad.isDisplayed()) {
	                close_ad.click();
	            }
        		}
        }
        catch (Exception e)
        {
            System.out.printf("Element Not Present");
        }
    		System.out.printf("Hamburger Menu is clicked");
        log.info("Hamburger Menu is clicked");
        return this;
    }

    public Mobile enter_Login_Credentials(String username, String password) throws InterruptedException {		
    		System.out.printf("Click on Login Button");
    		try {
    			int trial = 1;
    			Boolean loginFound = false;
    			while(trial < 4 && !loginFound) {
    				try {
    					if(log_in.isDisplayed()) {
		    				loginFound = true;
    						break;
    					}
	    			}
				catch (Exception e)
		        {
					hamburger_menu.click();
    					trial = trial + 1;
		        }
    			}
            if (log_in.isDisplayed()) {
            		log_in.click();
            		
	            userName.sendKeys(username);
	            passWord.sendKeys(password);
	            continue_button.click();
            }
        }
    		catch (Exception e)
        {
    			System.out.printf("Element Not Found");
        }
        
        return this;
    }
    
    public Mobile verifyLoggedInUser(String username)  throws InterruptedException {
    		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
    		
    		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
    			hamburger_menu.click();
    		}
    		
    		String loggedInText = "";
    		try {
            if (loggedInUser.isDisplayed()) {
            		loggedInText = loggedInUser.getText();
            		log.info("LoggedInUser Text: "+ loggedInText);
            		Assert.assertTrue(loggedInText.contains(username),"Invalid Login " + loggedInText);
            }
    		}catch(Exception e)
        {
            log.error("LoggedIn user can't be retrieved");
        }
        return this;
	}
    
    public Mobile gotoSettingsMenu()  throws InterruptedException {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		
		try{
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				settingsMenu.click();
			}
		}catch(Exception e)
	    {
	        log.error("Can't be navigated to Settings Menu");
	    }
		return this;
    }
    
    public Mobile gotoAccountSettings()  throws InterruptedException {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		
		try{
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				accountSettings.click();
			}
		}catch(Exception e)
	    {
	        log.error("Can't be navigated to Account Settings");
	    }
		return this;
    }
    

    public Mobile logout_app() throws InterruptedException {
        logoutMenu.click();
        log.info("Logged out the user successfully");
        return this;
    }

}
