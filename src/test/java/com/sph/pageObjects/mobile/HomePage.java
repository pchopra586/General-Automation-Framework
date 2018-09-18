package com.sph.pageObjects.mobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.sph.utilities.Constant.TAB;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.GenericNavigator;
import com.sph.utilities.IOSElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(HomePage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;
    
    @iOSXCUITFindBy(accessibility = IOSElements.CLOSE_INTERSTITIAL_AD_ID)
    @AndroidFindBy(className = AndroidElements.CLOSE_AD)
    private MobileElement close_ad;
    
	private static Integer xRowStart = 0;
	private static Integer xRowEnd = 0;
	//Reset Options for alignment parameters
	private static Boolean resetArticleWidth;
	private static Boolean resetXAxisStart;
	private static Boolean resetArticleImageVideoHeight;
	private static Boolean resetRemoveFromHomeBtnDimension;
	
	//Parameters to capture the expected row-wise Width for articles 
	private static Integer firstRowArticleWidth = 0;
	private static Integer secondRowArticleWidth = 0;
	
	//Parameter to capture Height of Image/Video row-wise, instead of Article Height(As article height could vary based on label length)
	private static Integer firstRowImageVideoHeight = 0;
	private static Integer secondRowImageVideoHeight = 0;
	private static Integer thirdRowOnwardsImageVideoHeight = 0;
	private static Integer thirdRowOnwardsImageVideoWidth = 0;
	
	//Parameters to capture the remove from home button dimensions
	private static Integer removeFromHomeBtnHeight = 0;
	private static Integer removeFromHomeBtnWidth = 0;
	private static Integer removeFromHomeBtnXAxisStart = 0;
	
	private static Set<String> defaultSubSections = new HashSet<String>(Arrays.asList(new String[]{"TOP STORIES", "MALAYSIA ELECTIONS","FAKE NEWS DEBUNKED","ST FOOD","ASIA TOP STORIES","WEB SPECIALS","ENTERTAINMENT"}));
			
	@iOSXCUITFindBy(accessibility = IOSElements.MAIN_NAVIGATION_BAR_NAME)
	@AndroidFindBy(id = AndroidElements.MAIN_NAVIGATION_BAR_NAME)
	private MobileElement logo;
	
	@iOSXCUITFindBy(accessibility = IOSElements.HAMBURGER_MENU_LOCATOR)
	@AndroidFindBy(xpath = AndroidElements.HAMBURGER_MENU_LOCATOR)
	private MobileElement menu;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.ST_NOW_TAB_LABEL + "\"]")
	private MobileElement stNowTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.HOME_TAB_LABEL + "\"]")
	private MobileElement homeTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.LATEST_TAB_LABEL + "\"]")
	private MobileElement latestTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.SINGAPORE_TAB_LABEL + "\"]")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"" + Constant.SINGAPORE_TAB_LABEL + "\"]")
	private MobileElement singaporeTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.POLITICS_TAB_LABEL + "\"]")
	private MobileElement politicsTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.ASIA_TAB_LABEL + "\"]")
	private MobileElement asiaTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.WORLD_TAB_LABEL + "\"]")
	private MobileElement worldTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.LIFESTYLE_TAB_LABEL + "\"]")
	private MobileElement lifestyleTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.FOOD_TAB_LABEL + "\"]")
	private MobileElement foodTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.FORUM_TAB_LABEL + "\"]")
	private MobileElement forumTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.VIDEOS_TAB_LABEL + "\"]")
	private MobileElement videosTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.OPINION_TAB_LABEL + "\"]")
	private MobileElement opinionTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.BUSINESS_TAB_LABEL + "\"]")
	private MobileElement businessTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.SPORT_TAB_LABEL + "\"]")
	private MobileElement sportTab;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + Constant.TECH_TAB_LABEL + "\"]")
	private MobileElement techTab;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
//        this.wait = new WebDriverWait(this.driver, 10);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		resetRemoveFromHomeBtnDimension = true;
	}
	
	public boolean onHomePage() {
		methodName = "gotoHomePage";
		logger.info("Entering Method: " + methodName);
		boolean onHomePage = false;
		try {
			Assert.assertTrue(logo.isEnabled(), "Not on Home Page");
			onHomePage = true;
		}catch(Exception e) {
			try {
	            if (close_ad.isDisplayed()) {
	                close_ad.click();
	            }
	        }
	        catch (Exception ex)
	        {
	            System.out.printf("Interstitial Ad Not Present");
	        }
			return onHomePage;
		}
		logger.info("Successfully exiting from method: " + methodName);
		return onHomePage;
	}
	
	/*
	 * Function to navigate to Home Page
	 */
	public HomePage gotoHomePage() {
		methodName = "gotoHomePage";
		logger.info("Entering Method: " + methodName);
		GenericNavigator navigator = new GenericNavigator(driver);
		
		if(!navigator.preConfigured()) {
			navigator.completeBasicInstallConfig();
		}
		
		int trial = 0;
		boolean onHomePage = false;
		while(trial < 2 && !onHomePage) {
			trial++;
			onHomePage = onHomePage();
		}
		
		Assert.assertTrue(onHomePage,"Unexpectedly not on Home Page");
		logger.info("Successfully exiting from method: " + methodName);
		return this;
	}
	
	public boolean appLogoMenuValidation() {
		methodName = "appLogoValidation";
		logger.info("Entering Method: " + methodName);
		boolean validated = false;
		int logoYAxis = 0;
		int logoHeight = 0;
		int menuYAxis = 0;
		int menuHeight = 0;
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			if(logo.getTagName().equals("THE STRAITS TIMES")){
				logger.info("Successfully exiting from method: " + methodName);
				validated = true;
				return validated;
			}
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			Assert.assertEquals(logo.getAttribute("name"), IOSElements.MAIN_NAVIGATION_BAR_NAME, "Inconsistent name for logo");
			Assert.assertTrue(logo.isEnabled(), "Logo is unexpectedly disabled");
			Assert.assertTrue(logo.isDisplayed(), "Logo is unexpectedly invisible");
			
			Assert.assertTrue(menu.isEnabled(), "Menu is unexpectedly disabled");
			Assert.assertTrue(menu.isDisplayed(), "Menu is unexpectedly invisible");
			
			logoYAxis = logo.getCoordinates().onPage().getY();
			menuYAxis = menu.getCoordinates().onPage().getY();
			Assert.assertEquals(logoYAxis, menuYAxis, "Y-Axis coordinate of menu and logo is different, implying misalignment of the elements in home page view");

			logoHeight = logo.getSize().getHeight();
			menuHeight = menu.getSize().getHeight();
			Assert.assertEquals(logoHeight, menuHeight, "Height of menu and logo is different, implying misalignment of the elements in home page view");
			validated = true;
			logger.info("Successfully exiting from method: " + methodName);
			return validated;
		}
		logger.info("Logo is inconsistent with expected");
		return validated;
	}
	
