package com.sph.stepDefinitions.mobile;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.ArticleListingPage;
import com.sph.pageObjects.mobile.ArticlePage;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.PrintEditionCalendar;
import com.sph.pageObjects.mobile.PrintEditionPage;
import com.sph.utilities.Constant;
import com.sph.utilities.GenericNavigator;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuildSanityValidation {
	static Logger log;
    WebDriver driver = DriverManager.getDriver();
    GenericNavigator genericNavigator;
    HomePage homePage = null;
    ArticleListingPage articleList = null;
    PrintEditionCalendar calendarIcn;
    
    private String articleTitle = null;
    private ArticlePage articlePage;
    
    private static Map<String, Integer> sectionDimension = null;
    
    private static Map<String, Integer> articleContentDimension = null;
    
    static {
    		//TODO: Check whether this correction is appropriate
    		log = Logger.getLogger(BuildSanityValidation.class);
    }

    public BuildSanityValidation() throws MalformedURLException {
    }
    
    @Given("^I want to complete the basic app installation \\[Sanity Testing\\]$")
    public void i_want_to_complete_the_basic_app_installation_Sanity_Testing() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    		genericNavigator = new GenericNavigator(this.driver);
    		genericNavigator.completeBasicInstallConfig();
    		
    		//homePage = new HomePage(this.driver);
    }
    
    @Then("^I goto STNow Tab in Home Page View$")
    public void i_goto_STNow_Tab_in_Home_Page_View() throws Throwable {
        homePage = new HomePage(this.driver);
        homePage.gotoTab(Constant.TAB.ST_NOW);
    }
    
//    @Then("^I validate the alignment and title of each articles in Home Page$")
//    public void i_validate_the_alignment_and_title_of_each_articles_in_Home_Page() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//    		homePage = new HomePage(this.driver);
//    		homePage.sectionValidation();
//        throw new PendingException();
//    }
    
