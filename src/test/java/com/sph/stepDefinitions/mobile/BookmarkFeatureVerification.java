package com.sph.stepDefinitions.mobile;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.ArticleDetailPage;
import com.sph.pageObjects.mobile.ArticlePage;
import com.sph.pageObjects.mobile.BookmarkPage;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.LoginPage;
import com.sph.pageObjects.mobile.MenuPage;
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

import com.sph.utilities.Constant;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;


public class BookmarkFeatureVerification {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(this.driver) ;
    ArticlePage articlePage=new ArticlePage(this.driver);;
    LoginPage loginPage=new LoginPage(this.driver);
    BookmarkPage bookmarkPage =new BookmarkPage(this.driver);
    MenuPage menuPage = new MenuPage(this.driver);
  
    GenericNavigator genericNavigator;
    static {
    		//TODO: Check whether this correction is appropriate
    		log = Logger.getLogger(LoginLifecycleValidation.class);
    }

    public BookmarkFeatureVerification() throws MalformedURLException {
    }
    
    @Given("^I want to complete basic app installation \\[Bookmark\\]$")
    public void completeBasicAppInstallation() throws Throwable {
    		genericNavigator = new GenericNavigator(driver);
    		genericNavigator.completeBasicInstallConfig();
    		
    }
      

    @When("^I click on premium article at the home page \\[Bookmark\\]$")
    public void openPremiumArticle() throws IOException, InterruptedException {       

    	homePage.openArticle(4, Constant.PREMIUM);
    	
    }
      
    
    @Then("^I am at article details page \\[Bookmark\\]$")
    public void verifyAtArticleDetailsPage() throws Throwable {
 
    		articlePage.assertOnDetailsPage();
    }
    
    @And("^Verify that article with same title is opened \\[Bookmark\\]$")
    public void assertTitle() throws Throwable {
    	homePage.assertTitleOfTheHeadline();
    } 
    

    @When("^I click on bookmark icon \\[Bookmark\\]$")
    public void clickOnBookmarkIcon() throws IOException, InterruptedException {

      	articlePage.clickOnBookmarkIcon();     	
    }  
  
    
    @Then("^Bookmark icon is highlighted \\[Bookmark\\]$")
    public void verifyBookmarkIconState() throws IOException, InterruptedException {
  
    	articlePage.isBookmarkIconSelected();     	
    } 
    
    @When("^I click on back button \\[Bookmark\\]$")
    public void clickOnBackButton() throws IOException, InterruptedException {
  
    	articlePage.goBackToListingPage(1);       	
    }
   
    @Then("^We are navigated back to the homepage \\[Bookmark\\]$")
    public void navigateToHomePage() throws IOException, InterruptedException {
    	homePage.onHomePage();        	
    } 

    @When("^I open hamburgerMenu \\[Bookmark\\]$")
    public void openHamburgerMenu() throws IOException, InterruptedException {
  
    	menuPage.clickOnMenu(); 	
    }
   
    @Then("^Click on bookmark link \\[Bookmark\\]$")
    public void openBookmarkedPage() throws IOException, InterruptedException {
    
    	menuPage.gotoMenu(Constant.MENU.BOOKMARK );   	
    } 
    
    @And("^verify screen title \\[Bookmark\\]$")
    public void verifyScreenTitle() throws Throwable {
    	
    	bookmarkPage.verifyScreenTitle();
    }
    
    @When("^I open bookmarked premium article \\[Bookmark\\]$")
    public void openBookmarkedPremiumArticle() throws IOException, InterruptedException {
    	homePage.openArticle(4, Constant.PREMIUM);    	
    }
   
    @Then("^Verify section Title \\[Bookmark\\]$")
    public void verifySectionTitle() throws IOException, InterruptedException {
    	articlePage.verifySectionTitle(Constant.BOOKMARK);	
    }
 
    @Then("^Verify default Bookmarkpage is displayed \\[Bookmark\\]$")
    public void verifyDefaultBookmarkPage() throws IOException, InterruptedException {
       	bookmarkPage.verifyDefaultBookmarkPage();
    }
    @Then("^Verify premium article is loaded with limited access \\[Bookmark\\]$")
    public void verifyPremiumArticleAccess() throws IOException, InterruptedException {
    
    	articlePage.verifyPremiumArticleAccess(4);
    }
    
    @When("^I click on free article at the home page \\[Bookmark\\]$")
    public void openFreeArticle() throws IOException, InterruptedException {
    	homePage.openArticle(4, Constant.FREE); 
    }
    
    @When("^I open bookmarked free article \\[Bookmark\\]$")
    public void openBookmarkedFreeArticle() throws IOException, InterruptedException {
    	homePage.openArticle(4, Constant.FREE);    	
    }
    
    @Then("^Verify free article is accessible to anonymous user \\[Bookmark\\]$")
    public void verifyfreeArticleAccess() throws IOException, InterruptedException {
    	articlePage.verifyArticleAccess();
    }
      
    @Then("^I click on HomePage link \\[Bookmark\\]$")
    public void goToHomePage() throws IOException, InterruptedException {
    	menuPage.gotoMenu(Constant.MENU.HOME_PAGE); 
    }
    
    @And("^Note the number of articles bookmarked \\[Bookmark\\]$")
    public void createListOfBookmarkedArticle() throws IOException, InterruptedException {
    	//bookmarkPage=new BookmarkPage(this.driver);;
    	
    }
    @And("^Observe the number of articles displayed under bookmarked section \\[Bookmark\\]$")
    public void compareBookmarkedArticleList() throws IOException, InterruptedException {
    	//bookmarkPage=new BookmarkPage(this.driver);;
    	
    }
    
    @And("^I open and verify Bookmarked Article \\[Bookmark\\]$")
    public void openAndVerifyBookmarkedArticle() throws IOException, InterruptedException {
    	//bookmarkPage=new BookmarkPage(this.driver);;
    	
    }
        
   }
