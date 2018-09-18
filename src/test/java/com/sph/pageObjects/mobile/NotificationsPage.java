package com.sph.pageObjects.mobile;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;


public class NotificationsPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(HomePage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
	
	public NotificationsPage(WebDriver driver) {
		this.driver = driver;
		this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
	}
	
	public void waitForNotificationView() {
		methodName = "waitForNotificationView";
		logger.info("Entering Method: " + methodName);
//		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"“Straits Times” Would Like to Send You Notifications\"]"))));
		logger.info("Exiting Method: " + methodName);
	}
	
	public boolean basicNotificationPopUpValidation() {
		methodName = "basicNotificationPopUpValidation";
		logger.info("Entering Method: " + methodName);
		MobileElement title = null; 
		MobileElement message = null ; 
		MobileElement denyNotification = null; 
		MobileElement allowNotification = null; 
		
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			title = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"“Straits Times” Would Like to Send You Notifications\"]"));
			message = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Notifications may include alerts, sounds, and icon badges. These can be configured in Settings.\"]"));
			denyNotification = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Don’t Allow\"]"));
			allowNotification = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"Allow\"]"));
				
			if(title.equals(null)) {
				logger.error("Notification Permission Pop-Up is not showing up expected title: \n[Straits Times Would Like to Send You Notifications]");
				return false;
			}
			if(message.equals(null)) {
				logger.error("Notification Permission Pop-Up is not showing up expected Message: \n[Notifications may include alerts, sounds, and icon badges. These can be configured in Settings.]");
				return false;
			}
			if(denyNotification.equals(null)) {
				logger.error("Notification Permission Pop-Up is not showing Deny Button: \n[\"Don't Allow\"]");
				return false;
			}
			if(allowNotification.equals(null)) {
				logger.error("Notification Permission Pop-Up is not showing Permit Button: \n[\"Allow\"]");
				return false;
			}	
			logger.info("Notification Permission Pop-Up is consistent with expected view(title, content and buttons)");
		}
		else{
			logger.info("No default Permission needed immediately after installation on " + capabilities.getCapability("platformName").toString());
		}
		
		logger.info("Exiting Method: " + methodName);
		return true;
	}
	
	public void acceptNotificationAfterInstall() {
		methodName = "acceptNotificationAfterInstall";
		logger.info("Entering Method: " + methodName);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			driver.switchTo().alert().accept();
//			IOSElement accept = (IOSElement) driver.findElement(By.xpath("//*[@text='Allow' and @id='Allow' and @class='UIAButton' and @onScreen='true']"));
//			accept.click();
			logger.info("Allowed the Permissions for ST App Default Notification");
		}
		else{
			logger.info("No default Permission needed immediately after installation on " + capabilities.getCapability("platformName").toString());
		}
		logger.info("Exiting Method: " + methodName);
	}
	
	public void declineNotificationAfterInstall() {
		methodName = "declineNotificationAfterInstall";
		logger.info("Entering Method: " + methodName);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			driver.switchTo().alert().dismiss();
			logger.info("Declined the Permissions for ST App Default Notification");
		}
		else{
			logger.info("No default Permission needed immediately after installation on " + capabilities.getCapability("platformName").toString());
		}
		logger.info("Exiting Method: " + methodName);
	}
}