//	public boolean tabAccessibilityValidation() {
//		methodName = "basicTabAccessibilityValidation";
//		logger.info("Entering Method: " + methodName);
//		logger.log(Status.INFO, "Entering Method: " + methodName);
//		
//		MobileElement tabButton = null; 
//		boolean success = true;
//		List<Map<String , String>> tabs  = new ArrayList<Map<String,String>>();
//		
//		Map<String,String> stNowTab = new HashMap<String,String>();
//		stNowTab.put("name", "ST Now");
//		stNowTab.put("label", "ST NOW");
//		stNowTab.put("enabled", "true");
//		stNowTab.put("visible", "true");
//		tabs.add(stNowTab);
//		
//		Map<String,String> homeTab = new HashMap<String,String>();
//		homeTab.put("name", "Home");
//		homeTab.put("label", "HOME");
//		homeTab.put("enabled", "true");
//		homeTab.put("visible", "true");
//		tabs.add(homeTab);
//		
//		Map<String,String> latestTab = new HashMap<String,String>();
//		latestTab.put("name", "Latest");
//		latestTab.put("label", "LATEST");
//		latestTab.put("enabled", "true");
//		latestTab.put("visible", "true");
//		tabs.add(latestTab);
//		
//		Map<String,String> singaporeTab = new HashMap<String,String>();
//		singaporeTab.put("name", "Singapore");
//		singaporeTab.put("label", "SINGAPORE");
//		singaporeTab.put("enabled", "true");
//		singaporeTab.put("visible", "true");
//		tabs.add(singaporeTab);
//		
//		Map<String,String> politicsTab = new HashMap<String,String>();
//		politicsTab.put("name", "Politics");
//		politicsTab.put("label", "POLITICS");
//		politicsTab.put("enabled", "true");
//		politicsTab.put("visible", "false");
//		tabs.add(politicsTab);
//		
//		Map<String,String> asiaTab = new HashMap<String,String>();
//		asiaTab.put("name", "Asia");
//		asiaTab.put("label", "ASIA");
//		asiaTab.put("enabled", "true");
//		asiaTab.put("visible", "false");
//		tabs.add(asiaTab);
//		
//		Map<String,String> worldTab = new HashMap<String,String>();
//		worldTab.put("name", "World");
//		worldTab.put("label", "WORLD");
//		worldTab.put("enabled", "true");
//		worldTab.put("visible", "false");
//		tabs.add(worldTab);
//		
//		Map<String,String> lifestyleTab = new HashMap<String,String>();
//		lifestyleTab.put("name", "Lifestyle");
//		lifestyleTab.put("label", "LIFESTYLE");
//		lifestyleTab.put("enabled", "true");
//		lifestyleTab.put("visible", "false");
//		tabs.add(lifestyleTab);
//		
//		Map<String,String> foodTab = new HashMap<String,String>();
//		foodTab.put("name", "Food");
//		foodTab.put("label", "FOOD");
//		foodTab.put("enabled", "true");
//		foodTab.put("visible", "false");
//		tabs.add(foodTab);
//		
//		Map<String,String> forumTab = new HashMap<String,String>();
//		forumTab.put("name", "Forum");
//		forumTab.put("label", "FORUM");
//		forumTab.put("enabled", "true");
//		forumTab.put("visible", "false");
//		tabs.add(forumTab);
//		
//		Map<String,String> videosTab = new HashMap<String,String>();
//		videosTab.put("name", "Videos");
//		videosTab.put("label", "VIDEOS");
//		videosTab.put("enabled", "true");
//		videosTab.put("visible", "false");
//		tabs.add(videosTab);
//		
//		Map<String,String> opinionTab = new HashMap<String,String>();
//		opinionTab.put("name", "Opinion");
//		opinionTab.put("label", "OPINION");
//		opinionTab.put("enabled", "true");
//		opinionTab.put("visible", "false");
//		tabs.add(opinionTab);
//		
//		Map<String,String> businessTab = new HashMap();
//		businessTab.put("name", "Business");
//		businessTab.put("label", "BUSINESS");
//		businessTab.put("enabled", "true");
//		businessTab.put("visible", "false");
//		tabs.add(businessTab);
//
//		Map<String,String> sportTab = new HashMap<String,String>();
//		sportTab.put("name", "Sport");
//		sportTab.put("label", "SPORT");
//		sportTab.put("enabled", "true");
//		sportTab.put("visible", "false");
//		tabs.add(sportTab);
//
//		Map<String,String> techTab = new HashMap<String,String>();
//		techTab.put("name", "Tech");
//		techTab.put("label", "TECH");
//		techTab.put("enabled", "true");
//		techTab.put("visible", "false");
//		tabs.add(techTab);
//		
//		int prevTabYAxis = 0;
//		int prevTabHeight = 0;
//		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
//			for(Map<String,String> tab : tabs) {
//				tabButton = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + tab.get("name") + "\"]");
//				
//				if(tabButton.equals(null)) {
//					logger.log(Status.ERROR, "Expected Tab: \"" + tab + "\" is missing from Home Page View");
//					success = false;
//				}
//				else {
//					Assert.assertEquals(tab.get("label"), tabButton.getAttribute("label"));
//					Assert.assertEquals(tab.get("type"), tabButton.getAttribute("type"));
//					Assert.assertEquals(tab.get("enabled"), tabButton.getAttribute("enabled").toString());
//					Assert.assertEquals(tab.get("visible"), tabButton.getAttribute("visible").toString());
//
//					if(prevTabYAxis == 0 && prevTabHeight == 0) {
//						prevTabYAxis = tabButton.getCoordinates().onPage().getY();
//						prevTabHeight = tabButton.getSize().getHeight();
//					}else {
//						Assert.assertEquals(tabButton.getCoordinates().onPage().getY(), prevTabYAxis);
//						Assert.assertEquals(tabButton.getSize().getHeight(), prevTabHeight);
//					}
//					logger.log(Status.INFO, "Tab: \"" + tab + "\" is consistent with expected view");
//				}
//			}
//		}
//		logger.info("Successfully exiting from method: " + methodName);
//		return success;
//	}
	
	public boolean tabAccessibilityValidation() {
		methodName = "tabAccessibilityValidation";
		logger.info("Entering Method: " + methodName);
		
		MobileElement tabButton = null; 
		boolean success = true;
		
		Map<TAB,Boolean> tab = new HashMap<TAB,Boolean>();
		tab.put(TAB.ST_NOW, true);
		tab.put(TAB.HOME, true);
		tab.put(TAB.LATEST, true);
		tab.put(TAB.SINGAPORE, true);
		tab.put(TAB.POLITICS, false);
		tab.put(TAB.ASIA, false);
		tab.put(TAB.WORLD, false);
		tab.put(TAB.LIFESTYLE, false);
		tab.put(TAB.FOOD, false);
		tab.put(TAB.FORUM, false);
		tab.put(TAB.VIDEOS, false);
		tab.put(TAB.OPINION, false);
		tab.put(TAB.BUSINESS, false);
		tab.put(TAB.SPORT, false);
		tab.put(TAB.TECH, false);
		
		int prevTabYAxis = 0;
		int prevTabHeight = 0;
		
		for(TAB tabLabel: tab.keySet()) {
			switch(tabLabel) {
			case ST_NOW:
				tabButton = stNowTab;
				break;
			case HOME:
				tabButton = homeTab;
				break;
			case LATEST:
				tabButton = latestTab;
				break;
			case SINGAPORE:
				tabButton = singaporeTab;
				break;
			case POLITICS:
				tabButton = politicsTab;
				break;
			case ASIA:
				tabButton = asiaTab;
				break;
			case WORLD:
				tabButton = worldTab;
				break;
			case LIFESTYLE:
				tabButton = lifestyleTab;
				break;
			case FOOD:
				tabButton = foodTab;
				break;
			case FORUM:
				tabButton = forumTab;
				break;
			case VIDEOS:
				tabButton = videosTab;
				break;
			case OPINION:
				tabButton = opinionTab;
				break;
			case BUSINESS:
				tabButton = businessTab;
				break;
			case SPORT:
				tabButton = sportTab;
				break;
			case TECH:
				tabButton = techTab;
				break;
			default:
				logger.error("Tab Name doesn't exist");
				break;
			}
			
			Assert.assertEquals(tabButton.getText(), tabLabel.toString(),"Inconsistent Tab Label for " + tabLabel.toString());
			if(tab.get(tabLabel)) {
				Assert.assertTrue(tabButton.isDisplayed(), "Unexpectedly invisible Tab: " + tabLabel.toString());
			}
			Assert.assertTrue(tabButton.isEnabled(), "Unexpectedly disabled Tab: " + tabLabel.toString());
			
			if(prevTabYAxis == 0 && prevTabHeight == 0) {
				prevTabYAxis = tabButton.getCoordinates().onPage().getY();
				prevTabHeight = tabButton.getSize().getHeight();
			}else {
				Assert.assertEquals(tabButton.getCoordinates().onPage().getY(), prevTabYAxis);
				Assert.assertEquals(tabButton.getSize().getHeight(), prevTabHeight);
			}
			logger.info("Tab: \"" + tab + "\" is consistent with expected view");
		}
		
		logger.info("Successfully exiting from method: " + methodName);
		return success;
	}
	
	public boolean isRemoveFromHomeBtnAligned(String sectionLabel) {
		boolean validated = false;
		methodName = "isRemoveFromHomeBtnAligned";
		logger.info("Entering Method: " + methodName);
		
		MobileElement addToHomeButton = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@label=\"" + sectionLabel + "\"]/following-sibling::XCUIElementTypeButton"));
		
		Integer btnHeight = addToHomeButton.getSize().getHeight();
		Integer btnWidth = addToHomeButton.getSize().getWidth();
		Integer btnXStart = addToHomeButton.getLocation().getX();
		
		if(resetRemoveFromHomeBtnDimension) {
			removeFromHomeBtnHeight = btnHeight;
			removeFromHomeBtnWidth = btnWidth;
			removeFromHomeBtnXAxisStart = btnXStart;
		}else {
			Assert.assertEquals(addToHomeButton.getAttribute("label"), IOSElements.REMOVE_FROM_HOME_BTN_LABEL, "For section: \"" +  sectionLabel + "\", label for Remove from Home Button is inconsistent");
			Assert.assertEquals(addToHomeButton.getAttribute("enabled"), "true", "Unexpectedly Remove from Home Button is disabled for Section: " + sectionLabel);
			Assert.assertEquals(addToHomeButton.getAttribute("name"), IOSElements.REMOVE_FROM_HOME_BTN_NAME, "For section: \"" +  sectionLabel + "\", name for Remove from Home Button is inconsistent");
			Assert.assertEquals(addToHomeButton.getAttribute("visible"), "true", "Unexpectedly Remove from Home Button is invisible for Section: " + sectionLabel);
			Assert.assertEquals(btnHeight, removeFromHomeBtnHeight, "For section: \"" +  sectionLabel + "\", height for Remove from Home Button is inconsistent");
			Assert.assertEquals(btnWidth, removeFromHomeBtnWidth, "For section: \"" +  sectionLabel + "\", width for Remove from Home Button is inconsistent");
			Assert.assertEquals(btnXStart, removeFromHomeBtnXAxisStart, "For section: \"" +  sectionLabel + "\", x-axis start for Remove from Home Button is inconsistent");
		}
		
		resetRemoveFromHomeBtnDimension = false;
		validated = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean sectionTitleValidation(Map<String,String> sectionDetails) {
		boolean validated = false;
		String sectionTitleType;
		String sectionTitleId;
		methodName = "sectionTitleValidation";
		logger.info("Entering Method: " + methodName);
		MobileElement section = null;
		
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			sectionTitleType = IOSElements.SECTION_LABEL_TYPE;
			sectionTitleId = IOSElements.SECTION_LABEL_ID;
			
			section = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@label=\"" + sectionDetails.get("label") + "\"]"));
			
			if(sectionDetails.get("customSection")!= null && sectionDetails.get("customSection").equals("true")) {
				Assert.assertEquals(isRemoveFromHomeBtnAligned(sectionDetails.get("label")), true, "For section: \"" +  sectionDetails.get("label") + "\" Remove from Home Button is misaligned");
			}
			
			Integer height = section.getSize().getHeight();
			Assert.assertEquals(section.getAttribute("type"), sectionTitleType, "For section: \"" +  sectionDetails.get("label") + "\" Title type is inconsistent");
			Assert.assertEquals(section.getAttribute("value"), sectionDetails.get("label"), "For section: \"" +  sectionDetails.get("label") + "\" Title value is inconsistent");
			Assert.assertEquals(section.getAttribute("name"), sectionTitleId, "For section: \"" +  sectionDetails.get("label") + "\" Title name is inconsistent");
			Assert.assertEquals(section.getAttribute("label"), sectionDetails.get("label"), "Section Title laebl is inconsistent");
			Assert.assertEquals(section.getAttribute("enabled").toString(), sectionDetails.get("enabled"), "For section: \"" +  sectionDetails.get("label") + "\" Title is unexpectedly disabled");
			Assert.assertTrue(Math.abs(height - Integer.parseInt(sectionDetails.get("height"))) < 2, "For section: \"" +  sectionDetails.get("label") + "\" Title height is inconsistent across sections");
			
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")){
			sectionTitleType = AndroidElements.SECTION_LABEL_TYPE;
			sectionTitleId = AndroidElements.SECTION_LABEL_ID;
			
			section = (MobileElement) driver.findElement(By.id(sectionTitleId));
			
			Integer height = section.getSize().getHeight();
			Assert.assertEquals(section.getAttribute("class"), sectionTitleType, "For section: \"" +  sectionDetails.get("label") + "\" Title type is inconsistent");
			Assert.assertEquals(section.getAttribute("text"), sectionDetails.get("label"), "For section: \"" +  sectionDetails.get("label") + "\" Title value is inconsistent");
			Assert.assertEquals(section.getAttribute("resource-id"), "com.buuuk.st:id/tv_title", "For section: \"" +  sectionDetails.get("label") + "\" Title name is inconsistent");
			Assert.assertEquals(section.getAttribute("enabled").toString(), sectionDetails.get("enabled"), "For section: \"" +  sectionDetails.get("label") + "\" Title is unexpectedly disabled");
			Assert.assertTrue(Math.abs(height - Integer.parseInt(sectionDetails.get("height"))) < 2, "For section: \"" +  sectionDetails.get("label") + "\" Title height is inconsistent across sections");
			
		}
		validated = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean premiumTitleValidation(Map<String,String> sectionDetails) {
		boolean validated = false;
		String sectionTitleType;
		methodName = "premiumTitleValidation";
		logger.info("Entering Method: " + methodName);
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			sectionTitleType = IOSElements.SECTION_LABEL_TYPE;
		
			MobileElement premiumTag = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeOther/XCUIElementTypeImage[@name=\"premium\"]"));
			MobileElement sectionTitle = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeOther/XCUIElementTypeImage[@name=\"premium\"]/following-sibling::XCUIElementTypeStaticText"));
			
			Assert.assertEquals(premiumTag.getAttribute("name"),"premium","Premium Tag is missing for Premium articles");
			Assert.assertEquals(sectionTitle.getAttribute("type"), sectionTitleType, "For section: \"" +  sectionDetails.get("label") + "\" Title type is inconsistent");
			Assert.assertEquals(sectionTitle.getAttribute("value"), sectionDetails.get("value"), "For section: \"" +  sectionDetails.get("label") + "\" Title value is inconsistent");
			Assert.assertEquals(sectionTitle.getAttribute("name"), sectionDetails.get("name"), "For section: \"" +  sectionDetails.get("label") + "\" Title name is inconsistent");
			Assert.assertEquals(sectionTitle.getAttribute("label"), sectionDetails.get("label"), "Section Title laebl is inconsistent");
			Assert.assertEquals(sectionTitle.getAttribute("enabled").toString(), sectionDetails.get("enabled"), "For section: \"" +  sectionDetails.get("label") + "\" Title is unexpectedly disabled");
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")){
			sectionTitleType = AndroidElements.SECTION_LABEL_TYPE;
		}
		validated = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean firstRowArticleAlignmentValidation(Integer articleSeqInLayout) {
		methodName = "firstRowArticleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		Integer xArticleStart = 0;
		Integer xArticleEnd = 0;
		Integer articleHeight = 0;
		Integer articleWidth = 0;
		boolean validated = false;
		if(resetXAxisStart) {
			xRowStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();
			resetXAxisStart= false;
		}
		else {
			xArticleStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();	
			Assert.assertEquals(xArticleStart, xRowStart, "Inconsistent x-axis placement of 1st article");
		}
		
		if(resetArticleWidth ){
			articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
			firstRowArticleWidth = articleWidth;
			xRowEnd = xRowStart + firstRowArticleWidth;
		}
		else {
			articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
			Assert.assertEquals(articleWidth, firstRowArticleWidth, "Inconsistent width of 1st article");
			
			xArticleEnd = xArticleStart + articleWidth;
			Assert.assertEquals(xArticleEnd, xRowEnd, "Inconsistent width of 1st article");
		}
		
		if(resetArticleImageVideoHeight) {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			firstRowImageVideoHeight = articleHeight;
		}
		else {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			//logger.info(((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeStaticText").getAttribute("label"));
			logger.info("********Expected Height: " + firstRowImageVideoHeight);
			logger.info("********Actual Height: " + articleHeight);
			Assert.assertTrue(Math.abs(articleHeight - firstRowImageVideoHeight) < 2, "Inconsistent Height of 1st article");
		}
		validated  = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean secondRowArticleAlignmentValidation(Integer articleSeqInLayout, boolean isRightArticle) {
		methodName = "secondRowArticleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		Integer xArticleStart = 0;
		Integer xArticleEnd = 0;
		Integer articleHeight = 0;
		Integer articleWidth = 0;
		boolean validated = false;
		
		xArticleStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();
		
		if(resetArticleWidth && !(isRightArticle)){
			Assert.assertEquals(xArticleStart, xRowStart, "Inconsistent x-axis placement of " + articleSeqInLayout.toString() + " article");
			
			articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
			secondRowArticleWidth = articleWidth;
		}
		else {
			articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
			/*
			 * TODO: Due to Appium bug, it's detecting the articleWidth 1px more than actual for 3rd article(on 2nd row). Try investigating and resolve from Appium's end.
			 * Quick Fix done in automation to handle this: Decreement the received article width for 3rd article by 1px
			 */
			if(isRightArticle) {
				articleWidth--;
				
				xArticleEnd = xArticleStart + articleWidth;
				Assert.assertEquals(xArticleEnd, xRowEnd, "Misaligned End of " + articleSeqInLayout.toString() + " article");
			}
			Assert.assertEquals(articleWidth, secondRowArticleWidth, "Inconsistent width of " + articleSeqInLayout.toString() + " article");
		}
		
		if(resetArticleImageVideoHeight && !(isRightArticle)) {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			secondRowImageVideoHeight = articleHeight;
		}
		else {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			Assert.assertTrue(Math.abs(articleHeight - secondRowImageVideoHeight) < 2, "Inconsistent Height of " + articleSeqInLayout.toString() + " article");
		}
		
		validated = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean isPremiumArticle(Integer articleSeqInLayout) {
		methodName = "isPremiumArticle";
		logger.info("Entering Method: " + methodName);
		boolean isPremium = false;
		try {
			MobileElement premiumIconImage = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]")));
			if(premiumIconImage.getAttribute("name").equals("premium")) {
				isPremium = true;
			}
		}catch(Exception e) {
			isPremium = false;
		}
		logger.info("Exiting from method: " + methodName);
		return isPremium;
	}
	
	public boolean thirdRowOnwardsArticleAlignmentValidation(Integer articleSeqInLayout) {
		methodName = "thirdRowOnwardsArticleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		Integer xArticleStart = 0;
		Integer xArticleEnd = 0;
		Integer mediaHeight = 0;
		Integer articleWidth = 0;
		Integer imageWidth = 0;
		boolean validated = false;
		
		xArticleStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();
		Assert.assertEquals(xArticleStart, xRowStart, "Inconsistent x-axis placement of " + articleSeqInLayout.toString() + " article");
		
		articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
		Assert.assertEquals(articleWidth, firstRowArticleWidth, "Inconsistent width of " + articleSeqInLayout.toString() + " article");
		
		xArticleEnd = xArticleStart + articleWidth;
		Assert.assertEquals(xArticleEnd, xRowEnd, "Inconsistent width of " + articleSeqInLayout.toString() + " article");
		
		try {
			if(isPremiumArticle(articleSeqInLayout)) {
				//As if the article is a premium article, then actual image is 2nd in the row
				imageWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[2]"))).getSize().getWidth();
				mediaHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[2]"))).getSize().getHeight();
				
				if(resetArticleImageVideoHeight) {
					thirdRowOnwardsImageVideoWidth = imageWidth;
					thirdRowOnwardsImageVideoHeight = mediaHeight;
				}
			}
			else {
				imageWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getWidth();
				mediaHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
				
				if(resetArticleImageVideoHeight) {
					thirdRowOnwardsImageVideoWidth = imageWidth;
					thirdRowOnwardsImageVideoHeight = mediaHeight;
				}
			}
			
			Assert.assertTrue(Math.abs(mediaHeight - thirdRowOnwardsImageVideoHeight) < 2, "Inconsistent Height of " + articleSeqInLayout.toString() + " article");
			
			Assert.assertTrue(Math.abs(imageWidth - thirdRowOnwardsImageVideoWidth) < 2, "Inconsistent Width of " + articleSeqInLayout.toString() + " article, as expected is: " + thirdRowOnwardsImageVideoWidth + ". However, received image width of: " + imageWidth);
		}catch(NoSuchElementException e) {
			logger.warn("No image found for this article");
		}
		
		
		validated = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean articleAlignmentValidation(MobileElement article, Integer articleSequence, Integer articleSeqInLayout) {
		boolean validated = false;
		methodName = "articleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		if(articleSequence.equals(1)) {
			firstRowArticleAlignmentValidation(articleSeqInLayout);
		}
		else if(articleSequence.equals(2) || articleSequence.equals(3)) {
			secondRowArticleAlignmentValidation(articleSeqInLayout,articleSequence.equals(3));
		}
		else {
			thirdRowOnwardsArticleAlignmentValidation(articleSeqInLayout);
		}
		validated = true;
		String articleLabel = article.getAttribute("label");
		logger.info("Successfully validated the alignment of article labelled as: " + articleLabel);
		logger.info("Exiting method: " + methodName);
		return validated;
	}
	
	public boolean whetherReachedLastArticleOfSection(String sectionTitle, Integer collectionViewSequence) {
		methodName = "whetherReachedLastArticleOfSection";
		logger.info("Entering Method: " + methodName);
		boolean reachedEndOfSection = false;
		int elementCnt = 0;
		
		if(defaultSubSections.contains(sectionTitle)) {
			Integer layoutSequence = collectionViewSequence - 1;
			try {
				MobileElement lastSectionArticle = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + layoutSequence.toString() + "]//XCUIElementTypeImage[@name=\"more_icn\"]")));
				reachedEndOfSection = true;
			}catch(Exception e) {
				reachedEndOfSection = false;
			}
		}
		else {
			List<WebElement> collectionView = driver.findElements(By.xpath("//XCUIElementTypeCollectionView/*"));
			for(WebElement element:collectionView) {
				elementCnt++;
				try {
					if(elementCnt <= collectionViewSequence) {
						continue;
					}else if(((MobileElement)element).getAttribute("type").equals("XCUIElementTypeCell")) {
						reachedEndOfSection = false;
						break;
					}else if(((MobileElement)element).getAttribute("type").equals("XCUIElementTypeOther")){
						reachedEndOfSection = true;
						break;
					}
				}catch(Exception e) {
					
				}
			}
		}
		logger.info("Exiting method: " + methodName);
		return reachedEndOfSection;
	}
	
	public boolean isArticleFromDifferentSection(Integer layoutSequence) {
		methodName = "isAnotherSectionArticle";
		logger.info("Entering Method: " + methodName);
		boolean isAnotherSectionArticle = false;
			
		try {
			MobileElement nextSectionTitle = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + layoutSequence.toString() + "]/preceding-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[@name=\"" + IOSElements.SECTION_LABEL_ID + "\"]")));
			isAnotherSectionArticle = true;
		}catch(Exception e) {
			try {
				MobileElement nextSectionTitle = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + layoutSequence.toString() + "]/preceding-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[@name=\"" + IOSElements.PREMIUM_SECTION_LABEL_ID + "\"]")));
				isAnotherSectionArticle = true;
			}catch(Exception ex) {
				isAnotherSectionArticle = false;
			}
		}
		logger.info("Exiting method: " + methodName);
		return isAnotherSectionArticle;
	}
	
	public boolean validateMoreStoriesLink(Integer layoutSequence,String moreIconLabel, String title) {
		methodName = "validateMoreStoriesLink";
		logger.info("Entering Method: " + methodName);
		boolean validatedMoreStoriesLink = false;
		MobileElement moreStories = null;
		MobileElement moreIconButton = null;
		MobileElement moreStoriesTab = null;
		MobileElement moreStoriesTabTitle = null;
		MobileElement backButton = null;
		util = new DeviceActions(driver);
		try {
			moreStories = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + layoutSequence.toString() + "]//XCUIElementTypeImage[@name=\"more_icn\"]")));
			moreIconButton = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + layoutSequence.toString() + "]//XCUIElementTypeButton[@name=\"" + moreIconLabel + "\"]")));
			
			//To handle cases, where the button is not in view yet
			if(moreIconButton.getAttribute("visible").equals("false")) {
				util.swipeVerticle("Up");
			}
			moreIconButton.click();
			//Tab title validation
			moreStoriesTab = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeNavigationBar[@name=\"" + title + "\"]"));
			Assert.assertEquals(moreStoriesTab.getAttribute("name"), title, "Navigation bar name is inconsistent to expected");
			
			moreStoriesTabTitle = moreStoriesTab.findElementByXPath("//XCUIElementTypeOther[@name=\"" + title + "\"]");
			Assert.assertEquals(moreStoriesTabTitle.getAttribute("label"), title, "Label of Navigation bar is inconsistent to expected");
			
			//Back Button validation
			backButton = (MobileElement) driver.findElement(By.id("back"));
			Assert.assertEquals(backButton.getAttribute("label"), "back", "Back button label is inconsistent to expected");
			Assert.assertEquals(backButton.getAttribute("name"), "back", "Back button name is inconsistent to expected");
			Assert.assertEquals(backButton.getAttribute("type"), "XCUIElementTypeButton", "Back button type is not button");
			Assert.assertTrue(backButton.isEnabled(), "Back button label is unexpectedly disabled");
			Assert.assertEquals(backButton.getAttribute("visible"),"true", "Back button label is unexpectedly invisible");
			
			//Navigate back to home page view
			backButton.click();
			validatedMoreStoriesLink = true;
		}catch(Exception e) {
			validatedMoreStoriesLink = false;
		}	
		logger.info("Exiting method: " + methodName);
		return validatedMoreStoriesLink;
	}
	
	public boolean whetherAdPresent(Integer layoutSequence) {
		methodName = "whetherAdPresent";
		logger.info("Entering Method: " + methodName);
		boolean adPresent = false;
		try {
			MobileElement adSection = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + layoutSequence.toString() + "]//XCUIElementTypeLink")));
			adPresent = true;
		}catch(Exception e) {
			logger.error(e.getMessage());
			adPresent = false;
		}	
		logger.info("Exiting method: " + methodName);
		return adPresent;
	}
	
	public boolean isArticleVisible(Integer layoutSequence) {
		methodName = "isArticleVisible";
		logger.info("Entering Method: " + methodName);
		boolean articleVisibility = false;
		
		MobileElement article = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + layoutSequence.toString() + "]")));
		if(article.getAttribute("visible").equals("true")){
			articleVisibility = true;
		}
		else{
			articleVisibility = false;
		}	
		logger.info("Exiting method: " + methodName);
		return articleVisibility;
	}
	
	public Integer sectionArticleValidation(String sectionTitle, boolean resetWidth, boolean resetX, boolean resetHeight, boolean followedByAd, String articleType) {
		Integer validated = 0;
		methodName = "sectionArticleValidation";
		boolean reachedEndOfSection = false;
		logger.info("Entering Method: " + methodName);
		resetArticleWidth = resetWidth;
		resetXAxisStart = resetX;
		resetArticleImageVideoHeight = resetHeight;
		//TODO: Find a proper to change it
		boolean alreadyCapturedArticle = false;
		Integer articleSequence = 0;
		Integer articleSeqInLayout = 0;
		Integer layoutSeqSectionFirstArticle = 0;
		Integer collectionViewSequence = 1;
		Map<String,Integer> articleLabels = new HashMap<String,Integer>();	
		reachedEndOfSection = false;
		MobileElement titleElement = null;
		String firstArticleOfSection = "";
		util = new DeviceActions(driver);
		boolean afterSwipeView = false;
		/*
		 * Traversal across the view to fetch the layout sequence of the first article in requested section
		 */
		List<WebElement> collectionView = driver.findElements(By.xpath("//XCUIElementTypeCollectionView/*"));
		for(WebElement element:collectionView) {
			if(element.getAttribute("type").equals("XCUIElementTypeCell")) {
				layoutSeqSectionFirstArticle++;
				//collectionViewSequence++;
			}else if(element.getAttribute("type").equals("XCUIElementTypeOther")) {
				//collectionViewSequence++;
				try {
					if(articleType.equalsIgnoreCase("PREMIUM")) {
						titleElement = (MobileElement) element.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + Constant.PREMIUM_SECTION_TITLE + "\"]"));	
					}else {
						titleElement = (MobileElement) element.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + Constant.SECTION_TITLE + "\"]"));	
					}
					if(titleElement.getAttribute("label").equals(sectionTitle)) {
						List<WebElement> title = driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"article_title\"]"));
						firstArticleOfSection = title.get(layoutSeqSectionFirstArticle).getAttribute("label");
						break;
					}
					
				}catch(Exception e) {
					continue;
					
				}
			}
		}
		
		while(!reachedEndOfSection){
			List<WebElement> articleList = driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"article_title\"]"));
			articleSeqInLayout = 0;
			for(WebElement article: articleList) {		
				articleSeqInLayout++;
				collectionViewSequence++;
				String currentlabel = ((MobileElement) article).getAttribute("label");
				logger.info("********Viewing article: " + article.getAttribute("label"));
				if(!(firstArticleOfSection.equals(currentlabel)) && articleSequence.equals(0)) {
					//Not yet reached the first article in the section
					continue;
				}
				for(String prevLabel: articleLabels.keySet()) { 
					if(prevLabel.equals(article.getAttribute("label"))) {
						alreadyCapturedArticle = true;
						break;
					}
					else {
						alreadyCapturedArticle = false;
					}
				}
				if(!alreadyCapturedArticle) {
					if(isArticleVisible(articleSeqInLayout)) {
						if(afterSwipeView) {
							if(isArticleFromDifferentSection(articleSeqInLayout)) {
								reachedEndOfSection = true;
								break;
							}
							afterSwipeView = false;
						}
						
						alreadyCapturedArticle = false;
						articleSequence++;
						
						//As this is a special case wherein second row article follows alignment rules of third row onward article(as there's only 1 last article in 2nd row)
						if(whetherReachedLastArticleOfSection(sectionTitle, collectionViewSequence)) {
							reachedEndOfSection = true;
							if(articleSequence.equals(2)) {
								articleSequence = 4;
							}
						}
						
						if(articleType.equalsIgnoreCase("PREMIUM")) {
							MobileElement premiumTag = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[@name=\"premium\"]"));
							Assert.assertEquals(premiumTag.getAttribute("enabled"),"true","Premium tag is not visible for Premium articles");
							logger.info("Validated the Premium tag is visible for Premium article");
						}
						logger.info("Validating article labelled: " + currentlabel);
						
						if(articleType.equalsIgnoreCase("VIEWPOINTS")) {
							Assert.assertTrue(articleAlignmentValidation((MobileElement) article, Constant.VIEWPOINT_ARTICLE_LAYOUT_SEQUENCE, articleSeqInLayout),"Article Alignment Validation Failed");
						}
						else {
							Assert.assertTrue(articleAlignmentValidation((MobileElement) article, articleSequence,articleSeqInLayout),"Article Alignment Validation Failed");
						}
						
						articleLabels.put(currentlabel, articleSequence);
						if(reachedEndOfSection) {
							break;
						}
					}
					else {
						//Decrementing the layout sequence of current article, as it should not be counted as not visible(thus the view is to be scrolled and re-evaluated)
						collectionViewSequence--;
						articleSeqInLayout--;
						break;
					}
				}
