package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.IOSElements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import junit.framework.Assert;

public class LatestTabPage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(LatestTabPage.class);
	private WebDriver driver;
    WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;
    private ArticleDetailPage articleDetail;

    public LatestTabPage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @iOSXCUITFindBy(xpath = "//" + IOSElements.ARTICLE_LIST_NATIVE_VIEW)
	private List<MobileElement> articleList;
    
    @iOSXCUITFindBy(tagName = "article_title")
	private List<MobileElement> articleTitleList;
  //(//XCUIElementTypeStaticText[@name="article_title"])[1]
		  
//	@iOSXCUITFindBy(tagName = "article_title")
//	private List<MobileElement> articlePublishTimeList;

    
    public void validateFirstArticleInView() {
		methodName = "validateArticle";
		log.info("Entering Method: " + methodName);
		MobileElement articleTitleOnListingPage = null;
		
		try {
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				articleTitleOnListingPage = articleTitleList.get(1);
				
				util.clickifClickable(articleTitleOnListingPage,Constant.SHORT_TIMEOUT);
				
				articleDetail = new ArticleDetailPage(driver);
				Assert.assertTrue(articleDetail.verifyArticleTitle(articleTitleOnListingPage.getText()));
				
			}
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
		}
		log.info("Exiting Method: " + methodName);
	}
    
    public String openArticle(int articleSequence) {
		methodName = "validateArticle";
		log.info("Entering Method: " + methodName);
		MobileElement articleTitleOnListingPage = null;
		
		try {
			if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				articleTitleOnListingPage = articleTitleList.get(articleSequence);
				
				util.clickifClickable(articleTitleOnListingPage,Constant.SHORT_TIMEOUT);
			}
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
		}
	
		log.info("Exiting Method: " + methodName);
		return articleTitleOnListingPage.getText();
    }
}
