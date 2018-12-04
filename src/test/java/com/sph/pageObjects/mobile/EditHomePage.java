package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;
import com.sph.driverFactory.DriverManager;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.GenericNavigator;
import com.sph.utilities.IOSElements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EditHomePage{
	
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(AccountPage.class);
	private WebDriver driver;
    WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;
    private SettingsPage settings;
	
	private static Integer expectedLabelXAxis = 0;
	private static Integer expectedLabelHeight = 0;
	private static Integer expectedLabelWidth = 0;
	
	private static Integer expectedSelectorXAxis = 0;
	private static Integer expectedSelectorHeight = 0;
	private static Integer expectedSelectorWidth = 0;
	
	private static Map<String, Boolean> sectionsOnHomePage = null;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"" + IOSElements.CUSTOM_SECTIONS_TABLE_VIEW + "\"]/XCUIElementTypeButton")
	private MobileElement backButton;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"" + IOSElements.CUSTOM_SECTIONS_TABLE_VIEW + "\"]")
	private MobileElement navigationBar;
	
	@iOSXCUITFindBy(id = IOSElements.NAVIGATION_TITLE_ID)
	private MobileElement pageTitle;
	
//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + sectionTitle + "\"]/following-sibling::XCUIElementTypeButton")
//	private MobileElement sectionSelector;
	
	public EditHomePage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        capabilities = ((RemoteWebDriver) driver).getCapabilities();
        util = new DeviceActions(this.driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
		sectionsOnHomePage = new LinkedHashMap<String,Boolean>();
		sectionsOnHomePage.put("ST Now", true);
		sectionsOnHomePage.put("Home", true);
		sectionsOnHomePage.put("Latest", true);
		sectionsOnHomePage.put("Singapore", false);
		sectionsOnHomePage.put("Politics", false);
		sectionsOnHomePage.put("Asia", false);
		sectionsOnHomePage.put("World", false);
		sectionsOnHomePage.put("Lifestyle", false);
		sectionsOnHomePage.put("Food", false);
		sectionsOnHomePage.put("Forum", false);
		sectionsOnHomePage.put("Videos", true);
		sectionsOnHomePage.put("Opinion", false);
		sectionsOnHomePage.put("Business", false);
		sectionsOnHomePage.put("Sport", false);
		sectionsOnHomePage.put("Tech", false);
	}
	
	public SettingsPage gotoPreviousView() {
		methodName = "gotoEditHomePage";
		log.info("Entering Method: " + methodName);
		
		//Click on Back button
		util.clickifClickable(backButton, Constant.SHORT_TIMEOUT);		
		
		try{
			return new SettingsPage(driver);
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
	}
	
	public EditHomePage gotoEditHomePage() {
		methodName = "gotoEditHomePage";
		log.info("Entering Method: " + methodName);
		try {
			GenericNavigator navigator = new GenericNavigator(driver);
			
			if(!navigator.preConfigured()) {
				navigator.completeBasicInstallConfig();
			}
			
			DriverManager.setWebDriver(driver);
//			
//			try{
//				SettingsPage settings = new SettingsPage(driver);
//			}catch(Exception e) {
//				log.error("Exception raised: " + e);
//				driver.quit();
//			}
			
			settings.gotoSettingsMenu().gotoSettings(Constant.SETTINGS_MENU.EDIT_HOME);
		
			log.info("Exiting Method: " + methodName);
		}catch(Exception ex) {
			log.error("Unable to goto Edit Home Page due to exception: " + ex.getMessage());
		}
		return this;
	}
	
	public EditHomePage validatePageTitle() {
		methodName = "validatePageTitle";
		log.info("Entering Method: " + methodName);
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			Assert.assertEquals(navigationBar.getAttribute("type"), "XCUIElementTypeNavigationBar","Inconsistent Type of Navigation Bar");
			Assert.assertEquals(navigationBar.getAttribute("name"), IOSElements.CUSTOM_SECTIONS_TABLE_VIEW, "Inconsistent name of Navigation Bar");
			Assert.assertTrue(navigationBar.isEnabled(), "Navigation Bar is unexpectedly disabled");
			Assert.assertTrue(navigationBar.isDisplayed(), "Navigation Bar is unexpectedly invisible");
			
			Assert.assertEquals(pageTitle.getAttribute("type"), "XCUIElementTypeStaticText","Inconsistent Type of Edit Home title element");
			Assert.assertEquals(pageTitle.getAttribute("value"), IOSElements.EDIT_HOME_PAGE_TITLE, "Inconsistent value of title element");
			Assert.assertEquals(pageTitle.getAttribute("label"), IOSElements.EDIT_HOME_PAGE_TITLE, "Inconsistent label of title element");
			Assert.assertTrue(pageTitle.isEnabled(), "Title is unexpectedly disabled");
			Assert.assertTrue(pageTitle.isDisplayed(), "Title is unexpectedly invisible");
			
			Assert.assertEquals(backButton.getAttribute("type"), "XCUIElementTypeButton","Inconsistent type of Button to navigate to previous view");
			Assert.assertTrue(backButton.isEnabled(), "Button to navigate to previous view is unexpectedly disabled");
			Assert.assertTrue(backButton.isDisplayed(), "Button to navigate to previous view is unexpectedly invisible");
			
		}
		
		log.info("Exiting Method: " + methodName);
		return this;
	}
	
	public EditHomePage validateDefaultEditHomeView() {
		methodName = "validateDefaultEditHomeView";
		log.info("Entering Method: " + methodName);
		boolean resetDimension = true;
		MobileElement sectionSelector = null;
		
		for(String sectionTitle:sectionsOnHomePage.keySet()) {
			log.info("Validating alignment of Section : " + sectionTitle);
			
			MobileElement sectionLabel = (MobileElement) driver.findElement(By.id(sectionTitle));
			
			//For last sections in Edit Home View, the button and labels are arranged differently unless in view. Hence, try scrolling up and get it in view for validating
			try {
				sectionSelector = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + sectionTitle + "\"]/following-sibling::XCUIElementTypeButton"));
			}catch(Exception NoSuchElementException) {
				DeviceActions util = new DeviceActions(driver);
				util.swipeVertical("Up");
				sectionSelector = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + sectionTitle + "\"]/following-sibling::XCUIElementTypeButton"));
			}
			
			Assert.assertEquals(sectionLabel.getAttribute("label"), sectionTitle, "Displayed Section label is incorrect");
			
			this.isLabelAligned(resetDimension, sectionLabel).isSectionSelectorAligned(resetDimension, sectionSelector).isSectionSelectionIntact(sectionSelector, sectionTitle);
		
			resetDimension = false;
			log.info("Successfully validated alignment for Section : " + sectionTitle);
		}
		log.info("Exiting Method: " + methodName);
		return this;
	}
	
	public EditHomePage isSectionSelectionIntact(MobileElement sectionSelector, String sectionTitle) {
		methodName = "isDefaultSectionSelectionIntact";
		log.info("Entering Method: " + methodName);
		
		if(sectionsOnHomePage.get(sectionTitle)) {
			//If section is by default expected to be added to home page
			Assert.assertEquals(sectionSelector.getAttribute("name"), "selection customize selected", "Failure: Section " + sectionTitle + " is unexpectedly failed to be added to home page by default");
			Assert.assertEquals(sectionSelector.getAttribute("enabled"), "false", "Failure: Default Section " + sectionTitle + " is unexpectedly enabled for update");
		}
		else {
			//If section is not expected to be added to home page by default
			Assert.assertEquals(sectionSelector.getAttribute("name"), "selection customize", "Failure: Section " + sectionTitle + " is unexpectedly added to home page by default");
			Assert.assertEquals(sectionSelector.getAttribute("enabled"), "true", "Failure: Default Section " + sectionTitle + " is unexpectedly disabled for update");
		}
		log.info("Exiting Method: " + methodName);
		return this;
	}
	
	public EditHomePage isLabelAligned(boolean resetDimension, MobileElement sectionLabel) {
		methodName = "isLabelAligned";
		log.info("Entering Method: " + methodName);
		Integer labelXAxis;
		Integer labelWidth;
		Integer labelHeight;
		
		if(resetDimension) {
			expectedLabelXAxis = sectionLabel.getLocation().getX();
			expectedLabelWidth = sectionLabel.getSize().getWidth();
			expectedLabelHeight = sectionLabel.getSize().getHeight();
		}
		else {
			labelXAxis = sectionLabel.getLocation().getX();
			labelWidth = sectionLabel.getSize().getWidth();
			labelHeight = sectionLabel.getSize().getHeight();
			
			Assert.assertEquals(labelXAxis, expectedLabelXAxis, "Section label is misaligned across x-axis");
			Assert.assertEquals(labelWidth, expectedLabelWidth, "Section label has inconsistent width");
			Assert.assertEquals(labelHeight, expectedLabelHeight, "Section label has inconsistent height");
		}
		log.info("Exiting Method: " + methodName);
		return this;
	}
	
	public EditHomePage isSectionSelectorAligned(boolean resetDimension, MobileElement sectionSelector) {
		methodName = "isSectionSelectorAligned";
		log.info("Entering Method: " + methodName);
		Integer selectorXAxis;
		Integer selectorWidth;
		Integer selectorHeight;
		
		if(resetDimension) {
			expectedSelectorXAxis = sectionSelector.getLocation().getX();
			expectedSelectorWidth = sectionSelector.getSize().getWidth();
			expectedSelectorHeight = sectionSelector.getSize().getHeight();
		}
		else {
			selectorXAxis = sectionSelector.getLocation().getX();
			selectorWidth = sectionSelector.getSize().getWidth();
			selectorHeight = sectionSelector.getSize().getHeight();
			
			Assert.assertEquals(selectorXAxis, expectedSelectorXAxis, "Section selector is misaligned across x-axis");
			Assert.assertEquals(selectorWidth, expectedSelectorWidth, "Section selector has inconsistent width");
			Assert.assertEquals(selectorHeight, expectedSelectorHeight, "Section selector has inconsistent height");
		}
		log.info("Exiting Method: " + methodName);
		return this;
	}
	
	public EditHomePage addSectionToHome(String sectionTitle) {
		methodName = "addSectionToHome";
		log.info("Entering Method: " + methodName);
		MobileElement sectionSelector = null;
		
		try {
			sectionSelector = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + sectionTitle + "\"]/following-sibling::XCUIElementTypeButton"));
		}catch(Exception NoSuchElementException) {
			DeviceActions util = new DeviceActions(driver);
			util.swipeVertical("Up");
			sectionSelector = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + sectionTitle + "\"]/following-sibling::XCUIElementTypeButton"));
		}
		if(!sectionSelector.getAttribute("name").contains("selected")) {
			sectionSelector.click();
		}else {
			log.info("Sub Section already added to home page");
		}
		log.info("Exiting Method: " + methodName);
		return this;
	}
	
	public EditHomePage removeSectionFromHome(String sectionTitle) {
		methodName = "removeSectionFromHome";
		log.info("Entering Method: " + methodName);
		MobileElement sectionSelector = null;
		
		try {
			sectionSelector = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + sectionTitle + "\"]/following-sibling::XCUIElementTypeButton"));
		}catch(Exception NoSuchElementException) {
			DeviceActions util = new DeviceActions(driver);
			util.swipeVertical("Up");
			sectionSelector = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + sectionTitle + "\"]/following-sibling::XCUIElementTypeButton"));
		}
		if(sectionSelector.getAttribute("name").contains("selected")) {
			sectionSelector.click();
		}else {
			log.info("Sub Section already removed from home page");
		}
		log.info("Exiting Method: " + methodName);
		return this;
	}
	
}
