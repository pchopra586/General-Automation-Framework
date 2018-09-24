package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.AndroidElements;
import com.sph.utilities.Constant;
import com.sph.utilities.GenericNavigator;
import com.sph.utilities.IOSElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PrintEditionPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(PrintEditionPage.class);
	private WebDriver driver;
    private Capabilities capabilities;
	
	@iOSXCUITFindBy(id = IOSElements.NAVIGATION_TITLE_ID)
	@AndroidFindBy(id = AndroidElements.TOOL_BAR_TITLE_ID)
	private MobileElement pageTitle;
	
	@iOSXCUITFindBy(accessibility = IOSElements.PRINT_EDITION_CALENDAR)
	@AndroidFindBy(id = AndroidElements.CALENDAR_BTN)
	private MobileElement calendar;
	
	@iOSXCUITFindBy(accessibility =  IOSElements.HAMBURGER_MENU_LOCATOR)
	@AndroidFindBy(accessibility =  AndroidElements.HAMBURGER_MENU_LOCATOR)
	private MobileElement hamburgerMenu;
	
	@iOSXCUITFindBy(accessibility =  IOSElements.TAB_TITLE_XPATH)
	@AndroidFindBy(id = AndroidElements.TAB_TITLE_ID)
	private List<MobileElement> tabInView;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ALERT_TITLE)
	@AndroidFindBy(id = AndroidElements.ALERT_TITLE_ID)
	private MobileElement alertTitle;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ALERT_MESSAGE)
	@AndroidFindBy(id = AndroidElements.ALERT_MSG_ID)
	private MobileElement alertMsg;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ALERT_IGNORE)
	@AndroidFindBy(id = AndroidElements.ALERT_IGNORE_ID)
	private MobileElement alertIgnore;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ALERT_ACCEPT)
	@AndroidFindBy(id = AndroidElements.ALERT_ACCEPT_ID)
	private MobileElement alertAccept;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_CLOSE)
	private MobileElement pushNotifyAlertClose;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_TITLE)
	private MobileElement pushNotifyAlertTitle;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_MSG)
	private MobileElement pushNotifyAlertMsg;

	public PrintEditionPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
	}
	
	public PrintEditionPage gotoPrintEditionPage() {
		methodName = "gotoPrintEditionPage";
		logger.info("Entering Method: " + methodName);
		try{
			GenericNavigator navigator = new GenericNavigator(driver);
			if(!navigator.preConfigured()) {
				navigator.completeBasicInstallConfig();
			}
			MenuPage menu = new MenuPage(driver);
			menu.clickOnMenu().gotoMenu(Constant.MENU.PRINT_EDITION);
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public Boolean verifyInstantPreDownloadAlert() {
		methodName = "verifyInstantPreDownloadAlert";
		logger.info("Entering Method: " + methodName);
		Boolean firstTimeVisitPopUpFound = false;
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(alertTitle.getText(), Constant.PRINT_EDITION_ALERT_TITLE,"Inconsistent Print Edition Alert Title");
		//Assert.assertEquals(alertMsg.getText(), Constant.PRINT_EDITION_ALERT_MESSAGE, "Inconsistent Print Edition Alert Message");
		Assert.assertEquals(alertIgnore.getText(), Constant.PRINT_EDITION_ALERT_IGNORE, "Inconsistent Print Edition Alert Ignore Button");
		Assert.assertEquals(alertAccept.getText(), Constant.PRINT_EDITION_ALERT_ACCEPT, "Inconsistent Print Edition Alert Accept Button");
		firstTimeVisitPopUpFound = true;
//		try {
//			Assert.assertEquals(alertTitle.getText(), Constant.PRINT_EDITION_ALERT_TITLE,"Inconsistent Print Edition Alert Title");
//			Assert.assertEquals(alertMsg.getText(), Constant.PRINT_EDITION_ALERT_MESSAGE, "Inconsistent Print Edition Alert Message");
//			Assert.assertEquals(alertIgnore.getText(), Constant.PRINT_EDITION_ALERT_IGNORE, "Inconsistent Print Edition Alert Ignore Button");
//			Assert.assertEquals(alertAccept.getText(), Constant.PRINT_EDITION_ALERT_ACCEPT, "Inconsistent Print Edition Alert Accept Button");
//			firstTimeVisitPopUpFound = true;
//		}
//		catch(Exception e) {
//			Log.INFO("No First time Print Edition PopUp Found. Exception thrown: " + e.getMessage());
//		}
		
		logger.info("Exiting Method: " + methodName);
		return firstTimeVisitPopUpFound;
	}
	
	public Boolean verifyPushNotifyPopUp() {
		methodName = "verifyPushNotifyPopUp";
		logger.info("Entering Method: " + methodName);
		Boolean pushNotifyPopUpFound = false;
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				Assert.assertEquals(pushNotifyAlertTitle.getText(), Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_TITLE,"Inconsistent Print Edition Push Notification Title");
				Assert.assertEquals(pushNotifyAlertMsg.getText(), Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_MSG, "Inconsistent Print Edition Push Notification Message");
				Assert.assertEquals(pushNotifyAlertClose.getText(), Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_CLOSE, "Inconsistent Push Notification Close Button");
				pushNotifyPopUpFound = true;
			}
		}
		catch(Exception e) {
			logger.warn("No Push Notification PopUp Found on Print Edition Page. Exception thrown: " + e.getMessage());
		}
		
		logger.info("Exiting Method: " + methodName);
		return pushNotifyPopUpFound;
	}
	
	public PrintEditionPage closePrintEditionInstantDownloadAlert() {
		methodName = "closePrintEditionInstantDownloadAlert";
		logger.info("Entering Method: " + methodName);
	
		alertAccept.click();
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public PrintEditionPage closePrintEditionPushNotificationAlert() {
		methodName = "closePrintEditionPushNotificationAlert";
		logger.info("Entering Method: " + methodName);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		pushNotifyAlertClose.click();
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public PrintEditionPage verifyDefaultView() {
		methodName = "verifyPageTitleContent";
		logger.info("Entering Method: " + methodName);
		
		List<String> expectedTabInView = new ArrayList<String>();
		expectedTabInView.add(Constant.TOP_OF_THE_NEWS_TAB_LABEL);
		expectedTabInView.add(Constant.WORLD_TAB_LABEL);
		expectedTabInView.add(Constant.OPINION_TAB_LABEL);
		expectedTabInView.add(Constant.HOME_TAB_LABEL);
		expectedTabInView.add(Constant.BUSINESS_TAB_LABEL);
		
		Iterator<String> expectedTabList = expectedTabInView.iterator();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertTrue(pageTitle.getAttribute("enabled").equals("true"), "Print Edition Page Title is unexpectedly disabled");
		Assert.assertTrue(calendar.getAttribute("enabled").equals("true"), "Calendar option to switch print edition to other dates is unexpectedly disabled");
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			Assert.assertEquals(pageTitle.getAttribute("text"), Constant.PRINT_EDITION_PAGE_TITLE, "Inconsistent name of Print Edition Page title");
			
			//Calendar icon is clickable
			Assert.assertTrue(calendar.getAttribute("clickable").equals("true"), "Calendar option to switch print edition to other dates is unexpectedly non-clickable");
		}
		
		String tabSelected = "true";
		for(MobileElement currentTab:tabInView) {
			if(currentTab.getAttribute("visible").equalsIgnoreCase("true")) {
				String currentTabLabel = currentTab.getText();
				String expectedTabLabel = expectedTabList.next();
				
				Assert.assertTrue(currentTab.getAttribute("enabled").equals("true"), "TAB: " + currentTabLabel + " is unexpectedly disabled");
				Assert.assertEquals(currentTab.getAttribute("selected"),tabSelected, "TAB: " + currentTabLabel + " is unexpectedly not selected in default view");
				Assert.assertTrue(currentTabLabel.equals(expectedTabLabel), "Tab title doesn't match for CURRENT TAB: " + currentTabLabel + ", whereas EXPECTED TAB: " + expectedTabLabel);
				tabSelected = "false";
			}
		}
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}
}
