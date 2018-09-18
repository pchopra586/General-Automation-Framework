package com.sph.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.pageObjects.mobile.AccountPage;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.IntroductionPage;
import com.sph.pageObjects.mobile.LicensePage;
import com.sph.pageObjects.mobile.NotificationsPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class GenericNavigator{
	private WebDriver driver;
    private WebDriverWait wait;
	
	private String methodName = null;
	private Capabilities capabilities;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(GenericNavigator.class);
    
    @iOSXCUITFindBy(accessibility = IOSElements.CLOSE_INTERSTITIAL_AD_ID)
    @AndroidFindBy(className = AndroidElements.CLOSE_AD)
    private MobileElement close_ad;
	
	public GenericNavigator(WebDriver driver) {
		this.driver = driver;
		this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public boolean preConfigured() {
		methodName = "preConfigured";
		logger.info("Entering Method: " + methodName);
		boolean isPreConfigured = false;
		HomePage home = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		isPreConfigured = home.onHomePage();
		
		logger.info("Exiting Method" + methodName);
		return isPreConfigured;
	}
	
	public boolean completeBasicInstallConfig() {
		methodName = "completeBasicInstallConfig";
		logger.info("Entering Method: " + methodName);
		boolean completedBasicInstallConfig = false;
		LicensePage license;
		IntroductionPage intro;
		NotificationsPage notify;
		DeviceActions util;
		
		notify = new NotificationsPage(driver);
		util = new DeviceActions(driver);		
		intro = new IntroductionPage(driver);
		license = new LicensePage(driver);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		notify.acceptNotificationAfterInstall();
		license.acceptAgreement();
		
		util.swipeHorizontal("left");
		intro.skipIntro();
		
		driver.getPageSource();
		
		//for now the Ad is not shown on iOS, hence, the check is only for Android
		try {
            if (close_ad.isDisplayed()) {
                close_ad.click();
            }
        }
        catch (Exception ex)
        {
            System.out.printf("Interstitial Ad Not Present");
        }
		
		completedBasicInstallConfig = true;
		logger.info("Exiting Method: " + methodName);
		return completedBasicInstallConfig;
	}
}
