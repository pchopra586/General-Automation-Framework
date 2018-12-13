package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.log4j.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ArticleListingPage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(ArticleListingPage.class);
	private WebDriver driver;
    WebDriverWait wait;
    Capabilities capabilities;
    private DeviceActions util;
    
	@iOSXCUITFindBy(xpath = "//*[@name='premium']//following-sibling::XCUIElementTypeStaticText[@name='article_title']")
	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/imgPremium']//following-sibling::android.widget.TextView[@resource-id='com.buuuk.st:id/article_title']")
	private List<MobileElement> premiumArticles;
	
	@HowToUseLocators(androidAutomation = LocatorGroupStrategy.ALL_POSSIBLE, iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther/child::XCUIElementTypeStaticText[@name='article_title']")
	@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/article_title']/../android.widget.TextView[@index='0']")
	//@AndroidFindBy(xpath = "//*[@resource-id='com.buuuk.st:id/imageLayout']//following-sibling::android.widget.TextView[@resource-id='com.buuuk.st:id/article_title']")
	private List<MobileElement> freeArticles;
	
	@AndroidFindBy(id = "article_title")
	@iOSXCUITFindBy(accessibility = "article_title")
	private List<MobileElement> articlesInView;
    
    public ArticleListingPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        this.wait = new WebDriverWait(this.driver, 30);
        this.util = new DeviceActions(this.driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
    
    public boolean isPremiumArticle(Integer articleSeqInLayout) {
		methodName = "isPremiumArticle";
		log.info("Entering Method: " + methodName);
		boolean isPremium = false;
		try {
			MobileElement premiumIconImage = ((MobileElement) driver.findElement(By.xpath("//XCUIElementTypeCell[" + articleSeqInLayout.toString() + "]//XCUIElementTypeImage[1]")));
			if(premiumIconImage.getAttribute("name").equals("premium")) {
				isPremium = true;
			}
		}catch(Exception e) {
			isPremium = false;
		}
		log.info("Exiting from method: " + methodName);
		return isPremium;
	}
    
    	//TODO: Discuss with Sandhya (couldn't get the purpose of element)
    public ArticlePage openArticle(String articleType) {
    		log.info("Opening Article Type: " + articleType);
		try {
			if (articleType == Constant.PREMIUM) {
				navigateToArticle(premiumArticles.get(0), articleType);
			} else {
				articleType = Constant.FREE;
				navigateToArticle(freeArticles.get(0), articleType);
			}
			return new ArticlePage(driver);
		}catch(Exception ex) {
			log.error("Cannot open Article");
			return null;
		}
	}
    
    public String openLastArticleInView() {
    		Integer totalArticlesInView = articlesInView.size();
    		String lastArticleTitle = null;
    		try {
    			while(totalArticlesInView >= 0) {
    				MobileElement lastArticleInView = articlesInView.get(totalArticlesInView-1);
    				if(lastArticleInView.getAttribute("visible").equalsIgnoreCase("true")) {
    					lastArticleTitle = lastArticleInView.getAttribute("label");
    					lastArticleInView.click();
    					break;
    				}
    				else {
    					totalArticlesInView--;
    				}
    			}
			return lastArticleTitle;
		}catch(Exception ex) {
			log.error("Cannot open Article");
			return null;
		}
	}
    
    public String openFirstArticleInView() {
		Integer totalArticlesInView = articlesInView.size();
		Integer articleIndex = 0;
		String firstArticleTitle = null;
		try {
			while(totalArticlesInView > articleIndex) {
				MobileElement firstArticleInView = articlesInView.get(articleIndex);
				if(firstArticleInView.getAttribute("visible").equalsIgnoreCase("true")) {
					firstArticleTitle = firstArticleInView.getAttribute("label");
					firstArticleInView.click();
					break;
				}
				else {
					articleIndex++;
				}
			}
		return firstArticleTitle;
	}catch(Exception ex) {
		log.error("Cannot open Article");
		return null;
	}
}

    //TODO: Discuss with Sandhya (couldn't get the purpose of element)
	public ArticlePage navigateToArticle(MobileElement element, String articleType) {
		log.info("Searching for " + articleType + " article..");
		util.swipeVertical(Constant.DIRECTION.UP.toString());
		try {
			String headline;
			headline = element.getText();
			log.info("Article found! Now navigating to " + articleType + " article..");
			log.info("Article heading is " + headline);
			util.clickifClickable(element, Constant.SHORT_TIMEOUT);
			log.info(" Opened article, verifying article headline of " + articleType + " article..");
			assertHeadingOfArticle(headline);

			log.info(headline);
			log.info("Article headline matches! we have been navigated to selected article..");
			
			return new ArticlePage(driver);
		} catch (Exception ex) {
			log.error(articleType + " article you are looking for is not present");
			Assert.fail(articleType + "article is not found!");
			return null;
		}		
	}
	
	public ArticlePage switchCall() {
		try {
			return new ArticlePage(driver).switchToMainArticle();
		}catch (Exception ex) {
			log.error("Unable to switch to Main Article");
			return null;
		}
	}

	public ArticlePage assertHeadingOfArticle(String headline) {
		try {
			return new ArticlePage(driver).assertArticleHeading(headline);
		}catch (Exception ex) {
			log.error("Heading of Article is inconsistent");
			return null;
		}
	}
	
	public ArticleListingPage navigateToTopOfPage() {
		methodName = "navigateToTopOfListingPage";
		log.info("Entering Method: " + methodName);
		boolean topOfHomePage = false;
		try {
			MobileElement currentElement = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"section_title\"]"));
			if(currentElement.getAttribute("label").equals("TOP STORIES")) {
				topOfHomePage = true;
			}
		}
		catch(Exception e) {
			log.info("Not reached top of Home page yet");
		}
		log.info("Successfully exiting from method: " + methodName);
		return this;
	}
}
