package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.apache.log4j.Logger;

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
    Logger log = Logger.getLogger(ArticleDetailPage.class);
	private WebDriver driver;
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
		log.info("Entering Method: " + methodName);
		Boolean verifiedTitle = false;
		try {
			Assert.assertEquals(articleTitle.getText().contains(expectedArticleTitle), "Article Title mismatch");
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
		}

		log.info("Exiting Method: " + methodName);
		return verifiedTitle;
	}
    
    public void gotoPreviousView() {
    		methodName = "gotoPreviousView";
		log.info("Entering Method: " + methodName);	
		try {
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				util.clickifClickable(backToPreviousView,Constant.SHORT_TIMEOUT);
			}
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
		}
		log.info("Exiting Method: " + methodName);
	}
    
    //Validate the tab title, text-to-speech, bookmark, share option, back button
    public void validateNavigationBarMenu() {
    		methodName = "validateNavigationBarMenu";
    }
}
