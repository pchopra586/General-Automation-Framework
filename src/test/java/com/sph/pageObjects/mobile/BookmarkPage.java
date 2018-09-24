package com.sph.pageObjects.mobile;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;


public class BookmarkPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(BookmarkPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;

    public BookmarkPage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @iOSXCUITFindBy(accessibility = "navigation_title")
	@AndroidFindBy(id = "toolbar_title")
	private MobileElement bookmarkPageTitle;

	@iOSXCUITFindBy(accessibility = "section_status_icon")
	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/rl_no_bookmarks']//child::android.widget.ImageView")
	private MobileElement bookmarkIconImage;

	@iOSXCUITFindBy(accessibility = "section_status_message")
	@AndroidFindBy(id = "tv_no_bookmarks")
	private MobileElement bookmarkHelpText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='article_title']")
	@AndroidFindBy(id = "article_title")
	private List<MobileElement> bookmarkedArticleList;

	@iOSXCUITFindBy(accessibility = "Delete")
	private MobileElement deleteButton;

	public HomePage verifyScreenTitle() {
		
		try {
			logger.info("Section title is : " + bookmarkPageTitle.getText());
			Assert.assertEquals(bookmarkPageTitle.getText(), Constant.BOOKMARK);
			logger.info("Section title matches : " + Constant.BOOKMARK + "Equals " + bookmarkPageTitle.getText());
			logger.info("Section title is displaying correctly!!");
			return new HomePage(driver);
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
	}

	public BookmarkPage verifyDefaultBookmarkPage() {
		logger.info("Verifying bookmark default screen ");
		try {
			util.verifyMobileElements("Bookmark Page", bookmarkIconImage, bookmarkHelpText);
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}
		logger.info("No articles Bookmarked yet!");
		return this;
	}

	public ArticlePage openAndVerifyBookmarkedArticle(int index) {
		try {
			if (bookmarkedArticleList.size() > 0) {
				bookmarkedArticleList.get(index).getText();
				logger.info("Article headline of bookmarked article is :" + bookmarkedArticleList.get(index).getText());
				bookmarkedArticleList.get(index).click();
				verifyBookmarkIconSelection();
			}
			return new ArticlePage(driver);
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
	}

	public boolean verifyBookmarkIconSelection() {
		try {
			return new ArticlePage(driver).isBookmarkIconSelected();
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return false;
		}

	}

	public LoginPage clickOnHamburgerIcon() {
		try {
			return new HomePage(driver).openLoginControl();
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return null;
		}

	}

	public BookmarkPage compareBookmarkedArticleList(List<String> articles) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < bookmarkedArticleList.size(); i++) {
			list.add(bookmarkedArticleList.get(i).getText());
		}

		logger.info("list are " + list);
		logger.info("Articles list are " + articles);
		Collections.reverse(list);
		Assert.assertEquals(list, articles);
		logger.info("Bookmarked articles are equal");
		return this;
	}

	public BookmarkPage deleteBookmarkedArticle(int index, List<String> articles) {
		if (bookmarkedArticleList.size() > 0) {
			articles.remove(bookmarkedArticleList.get(index).getText());
			util.swipeSpecificElement(bookmarkedArticleList.get(index), Constant.LEFT);
			logger.info("Article to be removed is at " + index);
			
			@SuppressWarnings("rawtypes")
			TouchAction action = new TouchAction((AppiumDriver) driver);
			action.tap(tapOptions().withElement(element(deleteButton))).perform();
		}
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < bookmarkedArticleList.size(); i++) {
			list.add(bookmarkedArticleList.get(i).getText());
		}
		logger.info("list are " + list);
		logger.info("Articles list are " + articles);
		Collections.reverse(list);
		Assert.assertEquals(list, articles);
		logger.info("Bookmarked articles are equal");
		return this;
	}

	public LoginPage openLoginControlOnBookmarkPage() {
		try {
			return new HomePage(driver).openLoginControl();
		}catch(Exception e) {
			logger.error("Unable to login from Bookmark page " + e);
			return null;
		}
	}

	public BookmarkPage surfAllBookmarkedArticles() {

		for (int i = 0; i < bookmarkedArticleList.size(); i++) {
			openAndVerifyBookmarkedArticle(i);
			clickOnBackButton(1);
		}

		return this;
	}

	public HomePage clickOnBackButton(int noOfArticle) {
		try {
			return new ArticlePage(driver).clickOnBackButton(noOfArticle);
		}catch(Exception e) {
			logger.error("Unable to login from Bookmark page " + e);
			return null;
		}
	}
}