package com.sph.stepDefinitions.mobile;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.PrintEditionCalendar;
import com.sph.pageObjects.mobile.PrintEditionPage;
import com.sph.utilities.Constant;
import com.sph.utilities.GenericNavigator;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuildSanityValidation {
	static Logger log;
    WebDriver driver = DriverManager.getDriver();
    GenericNavigator genericNavigator;
    HomePage homePage = null;
    PrintEditionCalendar calendarIcn;
    
    private static Map<String, Integer> sectionDimension = null;
    
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
    
    @Then("^I open the last article on Home Page$")
    public void i_open_the_last_article_on_Home_Page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^capture the dimension of elements on Article Detail Page$")
    public void capture_the_dimension_of_elements_on_Article_Detail_Page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I bookmark the article in view$")
    public void i_bookmark_the_article_in_view() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
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
