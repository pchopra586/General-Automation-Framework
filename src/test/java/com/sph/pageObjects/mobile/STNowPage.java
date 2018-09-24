package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class STNowPage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(AccountPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;

	public STNowPage(WebDriver driver) throws MalformedURLException {
		this.util = util;
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeLink[@name='LOAD MORE']")
	private MobileElement loadMoreLink;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeLink[@name='Recap']")
	private MobileElement recapLink;

	public void OpenRecapLink() {
		do{
			logger.info("Looking for load more link...");
		boolean flag = util.swipeVerticalUntilElementIsFound(loadMoreLink, 10, Constant.UP);
		if(flag){
		logger.info("Clicking on load more link...");
		util.clickUsingCoordinates(loadMoreLink);
		}
		}while(!(util.isElementPresent(recapLink, Constant.SHORT_TIMEOUT)));
		logger.info("clicking on recaplink");
		util.clickUsingCoordinates(recapLink);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}