package com.sph.pageObjects.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import com.sph.driverFactory.DriverManager;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LicensePage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(IntroductionPage.class);
	private WebDriver driver;
    WebDriverWait wait;
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
		log.info("Entering Method: " + methodName);
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			MobileElement title = null;
			MobileElement bannerText = null;
			MobileElement headerText = null;
			title = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"App For iOS - Singapore Press Holdings\"]"));
			bannerText = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeImage[@name=\"SPH\"]"));
			headerText = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"TERMS OF USE FOR APPS (IOS)\"]"));
		
			if(title.equals(null)) {
				log.error("License Page for ST App is not showing up expected title: \n[SPH]");
				return false;
			}
			if(bannerText.equals(null)) {
				log.error("License Page is not showing App Banner text: \n[SPH]");
				return false;
			}
			if(headerText.equals(null)) {
				log.error("License Page is not showing header text: \n[TERMS OF USE FOR APPS (IOS)]");
				return false;
			}	
			log.info("License Page is consistent with expected view(title, logo, text and views)");		
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			util.setWebViewContext();
			try {
				String title = driver.getTitle();
				String url = driver.getCurrentUrl();
				
				if(!(title.equals("App For Non-iOS - Singapore Press Holdings"))) {
					log.error("Title is missing");
				}
				if(!(url.equals("http://sph.com.sg/terms-and-conditions/app-for-non-ios/"))) {
					log.error("The url accessed for license loading is different from expected");
				}
			}finally {
				util.setNativeAppContext();
			}
		}
		
		log.info("Exiting Method: " + methodName);
		return licenseAcceptDeclineButtonsValidation();
	}
	
	public boolean licenseAcceptDeclineButtonsValidation() {
		methodName = "licenseAcceptDeclineButtonsValidation";
		log.info("Entering Method: " + methodName);
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			
			if(decline.getAttribute("enabled").equalsIgnoreCase("false") || decline.getAttribute("visible").equalsIgnoreCase("false")) {
				log.error("License Decline Button is disabled or invisible");
				return false;
			}
			if(accept.getAttribute("enabled").equalsIgnoreCase("false") || accept.getAttribute("visible").equalsIgnoreCase("false")) {
				log.error("License Accept Button is disabled or invisible");
				return false;
			}
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			
			if(!decline.getText().equals("Close App") || !decline.isEnabled() || !decline.isDisplayed()) {
				log.error("License Decline Button is disabled or invisible");
				return false;
			}
			if(!accept.getText().equals("I Agree") || !accept.isEnabled() || !accept.isDisplayed()) {
				log.error("License Accept Button is disabled or invisible");
				return false;
			}
		}
		log.info("License Accept and Decline Buttons are visible and enabled");
		log.info("Exiting Method: " + methodName);
		return true;
	}
	
	public void scrollValidation() {
		methodName = "scrollValidation";
		log.info("Entering Method: " + methodName);
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
			log.info("Keep scrolling until the End of Agreement is reached");
			licenseAcceptDeclineButtonsValidation();
		}
		log.info("Navigated the License till end");
		
		//Validate the downward scroll
		utils.swipeVertical("Down");
		log.info("Exiting Method: " + methodName);
	}
	
	public void acceptAgreement() {
		methodName = "acceptAgreement";
		log.info("Entering Method: " + methodName);
		util.clickifClickable(accept, Constant.LONG_TIMEOUT);
		log.info("Accepted the License Agreement");
		log.info("Exiting Method: " + methodName);
		DriverManager.setWebDriver(driver);
	}
	
	public void declineAgreement() {
		methodName = "declineAgreement";
		log.info("Entering Method: " + methodName);
		util.clickifClickable(decline, Constant.LONG_TIMEOUT);
		log.info("Declined the License Agreement");
		log.info("Exiting Method: " + methodName);
	}

	public void acknowledgeLicenseAlert() {
		/*
		 * License Alert is only shown on iOS, as Android handles it by closing the app
		 */
		methodName = "acknowledgeLicenseAlert";
		log.info("Entering Method: " + methodName);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			driver.findElement(By.id("Ok")).click();
			log.info("Acknowledged the alert after declining the license");
		}
		log.info("Exiting Method: " + methodName);
	}
	
	public boolean licenseAlertUXValidation() {
		/*
		 * License Alert is only shown on iOS, as Android handles it by closing the app
		 */
		methodName = "licenseAlertUXValidation";
		log.info("Entering Method: " + methodName);
		
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
				log.error("License Decline Alert: Missing expected title");
			}
			if(titleText.equals(null)) {
				log.error("License Decline Alert: Missing expected title text");
			}
			if(message.equals(null)) {
				log.error("License Decline Alert: Missing expected message");
			}
			if(acceptButton.equals(null) || !(acceptButton.getAttribute("enabled").equalsIgnoreCase("false"))){
				log.error("License Decline Alert: Missing/disabled acknowledge button");
			}
			log.info("Acknowledged the alert to accept the license");
		}
		
		log.info("Exiting Method: " + methodName);
		return true;
	}

	public void waitForLicenseView() {
		methodName = "waitForLicenseView";
		log.info("Entering Method: " + methodName);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("btn_tnc_cancel")));
		}
		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//XCUIElementTypeButton[1]")));
		}
		log.info("Exiting Method: " + methodName);
	}
	
	public boolean whetherReachedEndOfLicense() {
		methodName = "whetherReachedEndOfLicense";
		log.info("Entering Method: " + methodName);
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
		        log.info("End of License not reached!");
		    }finally{
				util.setNativeAppContext();
		    }
		}
		log.info("Exiting Method: " + methodName);
		return endOfLicense;
	}
}
