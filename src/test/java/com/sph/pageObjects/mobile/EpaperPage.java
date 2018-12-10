package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.apache.log4j.Logger;
import com.sph.driverFactory.DriverManager;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.AndroidElements;
import com.sph.utilities.Constant.MENU;
import com.sph.utilities.IOSElements;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EpaperPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(EpaperPage.class);
	private WebDriver driver;
    
	@iOSXCUITFindBy(accessibility = IOSElements.MAIN_NAVIGATION_BAR_NAME)
	@AndroidFindBy(id = AndroidElements.MAIN_NAVIGATION_BAR_NAME)
	private MobileElement pdfNavigationBar;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"" + IOSElements.MAIN_NAVIGATION_BAR_NAME + "\"]/following-sibiling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
	@AndroidFindBy(id = AndroidElements.MAIN_NAVIGATION_BAR_NAME)
	private MobileElement ePaperContentArea;

	public EpaperPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public boolean onEPaperPage() {
		methodName = "onEPaperPage";
		log.info("Entering Method: " + methodName);
		boolean onEPaperPage = false;
		try {
			Assert.assertTrue(pdfNavigationBar.isDisplayed(), "Not on Home Page");
			onEPaperPage = true;
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
		}
		log.info("Successfully exiting from method: " + methodName);
		return onEPaperPage;
	}
	
	public EpaperPage gotoEpaper() {
		methodName = "gotoHomePage";
		log.info("Entering Method: " + methodName);
		try {
			driver = DriverManager.getDriver();
			
			if(!onEPaperPage()) {
				MenuPage menu = new MenuPage(driver);
				menu.clickOnMenu().gotoMenu(MENU.EPAPER);
			}
			Assert.assertTrue(onEPaperPage(),"Unexpectedly not on Home Page");
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
		}
		log.info("Successfully exiting from method: " + methodName);
		return this;
	}
}
