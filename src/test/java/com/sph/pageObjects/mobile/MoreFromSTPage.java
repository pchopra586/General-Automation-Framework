package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MoreFromSTPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(MoreFromSTPage.class);
	private WebDriver driver;

	public MoreFromSTPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