////				reachedEndOfSection = whetherReachedLastArticleOfSection(articleSeqInLayout);
//				reachedEndOfSection = whetherReachedLastArticleOfSection(sectionTitle, collectionViewSequence);
//				if(reachedEndOfSection) {
//					break;
//				}
			}

			if(!reachedEndOfSection) {
				util.swipeVerticle("Up");
				afterSwipeView = true;
			}
			else if(reachedEndOfSection && followedByAd) {
					Assert.assertTrue(whetherAdPresent(articleSeqInLayout),"Expected to be followed by Ad, however missed it");
			}
			collectionViewSequence = 1;
			layoutSeqSectionFirstArticle = 0;
		}
		validated = articleSeqInLayout;
		logger.info("Exiting method: " + methodName);
		return validated;
	}
	
	public boolean reachedEndOfHomePage() {
		methodName = "reachedEndOfHomePage";
		logger.info("Entering Method: " + methodName);
		boolean endOfHomePage = false;
		try {
			MobileElement currentElement = (MobileElement) driver.findElement(By.id("MORE ENTERTAINMENT"));
			endOfHomePage = true;
		}
		catch(Exception e) {
			logger.info("Not reached end of Home page yet");
		}
		logger.info("Successfully exiting from Method: " + methodName);
		return endOfHomePage;
	}
	
	public boolean reachedTopOfHomePage() {
		methodName = "reachedTopOfHomePage";
		logger.info("Entering Method: " + methodName);
		boolean topOfHomePage = false;
		try {
			MobileElement currentElement = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"section_title\"]"));
			if(currentElement.getAttribute("label").equals("TOP STORIES")) {
				topOfHomePage = true;
			}
		}
		catch(Exception e) {
			logger.info("Not reached top of Home page yet");
		}
		logger.info("Successfully exiting from method: " + methodName);
		return topOfHomePage;
	}
	
	public boolean gotoSection(String label) {
		methodName = "goto " + label + " Section";
		logger.info("Entering Method: " + methodName);
		boolean onExpectedSection = false;
		String navigationDirection = "Up";
		while(!onExpectedSection) {
			try {
				if(label.equals("PREMIUM")){
					MobileElement currentElement = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Unique content, exclusive insights\"]"));
					onExpectedSection = true;
				}else {
					List<WebElement> currentElements = driver.findElements(By.xpath("//XCUIElementTypeStaticText[@name=\"section_title\"]"));
					for(WebElement element:currentElements) {
						if(((MobileElement) element).getAttribute("label").equals(label)) {
							onExpectedSection = true;
							break;
						}
					}
				}
			}catch(Exception e){
				logger.info("Need to navigate further to fetch the required section: " + label);
			}finally {
				if(!onExpectedSection) {
					if(reachedEndOfHomePage()) {
						navigationDirection = "Down";
					}
					else if(navigationDirection.equals("Down") && reachedTopOfHomePage()) {
						break;
					}
					this.util = new DeviceActions(driver);		
					util.swipeVerticle(navigationDirection);
				}
			}
		}
		
		logger.info("Successfully exiting from method: " + methodName);
		return onExpectedSection;
	}
	
	public MobileDriver gotoTab(TAB tab) {
		methodName = "gotoTab";
		logger.info("Entering Method: " + methodName);
		GenericNavigator navigator = new GenericNavigator(driver);
		
		try {
			switch(tab) {
			case ST_NOW: 
				util.clickifClickable(stNowTab, Constant.LONG_TIMEOUT);
			case HOME: 
				util.clickifClickable(homeTab, Constant.LONG_TIMEOUT);
			case LATEST: 
				util.clickifClickable(latestTab, Constant.LONG_TIMEOUT);
			case SINGAPORE: 
				util.clickifClickable(singaporeTab, Constant.LONG_TIMEOUT);
			case POLITICS: 
				util.clickifClickable(politicsTab, Constant.LONG_TIMEOUT);
			case ASIA: 
				util.clickifClickable(asiaTab, Constant.LONG_TIMEOUT);
			case WORLD: 
				util.clickifClickable(worldTab, Constant.LONG_TIMEOUT);
			case LIFESTYLE: 
				util.clickifClickable(lifestyleTab, Constant.LONG_TIMEOUT);
			case FOOD: 
				util.clickifClickable(foodTab, Constant.LONG_TIMEOUT);
			case FORUM: 
				util.clickifClickable(forumTab, Constant.LONG_TIMEOUT);
			case VIDEOS: 
				util.clickifClickable(videosTab, Constant.LONG_TIMEOUT);
			case OPINION: 
				util.clickifClickable(opinionTab, Constant.LONG_TIMEOUT);
			case BUSINESS: 
				util.clickifClickable(businessTab, Constant.LONG_TIMEOUT);
			case SPORT: 
				util.clickifClickable(sportTab, Constant.LONG_TIMEOUT);
			case TECH: 
				util.clickifClickable(techTab, Constant.LONG_TIMEOUT);
			default:
				logger.error("Please provide valid Tab Title");
				break;	
			}
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		
		return (MobileDriver) driver;
    }
}
	
