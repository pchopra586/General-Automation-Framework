package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MoreFromSTPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(MoreFromSTPage.class);
	WebDriver driver;

	public MoreFromSTPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
