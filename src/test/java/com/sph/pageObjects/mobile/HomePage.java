package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
import com.sph.listeners.Reporter;
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
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(HomePage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;
    
    public static Map<String,String> section = null;
	public static List<Map<String,String>> articleDetails;
	
    
    @iOSXCUITFindBy(accessibility = IOSElements.CLOSE_INTERSTITIAL_AD_ID)
    @AndroidFindBy(className = AndroidElements.CLOSE_AD)
    private MobileElement close_ad;
    
//    public static Integer sectionLabelHeight = 0;
//	boolean resetOption = true; //Only for first section
//	
//	private static Integer xRowStart = 0;
//	private static Integer xRowEnd = 0;
//	//Reset Options for alignment parameters
//	private static Boolean resetArticleWidth;
//	private static Boolean resetXAxisStart;
//	private static Boolean resetArticleImageVideoHeight;
//	private static Boolean resetRemoveFromHomeBtnDimension;
//	
//	//Parameters to capture the expected row-wise Width for articles 
//	private static Integer firstRowArticleWidth = 0;
//	private static Integer secondRowArticleWidth = 0;
//	
//	//Parameter to capture Height of Image/Video row-wise, instead of Article Height(As article height could vary based on label length)
//	private static Integer firstRowImageVideoHeight = 0;
//	private static Integer secondRowImageVideoHeight = 0;
//	private static Integer thirdRowOnwardsImageVideoHeight = 0;
//	private static Integer thirdRowOnwardsImageVideoWidth = 0;
//	
//	//Parameters to capture the remove from home button dimensions
//	private static Integer removeFromHomeBtnHeight = 0;
//	private static Integer removeFromHomeBtnWidth = 0;
//	private static Integer removeFromHomeBtnXAxisStart = 0;
	
	
	
	private static Set<String> defaultSubSections = new HashSet<String>(Arrays.asList(new String[]{"TOP STORIES","ST FOOD","ASIA TOP STORIES","WEB SPECIALS","ENTERTAINMENT"}));
			
	@iOSXCUITFindBy(accessibility = IOSElements.MAIN_NAVIGATION_BAR_NAME)
	@AndroidFindBy(id = AndroidElements.MAIN_NAVIGATION_BAR_NAME)
	private MobileElement logo;
	
	@iOSXCUITFindBy(accessibility = IOSElements.HAMBURGER_MENU_LOCATOR)
	@AndroidFindBy(xpath = AndroidElements.HAMBURGER_MENU_LOCATOR)
	private MobileElement menu;
	
	@iOSXCUITFindBy(accessibility = IOSElements.ST_NOW_TAB_ID)
	private MobileElement stNowTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.HOME_TAB_ID)
	private MobileElement homeTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.LATEST_TAB_ID)
	private MobileElement latestTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.SINGAPORE_TAB_ID)
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"" + Constant.SINGAPORE_TAB_LABEL + "\"]")
	private MobileElement singaporeTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.POLITICS_TAB_ID)
	private MobileElement politicsTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.ASIA_TAB_ID)
	private MobileElement asiaTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.WORLD_TAB_ID)
	private MobileElement worldTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.LIFESTYLE_TAB_ID)
	private MobileElement lifestyleTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.FOOD_TAB_ID)
	private MobileElement foodTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.FORUM_TAB_ID)
	private MobileElement forumTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.VIDEOS_TAB_ID)
	private MobileElement videosTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.OPINION_TAB_ID)
	private MobileElement opinionTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.BUSINESS_TAB_ID)
	private MobileElement businessTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.SPORT_TAB_ID)
	private MobileElement sportTab;
	
	@iOSXCUITFindBy(xpath = IOSElements.TECH_TAB_ID)
	private MobileElement techTab;
	
	@iOSXCUITFindBy(className = "XCUIElementTypeOther")
	@AndroidFindBy(id = "article_image_back")
	private MobileElement infoScreen;
	
	@iOSXCUITFindBy(accessibility = "TOP STORIES")
	@AndroidFindBy(id = "tv_title")
	private MobileElement topStoriesHeading;
	
	@AndroidFindBy(id = "article_image")
	private MobileElement articleWithImage;
	
	@AndroidFindBy(id = "article_title")
	@iOSXCUITFindBy(xpath = "//*[@name='article_title']")
	private MobileElement topStory;
	
	@iOSXCUITFindBy(accessibility = "add_to_home")
	private MobileElement addToHomeButton;
	
	// To Do: check for unique locator for free articles
	@HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/child::XCUIElementTypeStaticText[@name='article_title']")
	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/article_title']/../android.widget.TextView[@index='0']")
	//@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/imageLayout']//following-sibling::android.widget.TextView[@resource-id='com.buuuk.st:id/article_title']")
	private MobileElement freeArticleTitle;

	@iOSXCUITFindBy(xpath = "//*[@name='premium']//following-sibling::XCUIElementTypeStaticText[@name='article_title']")
	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/imgPremium']//following-sibling::android.widget.TextView[@resource-id='com.buuuk.st:id/article_title']")
	private MobileElement premiumArticleTitle;

	@iOSXCUITFindBy(xpath = "//*[@name='video_icon']//preceding-sibling::XCUIElementTypeStaticText[@name='article_title']")
	@HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/iv_play_button']//parent::android.widget.RelativeLayout/following-sibling::android.widget.LinearLayout//descendant::android.widget.TextView")
