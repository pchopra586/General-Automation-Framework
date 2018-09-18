package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.AndroidElements;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.IOSElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;


/* Class contains page objects for Login screen.*/
public class LoginPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(LoginPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;

	public LoginPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
		util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"" + IOSElements.LOGIN_PAGE_TITLE  + "\"]")
	@AndroidFindBy(id = "toolbar_title")
	private MobileElement pageTitle;
	
	@iOSXCUITFindBy(accessibility = IOSElements.CLOSE_MENU_ID)
	@AndroidFindBy(accessibility = AndroidElements.HAMBURGER_MENU_LOCATOR)
	private MobileElement backBtn;
	
	@iOSXCUITFindBy(accessibility = IOSElements.LOGIN_LABEL_ID)
	@AndroidFindBy(id = AndroidElements.LOGIN_LABEL_ID)
	private MobileElement loginLabel;
	
	@iOSXCUITFindBy(accessibility = IOSElements.MYSPH_LABEL_LOCATOR)
	@AndroidFindBy(className = AndroidElements.MYSPH_LABEL_LOCATOR)
	private MobileElement mySPHLogo;

	@iOSXCUITFindBy(className = IOSElements.LOGIN_USERNAME_LOCATOR)
	@AndroidFindBy(id = AndroidElements.LOGIN_USERNAME_LOCATOR)
	private MobileElement username;

	@iOSXCUITFindBy(className = IOSElements.LOGIN_PASSWORD_LOCATOR)
	@AndroidFindBy(id = AndroidElements.LOGIN_PASSWORD_LOCATOR)
	private MobileElement password;

	@iOSXCUITFindBy(accessibility = IOSElements.LOGIN_CONTINUE_LABEL)
	@AndroidFindBy(id = AndroidElements.LOGIN_CONTINUE_ID)
	private MobileElement continueButton;
	
	@iOSXCUITFindBy(accessibility = IOSElements.FORGOT_PASSWORD_ID)
	@AndroidFindBy(id = AndroidElements.FORGOT_PASSWORD_ID)
	private MobileElement forgotPassword;
	
	@iOSXCUITFindBy(accessibility = IOSElements.HELP_WITH_LOGIN_ID)
	private MobileElement needHelp;

//	@HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.CHAIN)
//	@iOSXCUITFindBy(className = "XCUIElementTypeOther")
//	@iOSXCUITFindBy(className = "XCUIElementTypeButton")
//	@AndroidFindBy(id = "tv_login_name")
//	private MobileElement loggedUser;
//
//	@iOSXCUITFindBy(className = "XCUIElementTypeStaticText")
//	private MobileElement loggedUser_ios;
//
//	@iOSXCUITFindBy(accessibility = "home menu")
//	@AndroidFindBy(id = "iv_home")
//	private MobileElement homeIcon;
//
//	@iOSXCUITFindBy(accessibility = "setting menu")
//	@AndroidFindBy(id = "iv_settings")
//	private MobileElement settingsLink;
//
//	@iOSXCUITFindBy(accessibility = "saved menu")
//	@AndroidFindBy(id = "iv_saved")
//	private MobileElement bookmarkLink;

	public void appLogin(String usernameText, String passwordText) {
		methodName = "appLogin";
		logger.info("Entering Method: " + methodName);

		logger.info("Click on login button");
		
		if(!onLoginPage()) {
			gotoLoginPage();
		}
		
		logger.info("Entering credentials for logging into application");
		username.sendKeys(usernameText);
		password.sendKeys(passwordText);
		continueButton.click();
		
		logger.info("Exiting Method: " + methodName);
	}
	
	public LoginPage gotoLoginPage() {
		methodName = "gotoLoginPage";
		logger.info("Entering Method: " + methodName);
		
//		GenericNavigator navigator = new GenericNavigator(driver, logger);
//		if(!navigator.preConfigured()) {
//			navigator.completeBasicInstallConfig();
//		}
		
		try {
			if(!onLoginPage()) {
				MenuPage menu = new MenuPage(driver);
				menu.clickOnMenu().gotoMenu(Constant.MENU.LOGIN);
			}
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		logger.info("Exiting Method: " + methodName);
		return this;
	}

	public LoginPage verifyLoginPageAlignment() {
		methodName = "verifyLoginPageAlignment";
		logger.info("Entering Method: " + methodName);
		
		try {
			util.isElementDisplayed(pageTitle);
			util.isElementDisplayed(backBtn);
			util.isElementDisplayed(loginLabel);
			util.isElementDisplayed(username);
			util.isElementDisplayed(password);
			util.isElementDisplayed(continueButton);
			util.isElementDisplayed(forgotPassword);
			util.isElementEnabled(mySPHLogo);
			
			Assert.assertTrue(Math.abs(username.getSize().getHeight() - password.getSize().getHeight()) < 4, "Inconsistent height of username and password fields");
			Assert.assertEquals(username.getSize().getWidth(), password.getSize().getWidth(), "Inconsistent width of username and password fields");
			Assert.assertEquals(username.getLocation().getX(), password.getLocation().getX(), "Inconsistent X-axis alignment of username and password fields");
			
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				Assert.assertEquals(pageTitle.getAttribute("label"), IOSElements.LOGIN_PAGE_TITLE, "Inconsistent Login Page title");
				Assert.assertEquals(continueButton.getAttribute("label"), IOSElements.LOGIN_CONTINUE_LABEL, "Inconsistent Continue Button Label");
				Assert.assertEquals(needHelp.getAttribute("label"), IOSElements.HELP_WITH_LOGIN_ID, "Inconsistent label for Help with Login link");
			}
			else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
				Assert.assertEquals(pageTitle.getAttribute("text"), AndroidElements.LOGIN_PAGE_TITLE, "Inconsistent Login Page title");
				Assert.assertEquals(continueButton.getAttribute("text"), AndroidElements.LOGIN_CONTINUE_LABEL, "Inconsistent Continue Button Label");
			}
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public Boolean onLoginPage() {
		methodName = "verifyLoginPageAlignment";
		logger.info("Entering Method: " + methodName);
		Boolean onLoginPage = false;
		
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			util.isElementDisplayed(pageTitle);
			onLoginPage = true;
		}catch(Exception e) {
			onLoginPage = false;
		}
		
		logger.info("Exiting Method: " + methodName);
		return onLoginPage;
	}

//	public SettingsPage openSettingsPage() {
//		Log.INFO("Clicking on settings link");
//		logger.log(Status.INFO,"Clicking on settings link");
//		util.clickifClickable(settingsLink, Constant.SHORT_TIMEOUT);
//		return new SettingsPage(driver, util, logger);
//	}

}
