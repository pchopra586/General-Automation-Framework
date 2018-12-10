package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.apache.log4j.Logger;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.listeners.Reporter;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.IOSElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ArticlePage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger log = Logger.getLogger(BookmarkPage.class);
	private WebDriver driver;
    private Capabilities capabilities;
    private DeviceActions util;
    
    private Map<String, Integer> contentDimension = null;

	public ArticlePage(WebDriver driver) throws MalformedURLException {
        this.driver = driver;
        util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = "article_title")
	@AndroidFindBy(id = "article_headline")
	private MobileElement acceptInfo; // need proper locator for this element

	@AndroidFindBy(id = "imgPremium")
	private MobileElement premiumIcon;

	@iOSXCUITFindBy(accessibility = "login")
	@AndroidFindBy(id = "btn_login")
	private MobileElement loginButton;

	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='article_title'])[1]")
	@AndroidFindBy(id = "article_headline")
	private MobileElement articleHeadline;

	@iOSXCUITFindBy(accessibility = "subscribe")
	@AndroidFindBy(id = "btn_subscribe")
	private MobileElement subscribeButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
	@AndroidFindBy(id = "tv_title")
	private MobileElement sectionTitle;
	
	@iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"article_published_on\"])[1]")
	private MobileElement publishInfo;

	@iOSXCUITFindBy(accessibility = "title_subscription_full_article")
	@AndroidFindBy(id = "tv_read_full_article")
	private MobileElement readFullArticleText;
	
	@iOSXCUITFindBy(accessibility = "time")
	private MobileElement timeIcn;

	@iOSXCUITFindBy(accessibility = "article_gradient")
	@AndroidFindBy(id = "gradientView")
	private MobileElement gradientView;

	@FindBy(css = "#main-content p:nth-child(3) ")
	private WebElement mainContent;

	@iOSXCUITFindBy(accessibility = "FROM AROUND THE WEB")
	private MobileElement fromAroundTheWebTitle;

	@iOSXCUITFindBy(accessibility = "MORE FROM ST")
	private MobileElement moreFromStTitle;

	@iOSXCUITFindBy(xpath = "//*[@name='FROM AROUND THE WEB']//following-sibling::XCUIElementTypeCell")
	private List<MobileElement> articlesUnderFromAroundTheWebSection;

	@iOSXCUITFindBy(xpath = "//*[@name='FROM AROUND THE WEB']//following-sibling::XCUIElementTypeCell/XCUIElementTypeStaticText[2]")
	private List<MobileElement> articlesTextUnderFromAroundTheWebSection;

	@iOSXCUITFindBy(xpath = "//*[@name='MORE FROM ST']//following-sibling::XCUIElementTypeCell")
	private List<MobileElement> articlesUnderMoreFromStSection;

	@iOSXCUITFindBy(xpath = "//*[@name='MORE FROM ST']//following-sibling::XCUIElementTypeCell/XCUIElementTypeStaticText[2]")
	private List<MobileElement> articlesTextUnderMoreFromStSection;

	@iOSXCUITFindBy(accessibility = "message_thanks")
	@AndroidFindBy(id = "tv_paywall_message_header")
	private MobileElement thankText;

	@iOSXCUITFindBy(accessibility = "subscription_message")
	@AndroidFindBy(id = "tv_paywall_message_body")
	private MobileElement premiumStoryAccessHelpText;

	@iOSXCUITFindBy(accessibility = "premium_info")
	@AndroidFindBy(id = "tv_paywall_premium_link")
	private MobileElement whatIsPremiumText;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/XCUIElementTypeOther[2]//child::XCUIElementTypeButton")
	private MobileElement imageOnArticlePage;

	@iOSXCUITFindBy(accessibility = "bookmark")
	@AndroidFindBy(id = "btn_boomark")
	private MobileElement bookmarkBtn;
	
	@iOSXCUITFindBy(accessibility = "share")
	private MobileElement shareBtn;
	
	@iOSXCUITFindBy(accessibility = "text_to_speech")
	private MobileElement textToSpeechBtn;

	@iOSXCUITFindBy(accessibility = "back")
	@AndroidFindBy(id = "btn_back")
	private MobileElement backBtn;

	@FindBy(css = ".related-story-headline a")
	@iOSXCUITFindBy(xpath = "//*[@name='RELATED STORY']//following-sibling:: XCUIElementTypeButton")
	@AndroidFindBy(id = "com.buuuk.st:id/tv_related_story_title")
	private MobileElement relatedStorylink;

	@iOSXCUITFindBy(accessibility = "close menu")
	private MobileElement closeButton;

	@iOSXCUITFindBy(xpath = "//*[@label='bookmark selected']")
	private MobileElement bookmarkIconSelected;

	@FindBy(tagName = "body")
	private WebElement articleContent;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='article_content']//child::XCUIElementTypeStaticText")
	@AndroidFindBy(id = "native_article_view")
	private MobileElement nativeArticle;

	@iOSXCUITFindBy(className = "XCUIElementTypeWebView")
	@AndroidFindBy(className = "android.webkit.WebView")
	private MobileElement webArticle;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='article_internal_suggestion']/XCUIElementTypeTable/XCUIElementTypeStaticText")
	private MobileElement intRecommendedTitle;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='article_internal_suggestion']//XCUIElementTypeStaticText[@name='article_title']")
	private MobileElement intRecommendedArticleTitle;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='article_internal_suggestion']//XCUIElementTypeStaticText[@name='article_published_on']")
	private MobileElement intRecommendedArticlePublishInfo;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='article_external_suggestion']/XCUIElementTypeTable/XCUIElementTypeStaticText")
	private MobileElement extRecommendedTitle;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='article_external_suggestion']//XCUIElementTypeStaticText[@name='article_title']")
	private MobileElement extRecommendedArticleTitle;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='article_external_suggestion']//XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
	private MobileElement extRecommendedArticlePublisherInfo;
	
	@iOSXCUITFindBy(accessibility = IOSElements.OUTBRAIN_TEXT)
	private MobileElement outbrainLogoText;
	
	@iOSXCUITFindBy(accessibility = IOSElements.OUTBRAIN_LOGO_ID)
	private MobileElement outbrainLogo;

	public ArticlePage switchToMainArticle() {
		boolean flag = util.isElementPresent(acceptInfo, Constant.SHORT_TIMEOUT);
		if (flag) {
			acceptInfo.click();
		}
		return this;
	}

	public ArticlePage switchView(String view) {
		log.info("Switching to " + view);
		try{
			util.switchContextToView((AppiumDriver<MobileElement>) driver, view);
		}catch (Exception ex) {
			log.info("Exception in getting response.." + ex.getMessage());
		}
		log.info("body is" + articleContent.getText());
		return this;
	}

	public ArticlePage assertArticleHeading(String headline) {
		boolean flag = util.isElementPresent(articleHeadline, Constant.SHORT_TIMEOUT, "Article Heading");

		if (flag) {
			try {
				log.info("Article headline from  assert article get Text is" + articleHeadline.getText());
				Assert.assertTrue(articleHeadline.getText().contains(headline),"Article opened is different from expected");
				log.info("Article headline matches! we have been navigated to selected article..");

			} catch (AssertionError er) {
				Assert.fail("Assertion Failed : " + er.getMessage());
			}
		}
		return this;
	}

	/*
	 * Commented section is present in below method purposely, as it is
	 * dependent on one of issues which developer needs to fix it, once fixed
	 * will remove comments
	 */

	public ArticlePage verifyPremiumArticleAccess(int maxSwipe) {

		log.info("verifying accessibility of premium article");
		boolean flag = util.swipeVerticalUntilElementIsFound(premiumStoryAccessHelpText, maxSwipe, Constant.UP);
		if (flag) {
			log.info("'premium Stories access help text' is visible!");
			// gradient view,what is premium not visible
			log.info(thankText.getText().replaceAll("\\s", " "));
			util.verifyMobileElements("Premium Article Page", readFullArticleText, subscribeButton, thankText,
					premiumStoryAccessHelpText);
			log.info(
					"Gradient view, read full article text ,Subscribe and Login button, Thank you Text,premium Stories access help text,what is Premium Text are present!");
			Assert.assertEquals(readFullArticleText.getText(), Constant.READFULLARTICLE);
			Assert.assertEquals(thankText.getText().replaceAll("\n", " "), Constant.THANK_YOU_FOR_READING_ST_TEXT);

			Assert.assertEquals(premiumStoryAccessHelpText.getText().trim(), Constant.PREMIUM_STORIES_ACCESS_HELP_TEXT);
			// util.assertEquals(whatIsPremiumText.getText(),
			// Constant.WHAT_IS_PREMIUM_TEXT);
			switchView(Constant.WEBVIEW);
			boolean visibilityFlag = util.isElementPresent((MobileElement)mainContent, Constant.SHORT_TIMEOUT);
			if (!visibilityFlag) {
				log.info("User doesn't have access to the article, kindly subscribe for get access to premium content!");
			}

		}

		return this;

	}

	public ArticlePage verifySectionTitle(String tabName) {
		log.info("Section title is : " + sectionTitle.getText());
		Assert.assertEquals(sectionTitle.getText(), tabName);
		log.info("Section title is displaying correctly!!");
		return this;
	}

	public ArticlePage verifyArticleAccess() {
		switchView(Constant.WEBVIEW);
		util.isElementPresent((MobileElement)mainContent, Constant.SHORT_TIMEOUT);
		log.info(mainContent.getText());
		log.info("User has access to the article!");
		switchView(Constant.NATIVE);
		return this;
	}

	public ArticlePage verifySectionTitleIsDisplayedOnMainArticlePage(MobileElement element, String sectionName) {
		util.swipeVerticalUntilElementIsFound(element, 20, Constant.UP);
		log.info("Section title is : " + element.getText());
		Assert.assertEquals(element.getText(), sectionName);
		log.info("Section title is displaying correctly!!");
		return this;
	}

	public ArticlePage verifyFromAroundTheWebSection() {
		verifySectionTitleIsDisplayedOnMainArticlePage(fromAroundTheWebTitle, Constant.FROM_AROUND_THE_WEB);
		verifySectionUnderMainArticlePage(articlesUnderFromAroundTheWebSection,
				articlesTextUnderFromAroundTheWebSection, Constant.FROM_AROUND_THE_WEB);
		return this;
	}

	public ArticlePage verifyMoreFromStSection() {
		verifySectionTitleIsDisplayedOnMainArticlePage(moreFromStTitle, Constant.MORE_FROM_ST);
		verifySectionUnderMainArticlePage(articlesUnderMoreFromStSection, articlesTextUnderMoreFromStSection,
				Constant.MORE_FROM_ST);
		return this;
	}

	public ArticlePage verifySectionUnderMainArticlePage(List<MobileElement> element1, List<MobileElement> element2,
			String sectionName) {

		log.info("Number of articles present under this section : " + element1.size());
		if (element1.size() > 0) {
			log.info("articles listed under " + sectionName + " are : ");
			for (int i = 0; i < element1.size(); i++) {
				log.info(element2.get(i).getText());
				log.info(element2.get(i).getText());
			}
		}
		return this;
	}

	public ArticlePage verifyMainArticleOnArticlePage() {
		util.isElementPresent(imageOnArticlePage, Constant.SHORT_TIMEOUT);
		log.info("Article Image is loaded successfully!");
		switchView(Constant.WEBVIEW);
		boolean visibilityFlag = util.isElementPresent((MobileElement)mainContent, Constant.SHORT_TIMEOUT);
		if (!visibilityFlag) {
			log.info(
					"User doesn't have access to the article, kindly subscribe for get access to premium content!");
		} else {
			log.info("User have access to the article, article is loaded successfully!");
		}
		switchView(Constant.NATIVE);
		return this;
	}

	public HomePage clickOnBackButton(int noOfArticle) {
		try {
			for (int i = 0; i < noOfArticle; i++) {
				util.clickifClickable(backBtn, Constant.SHORT_TIMEOUT);
			}
			return new HomePage(driver);
		}catch (Exception ex) {
			log.info("Exception in getting response.." + ex.getMessage());
			driver.quit();
			return null;
		}
	}

	public ArticlePage clickOnBookmarkIcon() {
		util.clickifClickable(bookmarkBtn, Constant.SHORT_TIMEOUT);
		return this;
	}

	public boolean isBookmarkIconSelected() {
		boolean flag;
		if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			flag = util.isElementPresent(bookmarkIconSelected, Constant.SHORT_TIMEOUT);
		} else {
			flag = util.isElementPresent(bookmarkBtn, Constant.SHORT_TIMEOUT);
			// flag=bookmarkButton.isSelected(); is NAF node , is Selected not
			// working need to confirm with the developer
		}
		// if (flag) {
		// log.info("Article is bookmarked successfully!");
		// util.logPassMessage("Article is bookmarked successfully!");
		// } else {
		// util.logFailedMessage("Article is not bookmarked");// pass or fail?
		//
		// }
		return flag;
	}

	public ArticlePage createListOfBookmarkedArticle(List<String> articles) {
		boolean flag = isBookmarkIconSelected();
		if (flag) {
			createArticleList(articles);
		}
		return this;
	}

	public String bookmarkArticle() {
		boolean flag = isBookmarkIconSelected();
		if (flag) {
			log.info("Article is already bookmarked!!");
		} else {
			clickOnBookmarkIcon();
			flag = isBookmarkIconSelected();
			if (flag) {
				log.info("Article is bookmarked successfully!");
			} else {
				log.error("Unable to bookmark article");
			}
		}
		return articleHeadline.getAttribute("label");
	}

	public ArticlePage removeBookmarkedArticle() {
		boolean flag = isBookmarkIconSelected();
		if (flag) {
			clickOnBookmarkIcon();
			flag = isBookmarkIconSelected();
			if (flag) {
				log.error("Unable to unbookmark article!");
			} else {
				log.info("Article is removed from bookmarked list of article!!");

			}

		} else {

			log.info("Navigated article was not bookmarked!!");

		}
		return this;
	}

	public ArticlePage navigateToRelatedArticle() {
		checkView();
		boolean flag = util.swipeVerticalUntilElementIsFound(relatedStorylink, 10, Constant.UP);
		if (flag) {
			String headline = getRelatedArticleHeadline();
			util.clickifClickable(relatedStorylink, Constant.SHORT_TIMEOUT);
			switchView(Constant.NATIVE);
			verifyRelatedArticleHeadline(headline);
		} else {
			log.info("No Related story found!!");
		}
		return this;
	}

	public ArticlePage closeArticle(int noOfArticle) {
		if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
			for (int i = 0; i < noOfArticle; i++) {
				util.clickifClickable(closeButton, Constant.SHORT_TIMEOUT);
			}
		}
		return this;
	}

	public List<String> createArticleList(List<String> articles) {
		if (!articles.contains(articleHeadline.getText())) {
			articles.add(articleHeadline.getText());
			log.info("Articles are " + articles);
		}
		return articles;
	}

	public ArticlePage verifyArticleContentForHtmlEntities() {
		checkView();
		String bodyText = articleContent.getText();
		log.info(bodyText);
		util.verifyUnwantedTextElements(bodyText, Constant.NON_BREAKING_SPACE, Constant.LESS_THAN,
				Constant.GREATER_THAN, Constant.AMPERSAND, Constant.DOUBLE_QUOTATION_MARK, Constant.APOSTROPHE,
				Constant.COPYRIGHT, Constant.REGISTERED_TRADEMARK, Constant.TRADEMARK);
		switchView(Constant.NATIVE);
		return this;
	}

	public ArticlePage bookMarkArticleTillCustomDepth(List<String> list, int depth, String bookmarkFlag) {
		for (int i = 0; i < depth; i++) {
			navigateToRelatedArticle();
			if (bookmarkFlag.equalsIgnoreCase(Constant.BOOKMARK_FLAG_YES)) {
				bookmarkArticle();
				createListOfBookmarkedArticle(list);
			}
		}
		return this;
	}

	public void checkView() {

		util.swipeVertical("UP");
		boolean flag = util.isElementPresent(nativeArticle, Constant.SHORT_TIMEOUT);
		if (flag) {
			log.info("The article is displayed in native view!");
			switchView(Constant.NATIVE);

		} else {
			log.info("The article is displayed in Web view! ");
			switchView(Constant.WEBVIEW);
		}

	}

	public ArticlePage verifyRelatedArticleHeadline(String relatedStoryHeadline) {
		assertArticleHeading(relatedStoryHeadline);
		return this;
	}

	public String getRelatedArticleHeadline() {
		return relatedStorylink.getText();
	}

	public ArticlePage openArticleFromMoreFromSTSection(int index) {
		verifyMoreFromStSection();
		if (articlesTextUnderMoreFromStSection.size() > 0) {
			util.swipeVerticalUntilElementIsFound(articlesTextUnderMoreFromStSection.get(index), 4, Constant.UP);
			articlesTextUnderMoreFromStSection.get(index).click();
		}
		return this;
	}

	public ArticlePage openArticleFromAroundTheWebSection(int index) {

		verifyFromAroundTheWebSection();
		if (articlesTextUnderMoreFromStSection.size() > 0) {
			util.swipeVerticalUntilElementIsFound(articlesTextUnderFromAroundTheWebSection.get(index), 4, Constant.UP);
			articlesTextUnderFromAroundTheWebSection.get(index).click();
		}
		return this;
	}

	public ArticlePage toggleBookMarkIconForRelatedArticles(int depth) {
		for (int i = 0; i < depth; i++) {
			navigateToRelatedArticle();

			bookmarkArticle();
			removeBookmarkedArticle();

		}
		return this;
	}

	public LoginPage clickOnLoginButton() {
		log.info("Clicking on login Button");
		try{
			util.clickifClickable(loginButton, Constant.SHORT_TIMEOUT);
			return new LoginPage(driver);
		}catch(Exception e) {
			log.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
		
	}
	
	/*new framework changes*/
	public void assertOnDetailsPage() {
		log.info("Verifying on details page");
		Reporter.addStepLog("Verifying on details page");
		boolean headline = util.isElementPresent(articleHeadline, Constant.SHORT_TIMEOUT, "Article Heading");
		boolean bookmark = util.isElementPresent(bookmarkBtn, Constant.SHORT_TIMEOUT, "Bookmark Button");
		boolean textToSpeech = util.isElementPresent(textToSpeechBtn, Constant.SHORT_TIMEOUT, "Bookmark Button");
		boolean share = util.isElementPresent(shareBtn, Constant.SHORT_TIMEOUT, "Bookmark Button");
		boolean back = util.isElementPresent(backBtn, Constant.SHORT_TIMEOUT, "Bookmark Button");
		if (headline && bookmark && textToSpeech && share && back) {
			log.info("Navigated to details page and article headline is displayed");
			Reporter.addStepLog("Navigated to details page and article headline is displayed");			
		}else{
			log.error("error while Navigation to details");				
		}
		
	}
	
	public Map<String, Integer> articlePageAlignmentValidation(Map<String, Integer> articleContentDimension) {
		methodName = "articlePageAlignmentValidation";
		log.info("Entering Method: " + methodName);
		
		Integer headlineXStart = articleHeadline.getCoordinates().onPage().getX();
		Integer headlineWidth = articleHeadline.getSize().getWidth();
		
		Integer backBtnWidth = backBtn.getSize().getWidth();
		Integer backBtnHeight = backBtn.getSize().getHeight();
		Integer backBtnXStart = backBtn.getCoordinates().onPage().getX();
		Integer backBtnYStart = backBtn.getCoordinates().onPage().getY();
		
		Integer navigationTitleXStart = sectionTitle.getCoordinates().onPage().getX();
		Integer navigationTitleYStart = sectionTitle.getCoordinates().onPage().getY();
		
		Integer textToSpeechWidth = textToSpeechBtn.getSize().getWidth();
		Integer textToSpeechHeight = textToSpeechBtn.getSize().getHeight();
		Integer textToSpeechXStart = textToSpeechBtn.getCoordinates().onPage().getX();
		Integer textToSpeechYStart = textToSpeechBtn.getCoordinates().onPage().getY();
		
		Integer bookmarkWidth = bookmarkBtn.getSize().getWidth();
		Integer bookmarkHeight = bookmarkBtn.getSize().getHeight();
		Integer bookmarkXStart = bookmarkBtn.getCoordinates().onPage().getX();
		Integer bookmarkYStart = bookmarkBtn.getCoordinates().onPage().getY();
		
		Integer shareWidth = shareBtn.getSize().getWidth();
		Integer shareHeight = shareBtn.getSize().getHeight();
		Integer shareXStart = shareBtn.getCoordinates().onPage().getX();
		Integer shareYStart = shareBtn.getCoordinates().onPage().getY();
		
		Integer timeIcnWidth = timeIcn.getSize().getWidth();
		Integer timeIcnHeight = timeIcn.getSize().getHeight(); //Verification will have timeXStart as well
		
		Integer publishInfoWidth = publishInfo.getSize().getWidth();
		Integer publishInfoXStart = publishInfo.getCoordinates().onPage().getX(); //Verification will have timeXStart as well
		
		Integer intRecommendedTitleWidth = intRecommendedTitle.getSize().getWidth();
		Integer intRecommendedTitleHeight = intRecommendedTitle.getSize().getHeight();
		
		Integer intRecommendedArticleTitleWidth = intRecommendedArticleTitle.getSize().getWidth();
		Integer intRecommendedArticleTitleXStart = intRecommendedArticleTitle.getCoordinates().onPage().getX();
		
		Integer intRecommendedArticlePublishInfoWidth = intRecommendedArticlePublishInfo.getSize().getWidth();
		Integer intRecommendedArticlePublishInfoXStart = intRecommendedArticlePublishInfo.getCoordinates().onPage().getX();
		Integer intRecommendedArticlePublishInfoHeight = intRecommendedArticlePublishInfo.getSize().getHeight();
			
		Integer extRecommendedTitleWidth = extRecommendedTitle.getSize().getWidth();
		Integer extRecommendedTitleHeight = extRecommendedTitle.getSize().getHeight();		
		
		Integer extRecommendedArticlePublisherInfoWidth = extRecommendedArticlePublisherInfo.getSize().getWidth();
		Integer extRecommendedArticlePublisherInfoXStart = extRecommendedArticlePublisherInfo.getCoordinates().onPage().getX();
		
		Integer outbrainLogoWidth = outbrainLogo.getSize().getWidth();
		Integer outbrainLogoXStart = outbrainLogo.getCoordinates().onPage().getX();
		Integer outbrainLogoHeight = outbrainLogo.getSize().getHeight();
		
		Integer outbrainLogoTextWidth = outbrainLogoText.getSize().getWidth();
		Integer outbrainLogoTextXStart = outbrainLogoText.getCoordinates().onPage().getX();
		Integer outbrainLogoTextHeight = outbrainLogoText.getSize().getHeight();
		
		if(articleContentDimension.get("resetReqDimension").equals(1)) {
			articleContentDimension.put("textXStart", headlineXStart);
			articleContentDimension.put("textRowWidth", headlineWidth);
			
			articleContentDimension.put("backBtnXStart", backBtnXStart);
		    articleContentDimension.put("backBtnYStart", backBtnYStart);
		    articleContentDimension.put("backBtnWidth", backBtnWidth);
		    articleContentDimension.put("backBtnHeight", backBtnHeight);
		    
		    articleContentDimension.put("navigationTitleXStart", navigationTitleXStart);
		    articleContentDimension.put("navigationTitleYStart", navigationTitleYStart);
		    
		    articleContentDimension.put("textToSpeechXStart", textToSpeechXStart);
		    articleContentDimension.put("textToSpeechYStart", textToSpeechYStart);
		    articleContentDimension.put("textToSpeechWidth", textToSpeechWidth);
		    articleContentDimension.put("textToSpeechHeight", textToSpeechHeight);
		    
		    articleContentDimension.put("bookmarkXStart", bookmarkXStart);
		    articleContentDimension.put("bookmarkYStart", bookmarkYStart);
		    articleContentDimension.put("bookmarkWidth", bookmarkWidth);
		    articleContentDimension.put("bookmarkHeight", bookmarkHeight);
		    
		    articleContentDimension.put("shareXStart", shareXStart);
		    articleContentDimension.put("shareYStart", shareYStart);
		    articleContentDimension.put("shareWidth", shareWidth);
		    articleContentDimension.put("shareHeight", shareHeight);
		    
		    articleContentDimension.put("timeIcnWidth", timeIcnWidth);
		    articleContentDimension.put("timeIcnHeight", timeIcnHeight);
		    articleContentDimension.put("publishInfoWidth", publishInfoWidth);
		    articleContentDimension.put("publishInfoXStart", publishInfoXStart);
		    
		    articleContentDimension.put("recommendedArticleTitleWidth", intRecommendedArticleTitleWidth);
		    articleContentDimension.put("recommendedArticleTitleXStart", intRecommendedArticleTitleXStart);
		    
		    articleContentDimension.put("intRecommendedTitleWidth", intRecommendedTitleWidth);
		    articleContentDimension.put("intRecommendedTitleHeight", intRecommendedTitleHeight);
		    articleContentDimension.put("intRecommendedArticlePublishInfoXStart", intRecommendedArticlePublishInfoXStart);
		    articleContentDimension.put("intRecommendedArticlePublishInfoWidth", intRecommendedArticlePublishInfoWidth);
		    articleContentDimension.put("intRecommendedArticlePublishInfoHeight", intRecommendedArticlePublishInfoHeight);
		    
		    articleContentDimension.put("extRecommendedTitleWidth", extRecommendedTitleWidth);
		    articleContentDimension.put("extRecommendedTitleHeight", extRecommendedTitleHeight);
		    articleContentDimension.put("extRecommendedArticlePublisherInfoXStart", extRecommendedArticlePublisherInfoXStart);
		    articleContentDimension.put("extRecommendedArticlePublisherInfoWidth", extRecommendedArticlePublisherInfoWidth);
		    
		    articleContentDimension.put("outbrainLogoTextXStart", outbrainLogoTextXStart);
		    articleContentDimension.put("outbrainLogoTextWidth", outbrainLogoTextWidth);
		    articleContentDimension.put("outbrainLogoTextHeight", outbrainLogoTextHeight);
		    
		    articleContentDimension.put("outbrainLogoXStart", outbrainLogoXStart);
		    articleContentDimension.put("outbrainLogoWidth", outbrainLogoWidth);
		    articleContentDimension.put("outbrainLogoHeight", outbrainLogoHeight);
		}
		else {
			Integer intRecommendedTitleXStart = intRecommendedTitle.getCoordinates().onPage().getX();
			Integer extRecommendedTitleXStart = extRecommendedTitle.getCoordinates().onPage().getX();
			Integer extRecommendedArticleTitleWidth = extRecommendedArticleTitle.getSize().getWidth();
			Integer extRecommendedArticleTitleXStart = extRecommendedArticleTitle.getCoordinates().onPage().getX();
			
			Assert.assertEquals(headlineXStart, articleContentDimension.get("textXStart"), "Inconsistent x-axis placement of articleHeadline");
			Assert.assertEquals(headlineWidth, articleContentDimension.get("textRowWidth"), "Inconsistent width of articleHeadline row");	
			
			Assert.assertEquals(backBtnXStart, articleContentDimension.get("backBtnXStart"), "Inconsistent x-axis placement of Back Button");
			Assert.assertEquals(backBtnYStart, articleContentDimension.get("backBtnYStart"), "Inconsistent y-axis placement of Back Button");	
			Assert.assertEquals(backBtnWidth, articleContentDimension.get("backBtnWidth"), "Inconsistent Width of Back Button");
			Assert.assertEquals(backBtnHeight, articleContentDimension.get("backBtnHeight"), "Inconsistent Height of Back Button");	

			Assert.assertEquals(backBtnXStart, articleContentDimension.get("navigationTitleXStart"), "Inconsistent x-axis placement of navigation title");
			Assert.assertEquals(backBtnYStart, articleContentDimension.get("navigationTitleYStart"), "Inconsistent y-axis placement of navigation title");	
			
			Assert.assertEquals(textToSpeechXStart, articleContentDimension.get("textToSpeechXStart"), "Inconsistent x-axis placement of Text to Speech Button");
			Assert.assertEquals(textToSpeechYStart, articleContentDimension.get("textToSpeechYStart"), "Inconsistent y-axis placement of Text to Speech Button");	
			Assert.assertEquals(textToSpeechWidth, articleContentDimension.get("textToSpeechWidth"), "Inconsistent Width of Text to Speech Button");
			Assert.assertEquals(textToSpeechHeight, articleContentDimension.get("textToSpeechHeight"), "Inconsistent Height of Text to Speech Button");	

			Assert.assertEquals(bookmarkXStart, articleContentDimension.get("bookmarkXStart"), "Inconsistent x-axis placement of Text to Speech Button");
			Assert.assertEquals(bookmarkYStart, articleContentDimension.get("bookmarkYStart"), "Inconsistent y-axis placement of Text to Speech Button");	
			Assert.assertEquals(bookmarkWidth, articleContentDimension.get("bookmarkWidth"), "Inconsistent Width of Text to Speech Button");
			Assert.assertEquals(bookmarkHeight, articleContentDimension.get("bookmarkHeight"), "Inconsistent Height of Text to Speech Button");	

			Assert.assertEquals(shareXStart, articleContentDimension.get("shareXStart"), "Inconsistent x-axis placement of share Button");
			Assert.assertEquals(shareYStart, articleContentDimension.get("shareYStart"), "Inconsistent y-axis placement of share Button");	
			Assert.assertEquals(shareWidth, articleContentDimension.get("shareWidth"), "Inconsistent Width of share Button");
			Assert.assertEquals(shareHeight, articleContentDimension.get("shareHeight"), "Inconsistent Height of share Button");	

			Assert.assertEquals((Integer)timeIcn.getCoordinates().onPage().getX(), articleContentDimension.get("textXStart"), "Inconsistent x-axis placement of articleHeadline");
			Assert.assertEquals(timeIcnWidth, articleContentDimension.get("timeIcnWidth"), "Inconsistent width of Time Icon");	
			Assert.assertEquals(timeIcnHeight, articleContentDimension.get("timeIcnHeight"), "Inconsistent height of Time Icon");	
			
			Assert.assertEquals(publishInfoXStart, articleContentDimension.get("publishInfoXStart"), "Inconsistent x-axis placement of publish info");
			Assert.assertEquals(publishInfoWidth, articleContentDimension.get("publishInfoWidth"), "Inconsistent width of publish info");	
			
			Assert.assertEquals(intRecommendedTitleWidth, articleContentDimension.get("intRecommendedTitleWidth"), "Inconsistent width of internal Recommended section title");
			Assert.assertEquals(intRecommendedTitleHeight, articleContentDimension.get("intRecommendedTitleHeight"), "Inconsistent height of internal Recommended section title");	
			Assert.assertEquals(intRecommendedTitleXStart, articleContentDimension.get("textXStart"), "Inconsistent x-axis placement of internal Recommended section title");
			
			Assert.assertEquals(intRecommendedArticleTitleXStart, articleContentDimension.get("recommendedArticleTitleXStart"), "Inconsistent x-axis placement of internally Recommended article title");
			Assert.assertEquals(intRecommendedArticleTitleWidth, articleContentDimension.get("recommendedArticleTitleWidth"), "Inconsistent width of internally Recommended article title");	
			
			Assert.assertEquals(intRecommendedArticlePublishInfoWidth, articleContentDimension.get("intRecommendedArticlePublishInfoWidth"), "Inconsistent width of internal Recommended Publish info");
			Assert.assertEquals(intRecommendedArticlePublishInfoHeight, articleContentDimension.get("intRecommendedArticlePublishInfoHeight"), "Inconsistent height of internal Recommended Publish info");	
			Assert.assertEquals(intRecommendedArticlePublishInfoXStart, articleContentDimension.get("intRecommendedArticlePublishInfoXStart"), "Inconsistent x-axis placement of internal Recommended Publish info");
			
			Assert.assertEquals(extRecommendedTitleWidth, articleContentDimension.get("extRecommendedTitleWidth"), "Inconsistent width of external Recommended section title");
			Assert.assertEquals(extRecommendedTitleHeight, articleContentDimension.get("extRecommendedTitleHeight"), "Inconsistent height of external Recommended section title");	
			Assert.assertEquals(extRecommendedTitleXStart, articleContentDimension.get("textXStart"), "Inconsistent x-axis placement of external Recommended section title");
			
			Assert.assertEquals(extRecommendedArticleTitleXStart, articleContentDimension.get("recommendedArticleTitleXStart"), "Inconsistent x-axis placement of externally Recommended article title");
			Assert.assertEquals(extRecommendedArticleTitleWidth, articleContentDimension.get("recommendedArticleTitleWidth"), "Inconsistent width of externally Recommended article title");	
		    
			Assert.assertEquals(extRecommendedArticlePublisherInfoXStart, articleContentDimension.get("extRecommendedArticlePublisherInfoXStart"), "Inconsistent x-axis placement of externally Recommended publisher info");
			Assert.assertEquals(extRecommendedArticlePublisherInfoWidth, articleContentDimension.get("extRecommendedArticlePublisherInfoWidth"), "Inconsistent width of externally Recommended publisher info");	
		    
			Assert.assertEquals(outbrainLogoTextWidth, articleContentDimension.get("outbrainLogoTextWidth"), "Inconsistent width of outbrain logo text");
			Assert.assertEquals(outbrainLogoTextHeight, articleContentDimension.get("outbrainLogoTextHeight"), "Inconsistent height of outbrain logo text");	
			Assert.assertEquals(outbrainLogoTextXStart, articleContentDimension.get("outbrainLogoTextXStart"), "Inconsistent x-axis placement of outbrain logo text");
			
			Assert.assertEquals(outbrainLogoWidth, articleContentDimension.get("outbrainLogoWidth"), "Inconsistent width of outbrain logo");
			Assert.assertEquals(outbrainLogoHeight, articleContentDimension.get("outbrainLogoHeight"), "Inconsistent height of outbrain logo");	
			Assert.assertEquals(outbrainLogoXStart, articleContentDimension.get("outbrainLogoXStart"), "Inconsistent x-axis placement of outbrain logo");
		}
		
		return articleContentDimension;
	}
	
	public boolean verifyWhetherReachedFirstArticleInListing(String previousArticleHeadline) {
		return articleHeadline.getAttribute("label").equals(previousArticleHeadline);
	}
	
}