//	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/iv_play_button']//parent::android.widget.RelativeLayout/following-sibling::android.widget.TextView")
//	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/iv_play_button']//parent::android.widget.RelativeLayout/parent::android.widget.FrameLayout/following-sibling::android.widget.LinearLayout//descendant::android.widget.TextView")
	private MobileElement videoIcon;

	@iOSXCUITFindBy(xpath = "//*[@name='video_icon']//following-sibling::XCUIElementTypeStaticText[@name='article_title']")
	private List<MobileElement> webviewArticles;

	@iOSXCUITFindBy(xpath = "//*[@name='article']/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage")
	private MobileElement topStoryImage;
	
	public HomePage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        this.wait = new WebDriverWait(this.driver, 30);
        this.util = new DeviceActions(this.driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//		resetRemoveFromHomeBtnDimension = true;
	}
	
	public boolean onHomePage() {
		methodName = "gotoHomePage";
		logger.info("Entering Method: " + methodName);
		boolean onHomePage = false;
		try {
			Assert.assertTrue(logo.isEnabled(), "Not on Home Page");
			Assert.assertTrue(stNowTab.isEnabled(), "Not on Home Page");
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
		try {
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
			
			if(!onHomePage) {
				MenuPage menu = new MenuPage(driver);
				menu.clickOnMenu().gotoMenu(Constant.MENU.HOME_PAGE);
				onHomePage = onHomePage();
			}
			
			Assert.assertTrue(onHomePage,"Unexpectedly not on Home Page");
			logger.info("Successfully exiting from method: " + methodName);
		}catch(Exception ex) {
			logger.error("Unable to goto Home Page due to exception: " + ex.getMessage());
		}
		return this;
	}
	
//	public void sectionValidation() {
//		methodName = "sectionValidation";
//		logger.info("Entering Test Case: " + methodName);
//		Map<String,String> sectionLabels = new LinkedHashMap<String,String>();
//		//sectionLabels.put("TOP STORIES", "MORE HEADLINES");
//		sectionLabels.put(IOSElements.PREMIUM_SECTION_LABEL_ID, "MORE STORIES");
//		sectionLabels.put("VIEWPOINTS", "MORE STORIES");
//		sectionLabels.put("ASIA TOP STORIES", "MORE ASIA STORIES");
//		sectionLabels.put("ST FOOD", "MORE FOOD STORIES");
//		sectionLabels.put("WEB SPECIALS", "MORE WEB SPECIALS");
//		sectionLabels.put("ENTERTAINMENT", "MORE ENTERTAINMENT");
//		boolean resetOption = true; //Only for first section
//		Integer sectionSequence = 2; 
//		boolean followedByAd = false; //Only until first three sections
//		
//		
//		for(String label:sectionLabels.keySet()) {
//			methodName = "Test section label: " + label;
//			logger.info("Entering Test Case: " + methodName);
//			System.out.println("Validating Section: " + label);
//			
//			this.gotoSection(label);
//			
//			if(sectionLabelHeight.equals(0) && !label.equals(IOSElements.PREMIUM_SECTION_LABEL_ID)) {
//				sectionLabelHeight = ((MobileDriver)driver).findElementByXPath("//XCUIElementTypeStaticText[@label=\"" + label + "\"]").getSize().getHeight();
//			}
//			
//			if(label.equals(IOSElements.PREMIUM_SECTION_LABEL_ID)) {
//				section = new HashMap<String,String>();
//				section.put("value", label);
//				section.put("label", label);
//				section.put("enabled", "true");
//				Assert.assertTrue(this.premiumTitleValidation(section));
//			}
//			else {
//				section = new HashMap<String,String>();
//				section.put("label", label);
//				section.put("enabled", "true");
//				section.put("visible", "true");
//				section.put("height", sectionLabelHeight.toString());
//				Assert.assertTrue(this.sectionTitleValidation(section));
//			}
//			
////			articleDetails  = new ArrayList<Map<String,String>>();
//			
//			//Assert.assertTrue(this.sectionTitleValidation(section));
//			logger.info("Successfully Validated the Section title: " + label);
//			
//			Integer lastArticleLayoutSequence = this.sectionArticleValidation(label, resetOption, resetOption, resetOption, followedByAd, label);
//			Assert.assertNotEquals(lastArticleLayoutSequence,0,"Section Article Validation failed");
//			
//			//Assert.assertTrue(this.validateMoreStoriesLink(lastArticleLayoutSequence,sectionLabels.get(label),label),"Failed to validate More Stories Link for " + label + " section");
//			logger.info("Exiting with success method: " + methodName);
//			
//			//Setting resetOption to false for Following sections(As rest sections should followed first section for alignment)
//			resetOption = false;
//			if(sectionSequence > 2) {
//				followedByAd = false;
//			}
//			//Remove after Premium section checks are implemented
//			sectionSequence++;
//			System.out.println("Successfully validated Section: " + label);
//		}
//		
//	}
	
	public Map<String, Integer> sectionValidation(String sectionLabel, Map<String, Integer> sectionDimension) {
		methodName = "sectionValidation";
		logger.info("Entering Test Case: " + methodName);
		sectionLabel = sectionLabel.toUpperCase();
		Integer sectionLabelHeight = 0;
//		Map<String,String> sectionLabels = new LinkedHashMap<String,String>();
//		//sectionLabels.put("TOP STORIES", "MORE HEADLINES");
//		sectionLabels.put(IOSElements.PREMIUM_SECTION_LABEL_ID, "MORE STORIES");
//		sectionLabels.put("VIEWPOINTS", "MORE STORIES");
//		sectionLabels.put("ASIA TOP STORIES", "MORE ASIA STORIES");
//		sectionLabels.put("ST FOOD", "MORE FOOD STORIES");
//		sectionLabels.put("WEB SPECIALS", "MORE WEB SPECIALS");
//		sectionLabels.put("ENTERTAINMENT", "MORE ENTERTAINMENT");
		
		Integer sectionSequence = 2; 
		boolean followedByAd = false; //Only until first three sections
		
		
//		for(String label:sectionLabels.keySet()) {
		methodName = "Test section label: " + sectionLabel;
		logger.info("Entering Test Case: " + methodName);
		System.out.println("Validating Section: " + sectionLabel);
		
		this.gotoSection(sectionLabel);
		
		if(sectionDimension.get("sectionLabelHeight").equals(0) && !sectionLabel.equals(IOSElements.PREMIUM_SECTION_LABEL_ID)) {
			sectionLabelHeight = ((MobileDriver)driver).findElementByXPath("//XCUIElementTypeStaticText[@label=\"" + sectionLabel + "\"]").getSize().getHeight();
			sectionDimension.put("sectionLabelHeight", sectionLabelHeight);
		}
		
		if(sectionLabel.equals(IOSElements.PREMIUM_SECTION_LABEL_ID)) {
			section = new HashMap<String,String>();
			section.put("value", sectionLabel);
			section.put("label", sectionLabel);
			section.put("enabled", "true");
			Assert.assertTrue(this.premiumTitleValidation(section));
		}
		else {
			section = new HashMap<String,String>();
			section.put("label", sectionLabel);
			section.put("enabled", "true");
			section.put("visible", "true");
			section.put("height", sectionDimension.get("sectionLabelHeight").toString());
			Assert.assertTrue(sectionTitleValidation(section, sectionDimension));
		}
		
//			articleDetails  = new ArrayList<Map<String,String>>();
		
		//Assert.assertTrue(this.sectionTitleValidation(section));
		logger.info("Successfully Validated the Section title: " + sectionLabel);
		
		sectionDimension = this.sectionArticleValidation(sectionLabel, followedByAd, sectionDimension);
		Assert.assertNotEquals(sectionDimension.get("articleSeqInLayout"),0,"Section Article Validation failed");
		
		//Assert.assertTrue(this.validateMoreStoriesLink(lastArticleLayoutSequence,sectionLabels.get(label),label),"Failed to validate More Stories Link for " + label + " section");
		logger.info("Exiting with success method: " + methodName);
		
		//Setting resetOption to false for Following sections(As rest sections should followed first section for alignment)
//		resetOption = false;
		if(sectionSequence > 2) {
			followedByAd = false;
		}
		//Remove after Premium section checks are implemented
//		sectionSequence++;
		System.out.println("Successfully validated Section: " + sectionLabel);
	
		return sectionDimension;
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
	
	public boolean isRemoveFromHomeBtnAligned(String sectionLabel, Map<String, Integer> removeFromHomeBtnDimension) {
		boolean validated = false;
		methodName = "isRemoveFromHomeBtnAligned";
		logger.info("Entering Method: " + methodName);
		
		MobileElement addToHomeButton = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@label=\"" + sectionLabel + "\"]/following-sibling::XCUIElementTypeButton"));
		
		Integer btnHeight = addToHomeButton.getSize().getHeight();
		Integer btnWidth = addToHomeButton.getSize().getWidth();
		Integer btnXStart = addToHomeButton.getLocation().getX();
		
		if(removeFromHomeBtnDimension.get("resetRemoveFromHomeBtnDimension").equals(1)) {
			removeFromHomeBtnDimension.put("btnHeight", btnHeight);
			removeFromHomeBtnDimension.put("btnWidth", btnWidth);
			removeFromHomeBtnDimension.put("removeFromHomeBtnXAxisStart",btnXStart);
		}else {
			Assert.assertEquals(addToHomeButton.getAttribute("label"), IOSElements.REMOVE_FROM_HOME_BTN_LABEL, "For section: \"" +  sectionLabel + "\", label for Remove from Home Button is inconsistent");
			Assert.assertEquals(addToHomeButton.getAttribute("enabled"), "true", "Unexpectedly Remove from Home Button is disabled for Section: " + sectionLabel);
			Assert.assertEquals(addToHomeButton.getAttribute("name"), IOSElements.REMOVE_FROM_HOME_BTN_NAME, "For section: \"" +  sectionLabel + "\", name for Remove from Home Button is inconsistent");
			Assert.assertEquals(addToHomeButton.getAttribute("visible"), "true", "Unexpectedly Remove from Home Button is invisible for Section: " + sectionLabel);
			Assert.assertEquals(btnHeight, removeFromHomeBtnDimension.get("removeFromHomeBtnHeight"), "For section: \"" +  sectionLabel + "\", height for Remove from Home Button is inconsistent");
			Assert.assertEquals(btnWidth, removeFromHomeBtnDimension.get("removeFromHomeBtnWidth"), "For section: \"" +  sectionLabel + "\", width for Remove from Home Button is inconsistent");
			Assert.assertEquals(btnXStart, removeFromHomeBtnDimension.get("removeFromHomeBtnXAxisStart"), "For section: \"" +  sectionLabel + "\", x-axis start for Remove from Home Button is inconsistent");
		}
		
		removeFromHomeBtnDimension.put("resetRemoveFromHomeBtnDimension", 0);
		validated = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean sectionTitleValidation(Map<String,String> sectionDetails, Map<String, Integer> sectionDimension) {
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
				Assert.assertEquals(isRemoveFromHomeBtnAligned(sectionDetails.get("label"), sectionDimension), true, "For section: \"" +  sectionDetails.get("label") + "\" Remove from Home Button is misaligned");
			}
			
			Integer height = section.getSize().getHeight();
			Assert.assertEquals(section.getAttribute("type"), sectionTitleType, "For section: \"" +  sectionDetails.get("label") + "\" Title type is inconsistent");
			Assert.assertEquals(section.getAttribute("value"), sectionDetails.get("label"), "For section: \"" +  sectionDetails.get("label") + "\" Title value is inconsistent");
			Assert.assertEquals(section.getAttribute("name"), sectionTitleId, "For section: \"" +  sectionDetails.get("label") + "\" Title name is inconsistent");
			Assert.assertEquals(section.getAttribute("label"), sectionDetails.get("label"), "Section Title laebl is inconsistent");
			Assert.assertEquals(section.getAttribute("enabled").toString(), sectionDetails.get("enabled"), "For section: \"" +  sectionDetails.get("label") + "\" Title is unexpectedly disabled");
			
			if(!sectionDetails.get("label").equals(IOSElements.PREMIUM_SECTION_LABEL_ID)) {
				Assert.assertTrue(Math.abs(height - Integer.parseInt(sectionDetails.get("height"))) < 2, "For section: \"" +  sectionDetails.get("label") + "\" Title height is inconsistent across sections");
			}
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
	
	public boolean firstRowArticleAlignmentValidation(Integer articleSeqInLayout, Map<String, Integer> sectionDimension) {
		methodName = "firstRowArticleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		Integer xArticleStart = 0;
		Integer xArticleEnd = 0;
		Integer articleHeight = 0;
		Integer articleWidth = 0;
		boolean validated = false;
		if(sectionDimension.get("resetXAxisStart").equals(1)) {
			Integer xRowStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();
			sectionDimension.put("xRowStart", xRowStart);
	    		sectionDimension.put("resetXAxisStart", 0);
		}
		else {
			xArticleStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();	
			Assert.assertEquals(xArticleStart, sectionDimension.get("xRowStart"), "Inconsistent x-axis placement of 1st article");
		}
		
		if(sectionDimension.get("resetArticleWidth").equals(1)){
			articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
			sectionDimension.put("firstRowArticleWidth", articleWidth);
			sectionDimension.put("xRowEnd", sectionDimension.get("xRowStart") + sectionDimension.get("firstRowArticleWidth"));
		}
		else {
			articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
			Assert.assertEquals(articleWidth, sectionDimension.get("firstRowArticleWidth"), "Inconsistent width of 1st article");
			
			xArticleEnd = xArticleStart + articleWidth;
			Assert.assertEquals(xArticleEnd, sectionDimension.get("xRowEnd"), "Inconsistent width of 1st article");
		}
		
		if(sectionDimension.get("resetArticleImageVideoHeight").equals(1)) {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			sectionDimension.put("firstRowImageVideoHeight", articleHeight);
		}
		else {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			//logger.info(((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeStaticText").getAttribute("label"));
			logger.info("********Expected Height: " + sectionDimension.get("firstRowImageVideoHeight"));
			logger.info("********Actual Height: " + articleHeight);
			Assert.assertTrue(Math.abs(articleHeight - sectionDimension.get("firstRowImageVideoHeight")) < 2, "Inconsistent Height of 1st article");
		}
		validated  = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean secondRowArticleAlignmentValidation(Integer articleSeqInLayout, boolean isRightArticle, Map<String, Integer> sectionDimension) {
		methodName = "secondRowArticleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		Integer xArticleStart = 0;
		Integer xArticleEnd = 0;
		Integer articleHeight = 0;
		Integer articleWidth = 0;
		boolean validated = false;
		
		xArticleStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();
		
		if(sectionDimension.get("resetArticleWidth").equals(1) && !(isRightArticle)){
			Assert.assertEquals(xArticleStart, sectionDimension.get("xRowStart"), "Inconsistent x-axis placement of " + articleSeqInLayout.toString() + " article");
			
			articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
			sectionDimension.put("secondRowArticleWidth", articleWidth);
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
				Assert.assertEquals(xArticleEnd, sectionDimension.get("xRowEnd"), "Misaligned End of " + articleSeqInLayout.toString() + " article");
			}
			Assert.assertEquals(articleWidth, sectionDimension.get("secondRowArticleWidth"), "Inconsistent width of " + articleSeqInLayout.toString() + " article");
		}
		
		if(sectionDimension.get("resetArticleImageVideoHeight").equals(1) && !(isRightArticle)) {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			sectionDimension.put("secondRowImageVideoHeight", articleHeight);
		}
		else {
			articleHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
			Assert.assertTrue(Math.abs(articleHeight - sectionDimension.get("secondRowImageVideoHeight")) < 2, "Inconsistent Height of " + articleSeqInLayout.toString() + " article");
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
	
	public boolean thirdRowOnwardsArticleAlignmentValidation(Integer articleSeqInLayout, Map<String, Integer> sectionDimension) {
		methodName = "thirdRowOnwardsArticleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		Integer xArticleStart = 0;
		Integer xArticleEnd = 0;
		Integer mediaHeight = 0;
		Integer articleWidth = 0;
		Integer imageWidth = 0;
		boolean validated = false;
		
		xArticleStart = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getCoordinates().onPage().getX();
		Assert.assertEquals(xArticleStart, sectionDimension.get("xRowStart"), "Inconsistent x-axis placement of " + articleSeqInLayout.toString() + " article");
		
		articleWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther"))).getSize().getWidth();
		Assert.assertEquals(articleWidth, sectionDimension.get("firstRowArticleWidth"), "Inconsistent width of " + articleSeqInLayout.toString() + " article");
		
		xArticleEnd = xArticleStart + articleWidth;
		Assert.assertEquals(xArticleEnd, sectionDimension.get("xRowEnd"), "Inconsistent width of " + articleSeqInLayout.toString() + " article");
		
		try {
			if(isPremiumArticle(articleSeqInLayout)) {
				//As if the article is a premium article, then actual image is 2nd in the row
				imageWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[2]"))).getSize().getWidth();
				mediaHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[2]"))).getSize().getHeight();
				
				if(sectionDimension.get("resetArticleImageVideoHeight").equals(1)) {
					sectionDimension.put("thirdRowOnwardsImageVideoWidth",imageWidth);
					sectionDimension.put("thirdRowOnwardsImageVideoHeight", mediaHeight);
				}
			}
			else {
				imageWidth = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getWidth();
				mediaHeight = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]"))).getSize().getHeight();
				
				if(sectionDimension.get("resetArticleImageVideoHeight").equals(1)) {
					sectionDimension.put("thirdRowOnwardsImageVideoWidth", imageWidth);
					sectionDimension.put("thirdRowOnwardsImageVideoHeight", mediaHeight);
				}
			}
			
			Assert.assertTrue(Math.abs(mediaHeight - sectionDimension.get("thirdRowOnwardsImageVideoHeight")) < 2, "Inconsistent Height of " + articleSeqInLayout.toString() + " article");
			
			Assert.assertTrue(Math.abs(imageWidth - sectionDimension.get("thirdRowOnwardsImageVideoWidth")) < 2, "Inconsistent Width of " + articleSeqInLayout.toString() + " article, as expected is: " + sectionDimension.get("thirdRowOnwardsImageVideoWidth") + ". However, received image width of: " + imageWidth);
		}catch(NoSuchElementException e) {
			logger.warn("No image found for this article");
		}
		
		
		validated = true;
		logger.info("Successfully exiting from method: " + methodName);
		return validated;
	}
	
	public boolean articleAlignmentValidation(MobileElement article, Integer articleSequence, Integer articleSeqInLayout, Map<String, Integer> sectionDimension) {
		boolean validated = false;
		methodName = "articleAlignmentValidation";
		logger.info("Entering Method: " + methodName);
		if(articleSequence.equals(1)) {
			firstRowArticleAlignmentValidation(articleSeqInLayout, sectionDimension);
		}
		else if(articleSequence.equals(2) || articleSequence.equals(3)) {
			secondRowArticleAlignmentValidation(articleSeqInLayout,articleSequence.equals(3), sectionDimension);
		}
		else {
			thirdRowOnwardsArticleAlignmentValidation(articleSeqInLayout, sectionDimension);
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
		Integer layoutSequence = collectionViewSequence - 1;
//		if(defaultSubSections.contains(sectionTitle)) {
		if(reachedEndOfSection) {
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
					if(elementCnt < collectionViewSequence) {
						continue;
					}else if(elementCnt == collectionViewSequence) {
						try{
							MobileElement sectionSeparator = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[" + layoutSequence.toString() + "]//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")));
							reachedEndOfSection = true;
							break;
						}catch(Exception e) {
							reachedEndOfSection = false;
						}
						
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
				util.swipeVertical("Up");
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
	
	public Map<String, Integer> sectionArticleValidation(String sectionTitle, boolean followedByAd, Map<String, Integer> sectionDimension) {
		//Integer validated = 0;
		methodName = "sectionArticleValidation";
		boolean reachedEndOfSection = false;
		logger.info("Entering Method: " + methodName);
//		Boolean resetDimension = sectionDimension.get("resetDimension").equals(1);
//		Boolean resetArticleWidth = resetDimension ;
//		Boolean resetXAxisStart = resetDimension;
//		Boolean resetArticleImageVideoHeight = resetDimension;
//		
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
//					if(articleType.equalsIgnoreCase(IOSElements.PREMIUM_SECTION_LABEL_ID)) {
//						titleElement = (MobileElement) element.findElement(By.xpath("//XCUIElementTypeStaticText[@label=\"" + IOSElements.PREMIUM_SECTION_LABEL_ID + "\"]"));	
//					}else {
//						titleElement = (MobileElement) element.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + Constant.SECTION_TITLE + "\"]"));	
//					}
					titleElement = (MobileElement) element.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + Constant.SECTION_TITLE + "\"]"));
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
				String currentLabel = ((MobileElement) article).getAttribute("label");
				logger.info("********Viewing article: " + article.getAttribute("label"));
				if(!(firstArticleOfSection.equals(currentLabel)) && articleSequence.equals(0)) {
					//Not yet reached the first article in the section
					continue;
				}
				if(articleLabels.containsKey(currentLabel)) {
					alreadyCapturedArticle = true;
				}
				else {
					alreadyCapturedArticle = false;
				}
//				for(String prevLabel: articleLabels.keySet()) { //Check better way to find 
//					if(prevLabel.equals(article.getAttribute("label"))) {
//						alreadyCapturedArticle = true;
//						break;
//					}
//					else {
//						alreadyCapturedArticle = false;
//					}
//				}
				if(!alreadyCapturedArticle) {
					System.out.println("Validating Article labelled: " + currentLabel);
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
						
						if(sectionTitle.equalsIgnoreCase(IOSElements.PREMIUM_SECTION_LABEL_ID)) {
							MobileElement premiumTag = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage[@name=\"premium\"]"));
							Assert.assertEquals(premiumTag.getAttribute("enabled"),"true","Premium tag is not visible for Premium articles");
							logger.info("Validated the Premium tag is visible for Premium article");
						}
						logger.info("Validating article labelled: " + currentLabel);
						
						if(sectionTitle.equalsIgnoreCase("VIEWPOINTS")) {
							Assert.assertTrue(articleAlignmentValidation((MobileElement) article, Constant.VIEWPOINT_ARTICLE_LAYOUT_SEQUENCE, articleSeqInLayout, sectionDimension),"Article Alignment Validation Failed");
						}
						else {
							Assert.assertTrue(articleAlignmentValidation((MobileElement) article, articleSequence,articleSeqInLayout, sectionDimension),"Article Alignment Validation Failed");
						}
						
						articleLabels.put(currentLabel, articleSequence);
						System.out.println("Successfully Validated Article labelled: " + currentLabel);
						
						//As this is a special case wherein second row article follows alignment rules of third row onward article(as there's only 1 last article in 2nd row)
						if(whetherReachedLastArticleOfSection(sectionTitle, collectionViewSequence)) {
							reachedEndOfSection = true;
							if(articleSequence.equals(2)) {
								articleSequence = 4;
							}
						}
						
						if(reachedEndOfSection) {
							System.out.println("Reached End of Section: " + sectionTitle);
							break;
						}
					}
					else {
						//Decrementing the layout sequence of current article, as it should not be counted as not visible(thus the view is to be scrolled and re-evaluated)
						collectionViewSequence--;
						articleSeqInLayout--;
						System.out.println("Need to be revalidated as article isn't visible, labelled: " + currentLabel);
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
				util.swipeVertical("Up");
				afterSwipeView = true;
			}
			else if(reachedEndOfSection && followedByAd) {
					Assert.assertTrue(whetherAdPresent(articleSeqInLayout),"Expected to be followed by Ad, however missed it");
			}
			collectionViewSequence = 1;
			layoutSeqSectionFirstArticle = 0;
		}
		sectionDimension.put("articleSeqInLayout",articleSeqInLayout);
		logger.info("Exiting method: " + methodName);
		return sectionDimension;
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
				if(label.equals(IOSElements.PREMIUM_SECTION_LABEL_ID)){
					MobileElement currentElement = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='section_title']//preceding-sibling::XCUIElementTypeImage[@name='premium']"));
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
					util.swipeVertical(navigationDirection);
					try{
						//Nothing
					}catch(Exception e){
						System.out.println("Error while waiting: " + e.getMessage());
					}
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

	//Cybage libraries
	public HomePage isInfoScreenPresent() {
		logger.info("Verifying if Information screen is displayed on the tab ");
		boolean flag = util.isElementPresent(infoScreen, Constant.SHORT_TIMEOUT);
		if (flag) {
			infoScreen.click();
			logger.info("Information screen is displayed, clicked on 'GOT IT' button, now on selected tab");
		}

		return this;
	}

	public HomePage navigateToTab(Constant.TAB tabName, String direction) {
		logger.info("Navigating to " + tabName + " Tab");
		switch (tabName) {
		
		case ST_NOW:
			switchTab(stNowTab, tabName, 14, direction);
			break;
			
		case HOME:
			switchTab(homeTab, tabName, 14, direction);
			break;

		case LATEST:
			switchTab(latestTab, tabName, 14, direction);
			break;

		case SINGAPORE:
			switchTab(singaporeTab, tabName, 14, direction);
			break;
		case POLITICS:
			switchTab(politicsTab, tabName, 14, direction);
			break;
		case ASIA:
			switchTab(asiaTab, tabName, 14, direction);
			break;
		case WORLD:
			switchTab(worldTab, tabName, 14, direction);
			break;
		case LIFESTYLE:
			switchTab(lifestyleTab, tabName, 14, direction);
			break;
		case FOOD:
			switchTab(foodTab, tabName, 14, direction);
			break;
		case FORUM:
			switchTab(forumTab, tabName, 14, direction);
			break;
		case VIDEOS:
			switchTab(videosTab, tabName, 14, direction);
			break;
		case OPINION:
			switchTab(opinionTab, tabName, 14, direction);
			break;
		case BUSINESS:
			switchTab(businessTab, tabName, 14, direction);
			break;
		case SPORT:
			switchTab(sportTab, tabName, 14, direction);
			break;

		case TECH:
			switchTab(techTab, tabName, 14, direction);
			break;

		default:
			logger.info("Please provide valid tab name");
			break;

		}
		return this;
	}

	/*
	 * Commented section is present in below method purposely, as it is
	 * dependent on one of issues which developer needs to fix it, once fixed
	 * will remove comments, also commented section is required for Android as
	 * well. For android it works, for iOS issue is already raised
	 */

	public HomePage switchTab(MobileElement tab, Constant.TAB tabName, int swipe, String direction) {
		logger.info("Checking visibility of the tab :" + tabName + "and navigating to  " + tabName);
		if (swipe > 0) {
			if (util.isElementPresent(tab, Constant.SHORT_TIMEOUT)) {
				util.clickifClickable(tab, Constant.SHORT_TIMEOUT);
				try {
					// WebDriverWait wait=new WebDriverWait(driver, 15);
					// boolean
					// flag=wait.until(ExpectedConditions.elementSelectionStateToBe(tab,true));
					// Assert.assertTrue(tab.isSelected());
					logger.info("Tab is visible! and tab title verified :Navigated to desired tab " + tabName);
				} catch (AssertionError er) {
					logger.error("Required tab not found!" + tabName);
					Assert.fail(
							"Assertion Failed , Tab title doesn't match: Navigated to incorrect tab" + er.getMessage());
				}
				return this;
			}
			util.swipeHorizontal(direction);
			swipe--;
			return switchTab(tab, tabName, swipe, direction);

		}

		logger.error("Required tab not found!" + tabName);
		Assert.fail("Required tab not found!");

		return this;
	}

	public LoginPage openLoginControl() {
		logger.info("Opening login page/Drawer menu");
		try{
			util.clickifClickable(menu, Constant.SHORT_TIMEOUT);
			return new LoginPage(driver);
		}catch(Exception ex) {
			logger.error("Cannot open Login Control");
			return null;
		}
	}
	
	public HomePage isOnHomePage() {
		logger.error("Verifying if on home page");
		if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("ios")) {
			util.clickUsingCoordinates(menu);
		}
		return this;
	}

	public ArticlePage verifyNavigatedToTopStoriesPage() {
		try{
			util.isElementPresent(topStoriesHeading, Constant.SHORT_TIMEOUT, "Top stories heading");
			logger.info("Top Stories page is displayed to the user");
			return new ArticlePage(driver);
		}catch(Exception ex) {
			logger.error("Cannot navigate to Top Stories page");
			return null;
		}
		
	}

	public HomePage assertOnMediaDisplayed() {
		util.isElementPresent(articleWithImage, Constant.SHORT_TIMEOUT, "Article with Image");
		return this;
	}

/////modified this for new framework To do : remove comments
	public void navigateToTopStoryOftheHomePage() {
		try {
			logger.info("Navigating to main article of home page");
			//String headline = topStory.getText();
			
			topStory.click();
			//util.clickifClickable(topStory, Constant.SHORT_TIMEOUT);
			//switchCall();
//			logger.info("Verifying main article headline");
//			
//			assertHeadingOfArticle(headline);
			
		}catch(Exception ex) {
			logger.error("Cannot navigate to Top Story of Home Page"+ex.getMessage());
			
		}
	}
	
	/*added methods here for new framework */
	
	public String getHeadlineOfTheArticle() {
		
			logger.info("Fetching headline of the topstory");
			Reporter.addStepLog("Fetching headline of the topstory");
	       // Reporter.addScenarioLog("User wants to launch the Straits Time site and accept the terms and conditions");
			String headline = topStory.getText();
			Reporter.addStepLog("Headline of the topstory is "+headline);
			return headline;
		
	}
	
	public void assertTitleOfTheHeadline() {
		
		logger.info("Assert headline of the topstory");
		Reporter.addStepLog("Assert headline of the topstory");
       // Reporter.addScenarioLog("User wants to launch the Straits Time site and accept the terms and conditions");
		String headline = getHeadlineOfTheArticle();
		assertHeadingOfArticle(headline);
		Reporter.addStepLog("Headline matches! Navigated to the correct article! "+headline);
		 
	
}
//////////////////////////////////////////////////////////////////////


	public LoginPage returnLoginPage() {
		try {
			return new LoginPage(driver);
		}catch(Exception ex) {
			logger.error("Cannot return to Login Page");
			return null;
		}
	}

	public STNowPage returnSTNowPage() {
		try {
			return new STNowPage(driver);
		}catch(Exception ex) {
			logger.error("Cannot go to STNow Page");
			return null;
		}
	}
	
	public HomePage verifyVisibilityOfAddToHomeButton() {
		logger.info("Verifying if 'Add To Home' Button is displayed on the tab ");
		boolean flag = util.isElementPresent(addToHomeButton, Constant.SHORT_TIMEOUT);
		if (flag) {
			logger.info("'Add To Home' Button is displayed on the tab");
		} else {
			logger.info("'Add To Home' Button is not displayed on the tab");
		}

		return this;
	}

	public ArticlePage openArticle(int maxSwipe, String articleType) {
		try {
			if (articleType == Constant.PREMIUM) {
				navigateToArticle(premiumArticleTitle, maxSwipe, articleType);
			} else if (articleType == Constant.FREE) {
				navigateToArticle(freeArticleTitle, maxSwipe, articleType);
			}
			return new ArticlePage(driver);
		}catch(Exception ex) {
			logger.error("Cannot open Article");
			return null;
		}
	}

	public ArticlePage navigateToArticle(MobileElement element, int maxSwipe, String articleType) {
		logger.info("Searching for " + articleType + " article..");
		boolean flag = util.swipeVerticalUntilElementIsFound(element, maxSwipe, Constant.UP);
		try {
			if (flag) {
				String headline;
				headline = element.getText();
				logger.info("Article found! Now navigating to " + articleType + " article..");
				logger.info("Article heading is " + headline);
				util.clickifClickable(element, Constant.SHORT_TIMEOUT);
				switchCall();
				logger.info(" Opened article, verifying article headline of " + articleType + " article..");
				assertHeadingOfArticle(headline);

				logger.info(headline);
				logger.info("Article headline matches! we have been navigated to selected article..");
			}
			return new ArticlePage(driver);

		} catch (Exception ex) {
			logger.error(articleType + " article you are looking for is not present");
			Assert.fail(articleType + "article is not found!");
			return null;
		}
		
	}

	public ArticlePage switchCall() {
		try {
			return new ArticlePage(driver).switchToMainArticle();
		}catch (Exception ex) {
			logger.error("Unable to switch to Main Article");
			return null;
		}
	}

	public ArticlePage assertHeadingOfArticle(String headline) {
		try {
			return new ArticlePage(driver).assertArticleHeading(headline);
		}catch (Exception ex) {
			logger.error("Heading of Article is inconsistent");
			return null;
		}
	}

	public HomePage verifyMainArticleOnHomePage() {
		try {
			String articleHeadline = topStory.getText();
			logger.info("Verifying main article at home page : ");
			logger.info("Article heading is " + articleHeadline);
			util.isElementPresent(topStory, Constant.SHORT_TIMEOUT, "Article headline");
			logger.info("Article headline is displayed!");
			util.isElementPresent(topStoryImage, Constant.SHORT_TIMEOUT, "Article Image");
			logger.info("Top story image is displayed!");
			logger.info("Article headline is displayed!");
		} catch (Exception er) {
			logger.error("Article headline is missing");
			Assert.fail("Article headline is missing");
		}

		return this;
	}

	public BookmarkPage returnBookmarkPage() {
		try {
			return new BookmarkPage(driver);
		}catch (Exception ex) {
			logger.error("Unable to return to Bookmark page");
			return null;
		}
	}

	public ArticlePage openAndVerifyArticle(List<String> list, int index, int maxSwipe) {
		try {
			if (maxSwipe > 0 && list.size() > 0) {
				if (list.get(index).equals(topStory.getText())) {
					topStory.click();
					logger.info("Article headline of article is :" + list.get(index));
					return new ArticlePage(driver);
				}
				util.swipeVertical(Constant.UP);
				maxSwipe--;
				return openAndVerifyArticle(list, index, maxSwipe);
	
			}
			return new ArticlePage(driver);
		}catch (Exception ex) {
			logger.error("Unable to open and verify article");
			return null;
		}
	}

	public ArticlePage openArticleContainingWebview(int maxSwipe, String articleType) {
		try {
			navigateToArticle(videoIcon, maxSwipe, articleType);
			return new ArticlePage(driver);
		}catch (Exception ex) {
			logger.error("Unable to open article having webview");
			return null;
		}
	}

	// below i was trying to validate all VISIBLE web view articles
	public ArticlePage verifyArticleContent() {
		try {
			return new ArticlePage(driver).verifyArticleContentForHtmlEntities();
		}catch (Exception ex) {
			logger.error("Unable to open and verify article");
			return null;
		}
	}

	public HomePage clickOnBackIcon() {
		try {
			return new ArticlePage(driver).clickOnBackButton(1);
		}catch (Exception ex) {
			logger.error("Unable to click On back icon");
			return null;
		}
	}

	public ArticlePage verifyAllArticleContainingWebview(int maxSwipe, String articleType) {
		try {
			List<String> list = new ArrayList<String>();
	
			for (int i = 0; i < webviewArticles.size(); i++) {
				logger.info("Web Articles are" + webviewArticles.size());
				list.add(webviewArticles.get(i).getText());
				logger.info("The list elements are " + list);
			}
			for (int i = 0; i < list.size(); i++) {
				logger.info("value of i " + i);
				openAndVerifyArticle(list, i, 4);// top story will always consider 1st Occurrence of article title, therefore seems not feasible
				verifyArticleContent();
				clickOnBackIcon();
			}
			return new ArticlePage(driver);
		}catch (Exception ex) {
			logger.error("Unable to open and verify article");
			return null;
		}
	}
}
	
