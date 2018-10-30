package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.AndroidElements;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.GenericNavigator;
import com.sph.utilities.IOSElements;

import io.appium.java_client.MobileDriver;
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
    private DeviceActions util;
    
    private Map<String, HashMap<String, Integer>> expectedDim = new HashMap<String, HashMap<String,Integer>>();
    
//    private HashMap<String, Integer> expectedArticleDim = new HashMap<String,Integer>();
//    
//    private HashMap<String, Integer> expectedTimeDim = new HashMap<String, Integer>();
//    
//    private HashMap<String, Integer> expectedArticleTitleDim = new HashMap<String, Integer>();
	
	@iOSXCUITFindBy(id = IOSElements.NAVIGATION_TITLE_ID)
	@AndroidFindBy(id = AndroidElements.TOOL_BAR_TITLE_ID)
	private MobileElement pageTitle;
	
	@iOSXCUITFindBy(accessibility = IOSElements.PRINT_EDITION_CALENDAR)
	@AndroidFindBy(id = AndroidElements.CALENDAR_BTN)
	private MobileElement calendar;
	
	@iOSXCUITFindBy(accessibility =  IOSElements.HAMBURGER_MENU_LOCATOR)
	@AndroidFindBy(accessibility =  AndroidElements.HAMBURGER_MENU_LOCATOR)
	private MobileElement hamburgerMenu;
	
	@iOSXCUITFindBy(xpath =  IOSElements.TAB_TITLE_XPATH)
	@AndroidFindBy(id = AndroidElements.TAB_TITLE_ID)
	private List<MobileElement> tabInView;
	
	//TODO: Add id in iOS
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_INSTANT_DOWNLOAD_TITLE)
	@AndroidFindBy(id = AndroidElements.ALERT_TITLE_ID)
	private MobileElement alertTitle;
	
	//TODO: Add id in iOS
	//@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_INSTANT_DOWNLOAD_MESSAGE)
	@iOSXCUITFindBy(xpath="//XCUIElementTypeStaticText[@name=\"" + Constant.PRINT_EDITION_INSTANT_DOWNLOAD_TITLE + "\"]//following-sibling::XCUIElementTypeStaticText")
	@AndroidFindBy(id = AndroidElements.ALERT_MSG_ID)
	private MobileElement alertMsg;
	
	@iOSXCUITFindBy(accessibility = IOSElements.ALERT_IGNORE_ID)
	@AndroidFindBy(id = AndroidElements.ALERT_IGNORE_ID)
	private MobileElement alertIgnore;
	
	@iOSXCUITFindBy(accessibility = IOSElements.ALERT_ACCEPT_ID)
	@AndroidFindBy(id = AndroidElements.ALERT_ACCEPT_ID)
	private MobileElement alertAccept;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_CLOSE)
	private MobileElement pushNotifyAlertClose;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_TITLE)
	private MobileElement pushNotifyAlertTitle;
	
	@iOSXCUITFindBy(accessibility = Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_MSG)
	private MobileElement pushNotifyAlertMsg;
	
	@iOSXCUITFindBy(xpath =  IOSElements.PRINT_EDITION_ARTICLES_INVIEW_XPATH)
	@AndroidFindBy(id = AndroidElements.TAB_TITLE_ID)
	private List<MobileElement> articlesInView;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText")
	@AndroidFindBy(id = AndroidElements.TAB_TITLE_ID)
	private List<MobileElement> weekdaysDisplayedInCalendar;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[2]")
	private MobileElement activeTabPointer;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[not(@name)]")
	private List<MobileElement> articleImagesInView;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"article_title\"]")
	private List<MobileElement> articleTitlesInView;

	public PrintEditionPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        util = new DeviceActions(driver);
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
		
		String alertMsgReceived = alertMsg.getText().trim().replaceAll("( )+", " ");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(alertTitle.getText(), Constant.PRINT_EDITION_INSTANT_DOWNLOAD_TITLE,"Inconsistent Print Edition Alert Title");
		Assert.assertEquals(alertMsgReceived, Constant.PRINT_EDITION_INSTANT_DOWNLOAD_MESSAGE, "Inconsistent Print Edition Alert Message");
		Assert.assertEquals(alertIgnore.getText(), Constant.PRINT_EDITION_INSTANT_DOWNLOAD_IGNORE, "Inconsistent Print Edition Alert Ignore Button");
		Assert.assertEquals(alertAccept.getText(), Constant.PRINT_EDITION_INSTANT_DOWNLOAD_ACCEPT, "Inconsistent Print Edition Alert Accept Button");
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
				Assert.assertEquals(pushNotifyAlertMsg.getText().trim(), Constant.PRINT_EDITION_ENABLE_PUSH_NOTIFICATION_ALERT_MSG.trim(), "Inconsistent Print Edition Push Notification Message");
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
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			alertAccept.click();
		}
		
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
		methodName = "verifyDefaultView";
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
		
		for(MobileElement currentTab:tabInView) {
			if(currentTab.getAttribute("visible").equalsIgnoreCase("true")) {
				String currentTabLabel = currentTab.getText();
				String expectedTabLabel = expectedTabList.next();
				
				Assert.assertTrue(currentTab.getAttribute("enabled").equals("true"), "TAB: " + currentTabLabel + " is unexpectedly disabled");
				Assert.assertTrue(currentTabLabel.equals(expectedTabLabel), "Tab title doesn't match for CURRENT TAB: " + currentTabLabel + ", whereas EXPECTED TAB: " + expectedTabLabel);
			}
		}
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}

	public PrintEditionPage verifyTabLayout(String sectionName) {
		//TODO: Section Name not yet used
		
		List<MobileElement> tabsValidated = new ArrayList<MobileElement>();
		for(MobileElement currentTab:tabInView) {
			while(currentTab.getAttribute("visible").equalsIgnoreCase("false")) {
				util = new DeviceActions(driver);
				util.swipeHorizontal("left");
			}
			currentTab.click();
			//Track and Verify image dimension
			validateArticlesInView();
			
			
			tabsValidated.add(currentTab);
		}
		
		
		//Track first and Verify article caption
		
		//Ensure timing elements are shown for every article
		
		return this;
	}
	
	public PrintEditionPage validateArticlesInView() {
		List<String> articleTitles = new ArrayList<String>();
		HashMap<String, Integer> actualImageDim = new HashMap<String, Integer>();
		String lastArticleValidated = "";
		String currentArticleTitle;
		int articleInViewCount = 0;
		while(true) {
			int count = 0;
			for(MobileElement currentArticle:articlesInView) {
				currentArticleTitle = articleTitlesInView.get(count).getAttribute("label");
				System.out.println("Starting validation of: " + currentArticleTitle);
				if(articleTitles.contains(currentArticleTitle)) {
					continue;
				}
				
				//Validate Article Cell Dimension
				
				//Validate Article Image Dimension
				actualImageDim.put("xStart", articleTitlesInView.get(count).getLocation().getX());
				actualImageDim.put("yStart", articleTitlesInView.get(count).getLocation().getY());
				actualImageDim.put("width", articleTitlesInView.get(count).getSize().width);
				actualImageDim.put("height", articleTitlesInView.get(count).getSize().height);
				
				if(!expectedDim.containsKey("imageDim")) {
					expectedDim.put("imageDim", actualImageDim);
				}
				else {	
					validateElementDimension(actualImageDim, expectedDim.get("imageDim"));
//					Assert.assertEquals(xStart, expectedArticleImageDim.get("xStart"), "X-Axis Start of article image is inconsistent");
//					Assert.assertEquals(yStart, expectedArticleImageDim.get("yStart"), "Y-Axis Start of article image is inconsistent");
//					Assert.assertEquals(width, expectedArticleImageDim.get("width"), "Width of article image is inconsistent");
//					Assert.assertEquals(height, expectedArticleImageDim.get("height"), "Height of article image is inconsistent");
				}
				
				//Validate Article Title Dimension
				System.out.println("Validated: " + currentArticleTitle);
				//Validate Article Time Dimension
				
				articleTitles.add(currentArticleTitle);
				count = count + 1;
				lastArticleValidated = currentArticleTitle;
			}
			
			//util = new DeviceActions(driver);
			util.swipeVertical("Up");
			articleInViewCount = articlesInView.size();
			String lastArticleTitleInView = articleTitlesInView.get(articleInViewCount - 1).getAttribute("label");
			if(lastArticleTitleInView.equals(lastArticleValidated)) {
				break;
			}
			
		}
		return this;
	}
	
	public Boolean validateElementDimension(HashMap<String,Integer> actualDim, HashMap<String,Integer> expectedDim) {
		methodName = "validateAlignment";
		logger.info("Entering Method: " + methodName);
		Boolean validated = false;
					
		Assert.assertEquals(actualDim.get("xStart"),expectedDim.get("xStart"), "X-Axis Start of article image is inconsistent");
		Assert.assertEquals(actualDim.get("yStart"),expectedDim.get("yStart"), "Y-Axis Start of article image is inconsistent");
		Assert.assertEquals(actualDim.get("width"),expectedDim.get("width"), "Width of article image is inconsistent");
		Assert.assertEquals(actualDim.get("height"),expectedDim.get("height"), "Height of article image is inconsistent");
		validated = true;
		logger.info("Exiting Method: " + methodName);
		return validated;
	}
	
	public PrintEditionPage openCalendarView() {
		methodName = "openCalendarView";
		logger.info("Entering Method: " + methodName);
		
		calendar.click();
		
		logger.info("Exiting Method: " + methodName);
		return this;
	}

	
}
