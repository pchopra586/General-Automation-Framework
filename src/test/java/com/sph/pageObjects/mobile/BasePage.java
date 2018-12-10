package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.Constant.TAB;
import com.sph.utilities.GenericNavigator;
import com.sph.utilities.IOSElements;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class BasePage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(BasePage.class);
	private WebDriver driver;
    WebDriverWait wait;
    Capabilities capabilities;
    private DeviceActions util;

    public BasePage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @iOSXCUITFindBy(accessibility = IOSElements.ST_NOW_TAB_ID)
	private MobileElement stNowTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.HOME_TAB_ID)
	private MobileElement homeTab;

    @iOSXCUITFindBy(accessibility = IOSElements.LATEST_TAB_ID)
	private MobileElement latestTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.SINGAPORE_TAB_ID)
	private MobileElement singaporeTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.POLITICS_TAB_ID)
	private MobileElement politicsTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.ASIA_TAB_ID)
	private MobileElement asiaTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.WORLD_TAB_ID)
	private MobileElement worldTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.LIFESTYLE_TAB_ID)
	private MobileElement lifestyleTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.FOOD_TAB_ID)
	private MobileElement foodTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.FORUM_TAB_ID)
	private MobileElement forumTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.VIDEOS_TAB_ID)
	private MobileElement videosTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.OPINION_TAB_ID)
	private MobileElement opinionTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.BUSINESS_TAB_ID)
	private MobileElement businessTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.SPORT_TAB_ID)
	private MobileElement sportTab;
    
    @iOSXCUITFindBy(accessibility = IOSElements.TECH_TAB_ID)
	private MobileElement techTab;
    
    public MobileDriver gotoTab(TAB tab) {
		methodName = "gotoTab";
		log.info("Entering Method: " + methodName);
		
		try {
			switch(tab) {
			case ST_NOW: 
				util.clickifClickable(stNowTab, Constant.LONG_TIMEOUT);
				break;
			case HOME: 
				util.clickifClickable(homeTab, Constant.LONG_TIMEOUT);
				break;
			case LATEST: 
				util.clickifClickable(latestTab, Constant.LONG_TIMEOUT);
				break;
			case SINGAPORE: 
				util.clickifClickable(singaporeTab, Constant.LONG_TIMEOUT);
				break;
			case POLITICS: 
				util.clickifClickable(politicsTab, Constant.LONG_TIMEOUT);
				break;
			case ASIA: 
				util.clickifClickable(asiaTab, Constant.LONG_TIMEOUT);
				break;
			case WORLD: 
				util.clickifClickable(worldTab, Constant.LONG_TIMEOUT);
				break;
			case LIFESTYLE: 
				util.clickifClickable(lifestyleTab, Constant.LONG_TIMEOUT);
				break;
			case FOOD: 
				util.clickifClickable(foodTab, Constant.LONG_TIMEOUT);
				break;
			case FORUM: 
				util.clickifClickable(forumTab, Constant.LONG_TIMEOUT);
				break;
			case VIDEOS: 
				util.clickifClickable(videosTab, Constant.LONG_TIMEOUT);
				break;
			case OPINION: 
				util.clickifClickable(opinionTab, Constant.LONG_TIMEOUT);
				break;
			case BUSINESS: 
				util.clickifClickable(businessTab, Constant.LONG_TIMEOUT);
				break;
			case SPORT: 
				util.clickifClickable(sportTab, Constant.LONG_TIMEOUT);
				break;
			case TECH: 
				util.clickifClickable(techTab, Constant.LONG_TIMEOUT);
				break;
			default:
				log.error("Please provide valid Tab Title");
				break;	
			}
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
		}
		
		return (MobileDriver) driver;
    }
}
