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
import com.sph.utilities.Constant.MENU;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.IOSElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;


public class MenuPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(IntroductionPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"" + IOSElements.ST_ICON_ID + "\"]/following-sibling::XCUIElementTypeStaticText")
	@AndroidFindBy(id =  AndroidElements.LOGGED_IN_USER_ID)
	private MobileElement loggedInUser;
	
	//@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name=\"" + IOSElements.ST_ICON_ID + "\"]/following-sibling::XCUIElementTypeStaticText")
	@AndroidFindBy(id =  AndroidElements.LOGOUT_MENU_ID)
	private MobileElement logoutMenu;
	
	@iOSXCUITFindBy(id = IOSElements.ARTICLE_SEARCH_BOX)
	@AndroidFindBy(id =  AndroidElements.ARTICLE_SEARCH_BOX)
	private MobileElement searchBar;
	
	@iOSXCUITFindBy(id = IOSElements.ST_ICON_ID)
	@AndroidFindBy(id =  AndroidElements.ST_ICON_ID)
	private MobileElement stIcon;
	
	@iOSXCUITFindBy(accessibility =  IOSElements.HAMBURGER_MENU_LOCATOR)
	@AndroidFindBy(accessibility =  AndroidElements.HAMBURGER_MENU_LOCATOR)
	private MobileElement hamburgerMenu;
	
	@iOSXCUITFindBy(id = IOSElements.SUBSCRIBE_MENU_ID)
	@AndroidFindBy(id = AndroidElements.SUBSCRIBE_MENU_ID)
	private MobileElement subscribeMenu;
	
	@iOSXCUITFindBy(id = IOSElements.LOGIN_MENU_ID)
	@AndroidFindBy(id = AndroidElements.LOGIN_MENU_ID)
	private MobileElement loginMenu;
	
	@iOSXCUITFindBy(id = IOSElements.HOME_PAGE_MENU_ID)
	@AndroidFindBy(id = AndroidElements.HOME_PAGE_MENU_ID)
	private MobileElement homePageMenu;
	
	@iOSXCUITFindBy(id = IOSElements.EPAPER_MENU_ID)
	@AndroidFindBy(id = AndroidElements.EPAPER_MENU_ID)
	private MobileElement ePaperMenu;
	
	@iOSXCUITFindBy(id = IOSElements.BOOKMARK_MENU_ID)
	@AndroidFindBy(id = AndroidElements.BOOKMARK_MENU_ID)
	private MobileElement bookmarkMenu;
	
	@iOSXCUITFindBy(id = IOSElements.PRINT_EDITION_MENU_ID)
	@AndroidFindBy(id = AndroidElements.PRINT_EDITION_MENU_ID)
	private MobileElement printEditionMenu;
	
	@iOSXCUITFindBy(id = IOSElements.SETTINGS_MENU_ID)
	@AndroidFindBy(id = AndroidElements.SETTINGS_MENU_ID)
	private MobileElement settingsMenu;
	
	@iOSXCUITFindBy(id = IOSElements.MORE_FROM_ST_MENU_ID)
	@AndroidFindBy(id = AndroidElements.MORE_FROM_ST_MENU_ID)
	private MobileElement moreFromSTMenu;
	
	public MenuPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 20);
		util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public MenuPage gotoMenuPage() {
		methodName = "gotoMenuPage";
		//Log.INFO("Entering Method: " + methodName);
		logger.info("Entering Method: " + methodName);
		
		try {
			clickOnMenu();
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
	
		//Log.INFO("Exiting Method: " + methodName);
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public boolean basicMenuValidation() {
		boolean validated = false;
		methodName = "sectionTitleValidation";
		//Log.INFO("Entering Method: " + methodName);
		logger.info("Entering Method: " + methodName);

		
		//Log.INFO("Exiting Method: " + methodName);
		logger.info("Exiting Method: " + methodName);
		return validated;
	}
	
	public boolean onMenuPage() {
		methodName = "sectionTitleValidation";
		//Log.INFO("Entering Method: " + methodName);
		logger.info("Entering Method: " + methodName);
		boolean onMenuPage = false;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				Assert.assertTrue(ePaperMenu.isDisplayed(),"Not on Menu Page: as missing ePaper Menu");
				Assert.assertTrue(bookmarkMenu.isDisplayed(), "Not on Menu Page: as missing bookmark Menu");
			}else {
				Assert.assertTrue(ePaperMenu.isDisplayed(),"Not on Menu Page: as missing ePaper Menu");
				Assert.assertTrue(bookmarkMenu.isDisplayed(), "Not on Menu Page: as missing bookmark Menu");
			}
			onMenuPage = true;
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		
		logger.info("Exiting Method: " + methodName);
		return onMenuPage;
	}
	
	public MenuPage clickOnMenu() {
		methodName = "clickOnMenu";
		logger.info("Entering Method: " + methodName);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			hamburgerMenu.click();
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public MobileDriver gotoMenu(MENU menu) {
		methodName = "gotoMenu - " + menu + "";
		logger.info("Entering Method: " + methodName);
		
		try {
			switch(menu) {
				case LOGOUT: 
					util.clickifClickable(logoutMenu, Constant.LONG_TIMEOUT);
				case SUBSCRIBE: 
					util.clickifClickable(subscribeMenu, Constant.LONG_TIMEOUT);
				case LOGIN: 
					util.clickifClickable(loginMenu, Constant.LONG_TIMEOUT);
				case HOME_PAGE: 
					util.clickifClickable(homePageMenu, Constant.LONG_TIMEOUT);
				case EPAPER: 
					util.clickifClickable(ePaperMenu, Constant.LONG_TIMEOUT);
				case BOOKMARK: 
					util.clickifClickable(bookmarkMenu, Constant.LONG_TIMEOUT);
				case PRINT_EDITION: 
					util.clickifClickable(printEditionMenu, Constant.LONG_TIMEOUT);
				case SETTINGS: 
					util.clickifClickable(settingsMenu, Constant.LONG_TIMEOUT);
				case MORE_FROM_ST: 
					util.clickifClickable(moreFromSTMenu, Constant.LONG_TIMEOUT);
				default:
					logger.error("Please provide valid Menu Title");
					break;	
			}
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		
		return (MobileDriver) driver;
	}
	
//	public boolean gotoEditHomeSettings() {
//		boolean onEditHomePage = false;
//		methodName = "gotoEditHomePage";
//		//Log.INFO("Entering Method: " + methodName);
//		logger.info("Entering Method: " + methodName);
//		MobileElement editHomeOption = null;
//		MobileElement currentPage = null;
//		
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		if(driver.getPlatformName().equalsIgnoreCase("Android")) {
//			
//		}
//		else if(driver.getPlatformName().equalsIgnoreCase("iOS")) {
//			//Goto to Settings Menu
//			gotoMenuPage();
//			gotoSelectedMenu(IOSElements.SETTINGS_MENU_LABEL);
//			
//			editHomeOption = driver.findElement(By.name("Edit Home"));
//		    editHomeOption.click();
//		    
////		    currentPage = driver.findElement(By.name("EDIT HOME"));
////		    Assert.assertEquals(currentPage.getAttribute("label"), "EDIT HOME","The label of Edit Home Page is inconsistent with expected");
////		    onEditHomePage = true;
//		}
//		
//		//Log.INFO("Exiting Method: " + methodName);
//		logger.info("Exiting Method: " + methodName);
//		return onEditHomePage;
//	}
	
//	public boolean goBackToHomePage() {
//		methodName = "closeSettingsView";
//		//Log.INFO("Entering Method: " + methodName);
//		logger.info("Entering Method: " + methodName);
//		boolean closedSettingsView = false;
//		
//		MobileElement closeSettingsButton = null;
//		
//		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) { {
//			closeSettingsButton = driver.findElementByXPath("//XCUIElementTypeButton[@name=\"" + IOSElements.SETTINGS_LABEL + "\"]/following-sibling::XCUIElementTypeButton");
//		}
//		
//		closeSettingsButton.click();
//		
//		closedSettingsView = true;
//		//Log.INFO("Exiting Method: " + methodName);
//		logger.info("Exiting Method: " + methodName);
//		return closedSettingsView;
//	}
	
	public MenuPage verifyLoggedInUser(String verifyUser) {
		methodName = "verifyLoggedInUser";
		logger.info("Entering Method: " + methodName);
		String loggedInText = "";
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			loggedInText = loggedInUser.getText();
			
			logger.info("Logged In User text: " + loggedInText);
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
			
		Assert.assertTrue(loggedInText.contains("Logged in as " + verifyUser),"Invalid Login " + loggedInText);
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public MenuPage verifyLogoutLinkDisplayed() {
		methodName = "verifyLoggedInUser";
		logger.info("Entering Method: " + methodName);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public MenuPage logout() {
		methodName = "logout";
		logger.info("Entering Method: " + methodName);
		
		try {
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
				logoutMenu.click();
			}
			else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				driver = gotoMenu(Constant.MENU.SETTINGS);
				
				SettingsPage settings = new SettingsPage(driver);
				driver = settings.gotoSettings(Constant.SETTINGS_MENU.ACCOUNT);
				
				AccountPage account = new AccountPage(driver);
				account.logout();
			}
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}
}
