package com.sph.stepDefinitions.mobile;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.ArticleDetailPage;
import com.sph.pageObjects.mobile.ArticlePage;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.Mobile;
import com.sph.pageObjects.mobile.PrintEditionPage;
import com.sph.utilities.GenericNavigator;
import com.sph.listeners.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.net.MalformedURLException;


public class ArticleTitleVerification {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    HomePage homePage;
    ArticlePage articlePage;
    GenericNavigator genericNavigator;
    static {
    		//TODO: Check whether this correction is appropriate
    		log = Logger.getLogger(LoginLifecycleValidation.class);
    }

    public ArticleTitleVerification() throws MalformedURLException {
    }
    
    @Given("^I want to complete basic app installation new$")
    public void completeBasicAppInstallation() throws Throwable {
    		genericNavigator = new GenericNavigator(driver);
    		genericNavigator.completeBasicInstallConfig();
    }
      
    @When("^I captured the article title$")
    public void clickOnFirstArticle() throws IOException, InterruptedException {
    	homePage = new HomePage(this.driver);
    	homePage.getHeadlineOfTheArticle();
        	
    }
    @And("^I click on first article at the home page$")
    public void titleHeading() throws IOException, InterruptedException {       
    	
    	homePage.navigateToTopStoryOftheHomePage();
    }
      
    
    @Then("^I am at article details page$")
    public void verifyAtArticleDetailsPage() throws Throwable {
    		articlePage =new ArticlePage((AppiumDriver<MobileElement>) this.driver);
    		articlePage.assertOnDetailsPage();
    }
    
    

    @And("^Verify that article with same title is opened$")
    public void assertTitle() throws Throwable {
    	homePage.assertTitleOfTheHeadline();
    } 
    

}
