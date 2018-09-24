package com.sph.pageObjects.mobile;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SubscribePage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(PrintEditionPage.class);
	private WebDriver driver;

	public SubscribePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