//    @Then("^I validate the alignment and title of \"([^\"]*)\" on Home Page$")
//	public void i_validate_the_alignment_and_title_of_on_Home_Page(String sectionLabel) throws Throwable {
//    		homePage = new HomePage(this.driver);
//		homePage.sectionValidation(sectionLabel);
//	}
    
    @Then("^I capture the dimension of \"([^\"]*)\" on Home Page$")
    public void i_capture_the_dimension_of_on_Home_Page(String sectionLabel) throws Throwable {
	    	sectionDimension = new HashMap<String, Integer>();
	    	sectionDimension.put("resetDimension", 1);		//0 = False, 1 = True
	    	sectionDimension.put("resetArticleWidth", 1);
	    	sectionDimension.put("resetXAxisStart", 1);
	    	sectionDimension.put("resetArticleImageVideoHeight", 1);
	    	sectionDimension.put("resetRemoveFromHomeBtnDimension", 1);
	    	sectionDimension.put("sectionLabelHeight", 0);
	    	sectionDimension.put("xRowStart", 0);
	    	sectionDimension.put("xRowEnd", 0);
	    	sectionDimension.put("firstRowArticleWidth", 0);
	    	sectionDimension.put("secondRowArticleWidth", 0);
	    	sectionDimension.put("firstRowImageVideoHeight", 0);
	    	sectionDimension.put("secondRowImageVideoHeight", 0);
	    	sectionDimension.put("thirdRowOnwardsImageVideoHeight", 0);
	    	sectionDimension.put("thirdRowOnwardsImageVideoWidth", 0);
	    	sectionDimension.put("removeFromHomeBtnHeight", 0);
	    	sectionDimension.put("removeFromHomeBtnWidth", 0);
	    	sectionDimension.put("removeFromHomeBtnXAxisStart", 0);
    	
    		homePage = new HomePage(this.driver);
		sectionDimension = homePage.sectionValidation(sectionLabel, sectionDimension);
    }

    @Then("^I validate the alignment and relative dimension of \"([^\"]*)\" on Home Page$")
    public void i_validate_the_alignment_and_relative_dimension_of_on_Home_Page(String sectionLabel) throws Throwable {
    		homePage = new HomePage(this.driver);
    		sectionDimension.put("resetDimension", 0);
    		sectionDimension.put("resetArticleWidth", 0);
	    	sectionDimension.put("resetXAxisStart", 0);
	    	sectionDimension.put("resetArticleImageVideoHeight", 0);
	    	sectionDimension.put("resetRemoveFromHomeBtnDimension", 0);
		sectionDimension = homePage.sectionValidation(sectionLabel, sectionDimension);
    }
    
    @Given("^I open the last article on Home Page$")
    public void i_open_the_last_article_on_Home_Page() throws Throwable {
    		articleList = new ArticleListingPage(this.driver);
    		articleTitle = articleList.openLastArticleInView();
    }
    
    @Then("^I validate the basic view of Article Content$")
    public void i_validate_the_basic_view_of_Article_Content() throws Throwable {
        //Validate article title
    		articlePage = new ArticlePage(this.driver);
    		articlePage.assertArticleHeading(articleTitle);
    		
    		//Validate the tab title, text-to-speech, bookmark, share option, back button
    		articlePage.assertOnDetailsPage();
    		
    		//Validate article image(if present)
    		//Validate article author
    		//Validate article publish date
    		//Validate article content(basic view)
    		//Validate Internal recommendation view
    		//Validate Outbrain recommendation view
    		
    }

    @And("^capture the dimension of elements on Article Detail Page$")
    public void capture_the_dimension_of_elements_on_Article_Detail_Page() throws Throwable {
	    	articleContentDimension = new HashMap<String, Integer>();
	    	
	    	/*0 = False, 1 = True for compulsory fields 
	    	 * Eg. Headline, 
	    	 * Navigation Bar Title, 
	    	 * Bookmark, 
	    	 * Text to Speech, 
	    	 * Share Button
	    	 * Publish Date/Time
	    	 * Internal Recommendations
	    	 * External Recommendations
	    	*/
	    	articleContentDimension.put("resetReqDimension", 1);
	    	
	    	articleContentDimension.put("textXStart", 0);
	    	articleContentDimension.put("textWidth", 0);
	    
	    	articleContentDimension.put("backBtnXStart", 0);
	    	articleContentDimension.put("backBtnYStart", 0);
	    	articleContentDimension.put("backBtnWidth", 0);
	    	articleContentDimension.put("backBtnHeight", 0);
	    	
	    	articleContentDimension.put("navigationTitleXStart", 0);
	    	articleContentDimension.put("navigationTitleYStart", 0);
	    	
	    	articleContentDimension.put("textToSpeechXStart", 0);
	    	articleContentDimension.put("textToSpeechYStart", 0);
	    	articleContentDimension.put("textToSpeechWidth", 0);
	    	articleContentDimension.put("textToSpeechHeight", 0);
	    	
	    	articleContentDimension.put("bookmarkXStart", 0);
	    	articleContentDimension.put("bookmarkYStart", 0);
	    	articleContentDimension.put("bookmarkWidth", 0);
	    	articleContentDimension.put("bookmarkHeight", 0);
	    	
	    	articleContentDimension.put("shareXStart", 0);
	    	articleContentDimension.put("shareYStart", 0);
	    	articleContentDimension.put("shareWidth", 0);
	    	articleContentDimension.put("shareHeight", 0);
	    	
	    	articleContentDimension.put("timeWidth", 0);
	    	articleContentDimension.put("timeHeight", 0);
	    	
	    	articleContentDimension.put("publishInfoWidth", 0);
	    	articleContentDimension.put("publishInfoXStart", 0);
	    	
	    	articleContentDimension.put("internalRecommendationTitleWidth", 0);
	    	articleContentDimension.put("internalRecommendationTitleHeight", 0);
	    	articleContentDimension.put("internalRecommendationArticleTitleWidth", 0);
	    	articleContentDimension.put("internalRecommendationArticleTitleXStart", 0);
	    	articleContentDimension.put("internalRecommendationArticlePublishInfoXStart", 0);
	    	articleContentDimension.put("internalRecommendationArticlePublishInfoWidth", 0);
	    	articleContentDimension.put("internalRecommendationArticlePublishInfoHeight", 0);
	    	
	    	articleContentDimension.put("externalRecommendationTitleWidth", 0);
	    	articleContentDimension.put("externalRecommendationTitleHeight", 0);
	    	
	    	//For Main Image/Video in view
	    	articleContentDimension.put("resetMediaDimension", 1);
	    	
	    	articleContentDimension.put("mediaXStart", 0);
	    	articleContentDimension.put("mediaWidth", 0);
	    	articleContentDimension.put("mediaHeight", 0);
	    	
	    	//For Author Name 
	    	articleContentDimension.put("resetAuthorDimension", 1);
	    	
	    	//For Premium Article Tag
	    	articleContentDimension.put("resetPremiumIcnDimension", 1);
	    	
	    	articleContentDimension.put("sectionLabelHeight", 0);
	    	articleContentDimension.put("xRowStart", 0);
	    	articleContentDimension.put("xRowEnd", 0);
	    	articleContentDimension.put("firstRowArticleWidth", 0);
	    	articleContentDimension.put("secondRowArticleWidth", 0);
	    	articleContentDimension.put("firstRowImageVideoHeight", 0);
	    	articleContentDimension.put("secondRowImageVideoHeight", 0);
	    	articleContentDimension.put("thirdRowOnwardsImageVideoHeight", 0);
	    	articleContentDimension.put("thirdRowOnwardsImageVideoWidth", 0);
	    	articleContentDimension.put("removeFromHomeBtnHeight", 0);
	    	articleContentDimension.put("removeFromHomeBtnWidth", 0);
	    	articleContentDimension.put("removeFromHomeBtnXAxisStart", 0);
    }

    @Then("^I bookmark the article in view$")
    public void i_bookmark_the_article_in_view() throws Throwable {
        //Bookmark the article
    }

    @Then("^Keep Scrolling to navigate and bookmark the articles on Home Page$")
    public void keep_Scrolling_to_navigate_and_bookmark_the_articles_on_Home_Page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
    

//    @When("^I open each article in STNow$")
//    public void i_open_each_article_in_STNow() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^I validate all articles are opened with respective titles and publish dates$")
//    public void i_validate_all_articles_are_opened_with_respective_titles_and_publish_dates() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^I navigate till end of STNow section$")
//    public void i_navigate_till_end_of_STNow_section() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^I open Recap hyperlink$")
//    public void i_open_Recap_hyperlink() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^Ensure it opens the previous day articles$")
//    public void ensure_it_opens_the_previous_day_articles() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^Click on Back button$")
//    public void click_on_Back_button() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Then("^Goto Home Tab in Home Page View$")
//    public void goto_Home_Tab_in_Home_Page_View() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
    
    
}
