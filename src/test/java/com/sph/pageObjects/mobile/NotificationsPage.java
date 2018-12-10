package com.sph.pageObjects.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.MobileElement;


public class NotificationsPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
	
	public NotificationsPage(WebDriver driver) {
		this.driver = driver;
		this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
	}
	
	public void waitForNotificationView() {
		methodName = "waitForNotificationView";
		log.info("Entering Method: " + methodName);
//		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"“Straits Times” Would Like to Send You Notifications\"]"))));
		log.info("Exiting Method: " + methodName);
	}
	
	public boolean basicNotificationPopUpValidation() {
		methodName = "basicNotificationPopUpValidation";
		log.info("Entering Method: " + methodName);
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
				log.error("Notification Permission Pop-Up is not showing up expected title: \n[Straits Times Would Like to Send You Notifications]");
				return false;
			}
			if(message.equals(null)) {
				log.error("Notification Permission Pop-Up is not showing up expected Message: \n[Notifications may include alerts, sounds, and icon badges. These can be configured in Settings.]");
				return false;
			}
			if(denyNotification.equals(null)) {
				log.error("Notification Permission Pop-Up is not showing Deny Button: \n[\"Don't Allow\"]");
				return false;
			}
			if(allowNotification.equals(null)) {
				log.error("Notification Permission Pop-Up is not showing Permit Button: \n[\"Allow\"]");
				return false;
			}	
			log.info("Notification Permission Pop-Up is consistent with expected view(title, content and buttons)");
		}
		else{
			log.info("No default Permission needed immediately after installation on " + capabilities.getCapability("platformName").toString());
		}
		
		log.info("Exiting Method: " + methodName);
		return true;
	}
	
	public void acceptNotificationAfterInstall() {
		methodName = "acceptNotificationAfterInstall";
		log.info("Entering Method: " + methodName);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			driver.switchTo().alert().accept();
			log.info("Allowed the Permissions for ST App Default Notification");
		}
		else{
			log.info("No default Permission needed immediately after installation on " + capabilities.getCapability("platformName").toString());
		}
		log.info("Exiting Method: " + methodName);
	}
	
	public void declineNotificationAfterInstall() {
		methodName = "declineNotificationAfterInstall";
		log.info("Entering Method: " + methodName);
		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			driver.switchTo().alert().dismiss();
			log.info("Declined the Permissions for ST App Default Notification");
		}
		else{
			log.info("No default Permission needed immediately after installation on " + capabilities.getCapability("platformName").toString());
		}
		log.info("Exiting Method: " + methodName);
	}
}
