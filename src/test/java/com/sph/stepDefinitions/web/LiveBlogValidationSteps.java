package com.sph.stepDefinitions.web;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.sph.driverFactory.DriverManager;
import com.sph.listeners.Reporter;
import com.sph.pageObjects.web.LiveBlogPage;
import com.sph.pageObjects.web.PrintEditionPage;
import com.sph.pageObjects.web.Web;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LiveBlogValidationSteps {
	static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Web stWeb;
    private LiveBlogPage liveBlogPage;

    static {
        log = Logger.getLogger(LiveBlogValidationSteps.class);
    }

    public LiveBlogValidationSteps() throws MalformedURLException {
    	
    }
    
    @Given("^I want to launch the Straits Times website\\[Live Blog\\]$")
    public void i_want_to_launch_the_Straits_Times_website_Live_Blog() throws Throwable {
    		stWeb = new Web(this.driver);
		stWeb.launch_Strait_Times();
	    Reporter.addStepLog("User wants to launch the Straits Times website- Thread:" );
	    Reporter.addScenarioLog("User wants to launch the Straits Times website");
    }

    @Then("^I get the total number of articles published in default view of Live Blog$")
    public void i_get_the_total_number_of_articles_published_in_default_view_of_Live_Blog() throws Throwable {
    		liveBlogPage = new LiveBlogPage(this.driver);
		int totalLiveBlogArticle = liveBlogPage.verifyArticleURL();
		Assert.assertNotEquals(totalLiveBlogArticle, 0, "Total Article Published is unexpectedly zero(0)");
		Reporter.addStepLog("[The total number of articles published is: " + totalLiveBlogArticle +"] in Live Blog - Thread: " +Thread.currentThread().getId());
		Reporter.addScenarioLog("[The total number of articles published is: " + totalLiveBlogArticle +"] in Live Blog");
    }
    
    @Then("^I say Hurray!$")
    public void i_say_Hurray() throws Throwable {
        
    }
}
