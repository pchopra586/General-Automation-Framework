package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.IOSElements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ArticleDetailPage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(ArticleDetailPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;

    public ArticleDetailPage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"article_title\"])[1]")
	private MobileElement articleTitle;
    
    @iOSXCUITFindBy(id = IOSElements.BACK_BTN_ID)
	private MobileElement backToPreviousView;
    
    public boolean verifyArticleTitle(String expectedArticleTitle) {
		methodName = "verifyArticleTitle";
		logger.info("Entering Method: " + methodName);
		Boolean verifiedTitle = false;
		try {
			Assert.assertEquals(articleTitle.getText().contains(expectedArticleTitle), "Article Title mismatch");
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}

		logger.info("Exiting Method: " + methodName);
		return verifiedTitle;
	}
    
    public void gotoPreviousView() {
    		methodName = "gotoPreviousView";
		logger.info("Entering Method: " + methodName);	
		try {
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				util.clickifClickable(backToPreviousView,Constant.SHORT_TIMEOUT);
			}
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		logger.info("Exiting Method: " + methodName);
	}
}
