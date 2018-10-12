package com.sph.stepDefinitions.web;

import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.sph.driverFactory.DriverManager;
import com.sph.listeners.Reporter;
import com.sph.pageObjects.web.PrintEditionPage;
import com.sph.pageObjects.web.Web;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PrintEditionValidationSteps {
	static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Web stWeb;
    private PrintEditionPage printEditionPage;

    static {
        log = Logger.getLogger(PrintEditionValidationSteps.class);
    }

    public PrintEditionValidationSteps() throws MalformedURLException {
    	
    }
    
    @Given("^I want to launch the Straits Times website\\[Print Edition\\]$")
    public void i_want_to_launch_the_Straits_Times_website_Print_Edition() throws Throwable {
	    	stWeb = new Web(this.driver);
		stWeb.launch_Strait_Times();
	    Reporter.addStepLog("User wants to launch the Straits Times website- Thread:" );
	    Reporter.addScenarioLog("User wants to launch the Straits Times website");
	}
	
    @When("^I click on Print Edition link$")
    public void i_click_on_Print_Edition_link() throws InterruptedException {
    		stWeb.gotoPrintEdition();
        Reporter.addStepLog("User wants to Goto Print Edition Web Copy Thread:");
        Reporter.addScenarioLog("User wants to Goto Print Edition Web Copy");
    }

    @Then("^I get the total number of articles published in Print Edition$")
    public void i_get_the_total_number_of_articles_published_in_Print_Edition() throws InterruptedException {
    		printEditionPage = new PrintEditionPage(this.driver);
    		int totalArticlePublishedToday = printEditionPage.getTotalStoryPublished();
    		Assert.assertNotEquals(totalArticlePublishedToday, 0, "Total Article Published is unexpectedly zero(0)");
    		Reporter.addStepLog("[The total number of articles published is: " + totalArticlePublishedToday +"] in Print Edition - Thread: " +Thread.currentThread().getId());
        Reporter.addScenarioLog("[The total number of articles published is: " + totalArticlePublishedToday +"] in Print Edition");   		
    }
}
