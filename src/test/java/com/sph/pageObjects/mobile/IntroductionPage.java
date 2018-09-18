package com.sph.pageObjects.mobile;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.DeviceActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class IntroductionPage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(IntroductionPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
	
	public IntroductionPage(WebDriver driver) {
		this.driver = driver;
//        this.wait = new WebDriverWait(this.driver, 30);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public Boolean firstIntroPageUXValidation() {
		methodName = "firstIntroPageUXValidation";
		logger.info("Entering Method: " + methodName);
		MobileElement onBoardingLogo = null;
		MobileElement title = null;
		MobileElement welcomeText = null;
		MobileElement logoText = null;
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			onBoardingLogo = (MobileElement) driver.findElement(By.id("on_boarding_st_logo"));
			title = (MobileElement) driver.findElement(By.id("Welcome"));
			welcomeText = (MobileElement) driver.findElement(By.id("Hello! Here are some features of the ST app you might like."));
			logoText = (MobileElement) driver.findElement(By.id("sph-logo"));
			
			if(onBoardingLogo.equals(null)) {
				logger.error("First Intro Page: Missing expected On Boarding Logo");
				return false;
			}
			if(title.equals(null)) {
				logger.error("First Intro Page: Missing expected title");
				return false;
			}
			if(welcomeText.equals(null)) {
				logger.error("First Intro Page: Missing expected Welcome Text");
				return false;
			}
			if(logoText.equals(null)) {
				logger.error("First Intro Page: Missing expected Logo text");
				return false;
			}	
			logger.info("First Intro Page: consistent with expected view(title, logo, text and views)");		
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			title = (MobileElement) driver.findElement(By.id("Welcome"));
			welcomeText = (MobileElement) driver.findElement(By.id("Hello!\nHere are some features of the ST app\nyou might like."));
			if(title.equals(null)) {
				logger.error("First Intro Page: Missing expected title");
				return false;
			}
			if(welcomeText.equals(null)) {
				logger.error("First Intro Page: Missing expected Welcome Text");
				return false;
			}
		}
		logger.info("Exiting Method: " + methodName);
		return true;
	}
	
	public Boolean secondIntroPageUXValidation() {
		methodName = "secondIntroPageUXValidation";
		logger.info("Entering Method: " + methodName);
		
		MobileElement title = null;
		MobileElement introPurpose = null;
		MobileElement introText = null;
		MobileElement navigationBar = null;
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			navigationBar = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypePageIndicator"));
			title = (MobileElement) driver.findElement(By.id("intro-1"));
			introPurpose = (MobileElement) driver.findElement(By.id("Create your own home page"));
			introText = (MobileElement) driver.findElement(By.id("Read what matters to you first. Add up to 4 sections of content you like and they will appear first."));
			
			if(navigationBar.equals(null)) {
				logger.error("Second Intro Page: Navigation Bar/Indicator is missing");
				return false;
			}
			if(title.equals(null)) {
				logger.error("Second Intro Page: title is missing [intro-1]");
				return false;
			}
			if(introPurpose.equals(null)) {
				logger.error("Second Intro Page: Purpose is missing/unexpected");
				return false;
			}
			if(introText.equals(null)) {
				logger.error("Second Intro Page: Intro text is missing/unexpected");
				return false;
			}	
			logger.info("Second Intro Page is consistent with expected view(title, Purpose, text and Navigation views)");		
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			navigationBar = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypePageIndicator"));
			title = (MobileElement) driver.findElement(By.id("intro-1"));
			introPurpose = (MobileElement) driver.findElement(By.id("Create your own home page"));
			//introText = driver.("Read what matters to you first. Add up to 4 sections of content you like and they will appear first.");
		}
		
		logger.info("Exiting Method: " + methodName);
		return true;
	}
	
	public void skipIntro() {
		methodName = "skipIntro";
		logger.info("Entering Method: " + methodName);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			//find an element by id
		    MobileElement skip = (MobileElement) driver.findElement(By.id("skip"));
		    skip.click();
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			//find an element by id
			MobileElement skip = (MobileElement) driver.findElement(By.name("skip"));
		    skip.click();
		}
		logger.info("Skipped the Introduction Page");
		logger.info("Exiting Method: " + methodName);
	}
}
