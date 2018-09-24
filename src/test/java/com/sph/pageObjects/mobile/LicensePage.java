package com.sph.pageObjects.mobile;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.DriverManager;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LicensePage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(IntroductionPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;
	
	public LicensePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 30);
		util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(accessibility = "Log Out")
	@AndroidFindBy(id = "tv_sign_in")
	private MobileElement logOut;
	
	@iOSXCUITFindBy(id = "accept")
	@AndroidFindBy(id = "btn_tnc_ok")
	private MobileElement accept;
	
	@iOSXCUITFindBy(id = "decline")
	@AndroidFindBy(id = "btn_tnc_cancel")
	private MobileElement decline;
	
	public boolean basicLicenseViewValidationAtEntry() {
		methodName = "basicLicenseViewValidationAtEntry";
		logger.info("Entering Method: " + methodName);
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			MobileElement title = null;
			MobileElement bannerText = null;
			MobileElement headerText = null;
			title = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"App For iOS - Singapore Press Holdings\"]"));
			bannerText = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeImage[@name=\"SPH\"]"));
			headerText = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"TERMS OF USE FOR APPS (IOS)\"]"));
		
			if(title.equals(null)) {
				logger.error("License Page for ST App is not showing up expected title: \n[SPH]");
				return false;
			}
			if(bannerText.equals(null)) {
				logger.error("License Page is not showing App Banner text: \n[SPH]");
				return false;
			}
			if(headerText.equals(null)) {
				logger.error("License Page is not showing header text: \n[TERMS OF USE FOR APPS (IOS)]");
				return false;
			}	
			logger.info("License Page is consistent with expected view(title, logo, text and views)");		
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			util.setWebViewContext();
			try {
				String title = driver.getTitle();
				String url = driver.getCurrentUrl();
				
				if(!(title.equals("App For Non-iOS - Singapore Press Holdings"))) {
					logger.error("Title is missing");
				}
				if(!(url.equals("http://sph.com.sg/terms-and-conditions/app-for-non-ios/"))) {
					logger.error("The url accessed for license loading is different from expected");
				}
			}finally {
				util.setNativeAppContext();
			}
		}
		
		logger.info("Exiting Method: " + methodName);
		return licenseAcceptDeclineButtonsValidation();
	}
	
	public boolean licenseAcceptDeclineButtonsValidation() {
		methodName = "licenseAcceptDeclineButtonsValidation";
		logger.info("Entering Method: " + methodName);
		MobileElement decline = null;
		MobileElement accept = null;
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			
			if(decline.getAttribute("enabled").equalsIgnoreCase("false") || decline.getAttribute("visible").equalsIgnoreCase("false")) {
				logger.error("License Decline Button is disabled or invisible");
				return false;
			}
			if(accept.getAttribute("enabled").equalsIgnoreCase("false") || accept.getAttribute("visible").equalsIgnoreCase("false")) {
				logger.error("License Accept Button is disabled or invisible");
				return false;
			}
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			
			if(!decline.getText().equals("Close App") || !decline.isEnabled() || !decline.isDisplayed()) {
				logger.error("License Decline Button is disabled or invisible");
				return false;
			}
			if(!accept.getText().equals("I Agree") || !accept.isEnabled() || !accept.isDisplayed()) {
				logger.error("License Accept Button is disabled or invisible");
				return false;
			}
		}
		logger.info("License Accept and Decline Buttons are visible and enabled");
		logger.info("Exiting Method: " + methodName);
		return true;
	}
	
	public void scrollValidation() {
		methodName = "scrollValidation";
		logger.info("Entering Method: " + methodName);
		DeviceActions utils = null;

		utils = new DeviceActions(driver);
		driver.getPageSource(); 
		
		//Validate the upward scroll
		utils.swipeVertical("Up");
		
		//Validate the Upward Arrow button to take the control back to License's first page
		utils.goToFirstPage();
		
		//Validate the Scroll action is able to navigate till end of the license
		while(whetherReachedEndOfLicense()) {
			utils.swipeVertical("Up");
			logger.info("Keep scrolling until the End of Agreement is reached");
			licenseAcceptDeclineButtonsValidation();
		}
		logger.info("Navigated the License till end");
		
		//Validate the downward scroll
		utils.swipeVertical("Down");
		logger.info("Exiting Method: " + methodName);
	}
	
	public void acceptAgreement() {
		methodName = "acceptAgreement";
		logger.info("Entering Method: " + methodName);
		util.clickifClickable(accept, Constant.LONG_TIMEOUT);
		logger.info("Accepted the License Agreement");
		logger.info("Exiting Method: " + methodName);
		DriverManager.setWebDriver(driver);
	}
	
	public void declineAgreement() {
		methodName = "declineAgreement";
		logger.info("Entering Method: " + methodName);
		util.clickifClickable(decline, Constant.LONG_TIMEOUT);
		logger.info("Declined the License Agreement");
		logger.info("Exiting Method: " + methodName);
	}

	public void acknowledgeLicenseAlert() {
		/*
		 * License Alert is only shown on iOS, as Android handles it by closing the app
		 */
		methodName = "acknowledgeLicenseAlert";
		logger.info("Entering Method: " + methodName);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			driver.findElement(By.id("Ok")).click();
			logger.info("Acknowledged the alert after declining the license");
		}
		logger.info("Exiting Method: " + methodName);
	}
	
	public boolean licenseAlertUXValidation() {
		/*
		 * License Alert is only shown on iOS, as Android handles it by closing the app
		 */
		methodName = "licenseAlertUXValidation";
		logger.info("Entering Method: " + methodName);
		
		MobileElement title = null;
		MobileElement titleText = null;
		MobileElement message = null;
		MobileElement acceptButton = null;
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			title = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeAlert[@name=\"Terms of Use\"]"));
			titleText = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Terms of Use\"]"));
			message = (MobileElement) driver.findElement(By.id("Please accept SPH terms of use to continue using the application"));
			acceptButton = (MobileElement) driver.findElement(By.id("Ok"));
			
			if(title.equals(null)) {
				logger.error("License Decline Alert: Missing expected title");
			}
			if(titleText.equals(null)) {
				logger.error("License Decline Alert: Missing expected title text");
			}
			if(message.equals(null)) {
				logger.error("License Decline Alert: Missing expected message");
			}
			if(acceptButton.equals(null) || !(acceptButton.getAttribute("enabled").equalsIgnoreCase("false"))){
				logger.error("License Decline Alert: Missing/disabled acknowledge button");
			}
			logger.info("Acknowledged the alert to accept the license");
		}
		
		logger.info("Exiting Method: " + methodName);
		return true;
	}

	public void waitForLicenseView() {
		methodName = "waitForLicenseView";
		logger.info("Entering Method: " + methodName);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("btn_tnc_cancel")));
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeButton[1]")));
		}
		logger.info("Exiting Method: " + methodName);
	}
	
	public boolean whetherReachedEndOfLicense() {
		methodName = "whetherReachedEndOfLicense";
		logger.info("Entering Method: " + methodName);
		boolean endOfLicense = false;
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			endOfLicense = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"(Last updated on 5 September 2011)\"]"))).getAttribute("visible").equalsIgnoreCase("false");
			return endOfLicense;
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			util.setWebViewContext();
			try{
				endOfLicense = ((MobileElement) driver.findElement(By.xpath("//td[text()='Copyright © 2017 Singapore Press Holdings Ltd. Co.']"))).isDisplayed();
		    }catch (Exception e) {
		        logger.info("End of License not reached!");
		    }finally{
				util.setNativeAppContext();
		    }
		}
		logger.info("Exiting Method: " + methodName);
		return endOfLicense;
	}
}
