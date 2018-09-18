package com.sph.stepDefinitions.mobile;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.AccountPage;
import com.sph.pageObjects.mobile.ArticleDetailPage;
import com.sph.pageObjects.mobile.BasePage;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.IntroductionPage;
import com.sph.pageObjects.mobile.LatestTabPage;
import com.sph.pageObjects.mobile.LicensePage;
import com.sph.pageObjects.mobile.LoginPage;
import com.sph.pageObjects.mobile.MenuPage;
import com.sph.pageObjects.mobile.Mobile;
import com.sph.pageObjects.mobile.NotificationsPage;
import com.sph.pageObjects.mobile.SettingsPage;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.GenericNavigator;
import com.sph.utilities.Constant.MENU;
import com.sph.utilities.Constant.SETTINGS_MENU;
import com.sph.utilities.Constant.TAB;
import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class StepDefsLogin {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Mobile mobileApp;
//    private List<WebElement> searchResults;
//    
//    private NotificationsPage notify;
//    private LicensePage license;
//    
//    private DeviceActions util;
//    private IntroductionPage intro;
//    
//    private MenuPage menu;
//    private LoginPage login;
//    
//    private SettingsPage settings;
//    private AccountPage account;
//    
//    private BasePage basePage;
//    private LatestTabPage latest;
//    
//    private ArticleDetailPage article;
//    private String expectedArticleTitle = "";

    static {
//        log = Logger.getLogger(StepDefsWeb.class);
    		//TODO: Check whether this correction is appropriate
    		log = Logger.getLogger(StepDefsLogin.class);
    }

    public StepDefsLogin() throws MalformedURLException {
    }

    @Given("^I want to launch the Straits Time site and accept the terms and conditions$")
    public void i_want_to_accept_the_terms_and_conditions() throws IOException, InterruptedException {
        mobileApp = new Mobile(this.driver);
        mobileApp.accept_terms_and_conditions();
        
//      notify = new NotificationsPage(driver);		
//		notify.acceptNotificationAfterInstall();
//		
//		license = new LicensePage(driver);
//		license.acceptAgreement();
		
        Reporter.addStepLog("User wants to launch the Straits Time site and accept the terms and conditions");
        Reporter.addScenarioLog("User wants to launch the Straits Time site and accept the terms and conditions");

    }
    @When("^I want to skip the tutorials$")
    public void i_want_to_skip_the_tutorials() throws IOException, InterruptedException {
        mobileApp.skip_tutorials();
    		
//    		util = new DeviceActions(driver);		
//		intro = new IntroductionPage(driver);
//		
//    		util.swipeHorizontal("left");
//    		intro.skipIntro();
    		
        Reporter.addStepLog("User wants to skip the tutorials");
        Reporter.addScenarioLog("User wants to skip the tutorials");

    }
    @And("^I want to click the hamburger menu$")
    public void i_want_to_click_the_hamburger_menu() throws IOException, InterruptedException {
        mobileApp.click_hamburger_menu();
    		
//    		menu = new MenuPage(driver);
//    		
//    		util.closeInterstitialAd();
//    		
//		menu.clickOnMenu();
    	
        Reporter.addStepLog("User wants to click the hamburger menu");
        Reporter.addScenarioLog("User wants to click the hamburger menu");
    }

    @When("^I want to enter the login credentials as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_want_to_enter_the_login_credentials_as_and(String username, String password) throws Throwable {
        mobileApp.enter_Login_Credentials(username, password);
    	
//    		driver = menu.gotoMenu(Constant.MENU.LOGIN);
//    		login = new LoginPage(driver);
//    		login.appLogin(username, password);
//    	
    		Reporter.addStepLog("User wants to enter the login credentials");
    		Reporter.addScenarioLog("User wants to enter the login credentials");
    }
    
    @And("^I want to click the continue button$")
    public void i_want_to_click_the_continue_button() throws IOException, InterruptedException {
//        mobileApp.click_continue_button();
        
        Reporter.addStepLog("User wants to click continue button");
		Reporter.addScenarioLog("User wants to click continue button");
    }
    
    @Then("^I want to verify the logged-in user as \"([^\"]*)\"$")
    public void i_want_to_verify_the_logged_in_user_as(String loggedInUser) throws Throwable {
    		mobileApp.verifyLoggedInUser(loggedInUser);
    		
//    		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
//		
//		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
//			menu.clickOnMenu();
//		}
//    		
//    		menu.verifyLoggedInUser(loggedInUser);
    		Reporter.addStepLog("User wants to verify the logged in user");
    		Reporter.addScenarioLog("User wants to verify the logged in user");
    }
    
//    @Then("^I want to goto Home Page$")
//    public void i_want_to_goto_Home_Page() throws Throwable {
//    		mobileApp.gotoHomePage();
//    	
////        driver = menu.gotoMenu(MENU.HOME_PAGE);
//    	
//        Reporter.addStepLog("User wants to goto Home Page");
//		Reporter.addScenarioLog("User wants to goto Home Page");
//    }
//
//    @Then("^I want to goto Latest Tab$")
//    public void i_want_to_goto_Latest_Tab() throws Throwable {
//    		mobileApp.openLatestTab();
//    		
////        basePage = new BasePage(driver);
////        driver = basePage.gotoTab(TAB.LATEST);
//        Reporter.addStepLog("User wants to goto Home Page");
//		Reporter.addScenarioLog("User wants to goto Home Page");
//    }

//    @Then("^I want to open the first article$")
//    public void i_want_to_open_the_first_article() throws Throwable {
//    		mobileApp.read_title_article_first_article_latest();
//    	
////    		int first = 1;
////        latest = new LatestTabPage(driver);
////        expectedArticleTitle = latest.openArticle(first);
//        Reporter.addStepLog("User wants to goto Home Page");
//		Reporter.addScenarioLog("User wants to goto Home Page");
//    }
//
//    @Then("^I want to verify the article title$")
//    public void i_want_to_verify_the_article_title() throws Throwable {
//    		mobileApp.read_title_article_first_article_latest();
//    		
////        article = new ArticleDetailPage(driver);
////        article.verifyArticleTitle(expectedArticleTitle);
//        Reporter.addStepLog("User wants to goto Home Page");
//		Reporter.addScenarioLog("User wants to goto Home Page");
//    }
//
//    @Then("^I want to go back to previous view$")
//    public void i_want_to_go_back_to_previous_view() throws Throwable {
////    		article.gotoPreviousView();
//    		
//    		Reporter.addStepLog("User wants to goto Home Page");
//		Reporter.addScenarioLog("User wants to goto Home Page");
//    }
    
    @Then("^I want to goto Settings menu$")
    public void i_want_to_goto_Settings_menu() throws Throwable {
        mobileApp.gotoSettingsMenu();
    	
    		//menu.gotoMenu(MENU.SETTINGS);
        
        Reporter.addStepLog("User wants to goto Settings Menu");
		Reporter.addScenarioLog("User wants to goto Settings Menu");
    }

    @Then("^I want to goto Account Settings$")
    public void i_want_to_goto_Account_Settings() throws Throwable {
    		mobileApp.gotoAccountSettings();
    	
//        settings = new SettingsPage(driver);
//        settings.gotoSettings(SETTINGS_MENU.ACCOUNT);
        
        Reporter.addStepLog("User wants to goto Account Settings");
		Reporter.addScenarioLog("User wants to goto Account Settings");
    }

    @Then("^I want to logout the user$")
    public void i_want_to_logout_the_user() throws Throwable {
    		mobileApp.logout_app();
    		
//    		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
//		
//		if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
//    			menu.logout();
//		}
//		else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
//			AccountPage account = new AccountPage(driver);
//			account.logout();
//		}
		Reporter.addStepLog("User wants to logout");
		Reporter.addScenarioLog("User wants to logout");
    }

}
