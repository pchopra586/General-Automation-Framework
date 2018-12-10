package com.sph.pageObjects.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;

import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SubscribePage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(PrintEditionPage.class);
	private WebDriver driver;

	public SubscribePage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
