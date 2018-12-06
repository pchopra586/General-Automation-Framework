package com.sph.stepDefinitions.mobile;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.AccountPage;
import com.sph.pageObjects.mobile.IntroductionPage;
import com.sph.pageObjects.mobile.LicensePage;
import com.sph.pageObjects.mobile.LoginPage;
import com.sph.pageObjects.mobile.MenuPage;
import com.sph.pageObjects.mobile.NotificationsPage;
import com.sph.pageObjects.mobile.SettingsPage;
import com.sph.utilities.Constant;
import com.sph.utilities.DeviceActions;
import com.sph.listeners.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class LoginLifecycleValidation {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    
    private LicensePage license;
	private IntroductionPage intro;
	private NotificationsPage notify;
	private DeviceActions util;
	private MenuPage menu;
	private LoginPage login;
	private SettingsPage settings;
	private AccountPage account;
	private Capabilities capabilities;
    static {
    		//TODO: Check whether this correction is appropriate
    		log = Logger.getLogger(LoginLifecycleValidation.class);
    }

    public LoginLifecycleValidation() throws MalformedURLException {
    		this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
    }

    @Given("^I want to launch the Straits Time site and accept the terms and conditions$")
    public void i_want_to_accept_the_terms_and_conditions() throws IOException, InterruptedException {
//        mobileApp = new Mobile(this.driver);
//        mobileApp.accept_terms_and_conditions();
		
		notify = new NotificationsPage(this.driver);
		license = new LicensePage(this.driver);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		notify.acceptNotificationAfterInstall();
		license.acceptAgreement();
        
		log.info("Test Started");
        Reporter.addStepLog("User wants to launch the Straits Time site and accept the terms and conditions");
        Reporter.addScenarioLog("User wants to launch the Straits Time site and accept the terms and conditions");

    }
    @When("^I want to skip the tutorials$")
    public void i_want_to_skip_the_tutorials() throws IOException, InterruptedException {
//        mobileApp.skip_tutorials();
    		intro = new IntroductionPage(this.driver);
    		util = new DeviceActions(this.driver);		
    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    		util.swipeHorizontal("left");
    		intro.skipIntro();
    		
        Reporter.addStepLog("User wants to skip the tutorials");
        Reporter.addScenarioLog("User wants to skip the tutorials");

    }
    @And("^I want to click the hamburger menu$")
    public void i_want_to_click_the_hamburger_menu() throws IOException, InterruptedException {
//        mobileApp.click_hamburger_menu();
    		menu = new MenuPage(this.driver);
    		menu.clickOnMenu().gotoMenu(Constant.MENU.LOGIN);
    		
        Reporter.addStepLog("User wants to click the hamburger menu");
        Reporter.addScenarioLog("User wants to click the hamburger menu");
    }

    @When("^I want to enter the login credentials as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_want_to_enter_the_login_credentials_as_and(String username, String password) throws Throwable {
//        mobileApp.enter_Login_Credentials(username, password);
    		login = new LoginPage(this.driver);
    		login.appLogin(username, password);
    		
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
//    		mobileApp.verifyLoggedInUser(loggedInUser);
    		menu.clickOnMenu();
    		menu.verifyLoggedInUser(loggedInUser);
    	
    		Reporter.addStepLog("User wants to verify the logged in user");
    		Reporter.addScenarioLog("User wants to verify the logged in user");
    }
    
    @Then("^I want to goto Settings menu$")
    public void i_want_to_goto_Settings_menu() throws Throwable {
//        mobileApp.gotoSettingsMenu();
    		menu.clickOnMenu().gotoMenu(Constant.MENU.SETTINGS);
        
        Reporter.addStepLog("User wants to goto Settings Menu");
		Reporter.addScenarioLog("User wants to goto Settings Menu");
    }

    @Then("^I want to goto Account Settings$")
    public void i_want_to_goto_Account_Settings() throws Throwable {
//    		mobileApp.gotoAccountSettings();
    		settings = new SettingsPage(this.driver);
    		settings.gotoSettings(Constant.SETTINGS_MENU.ACCOUNT);
        
        Reporter.addStepLog("User wants to goto Account Settings");
		Reporter.addScenarioLog("User wants to goto Account Settings");
    }

    @Then("^I want to logout the user$")
    public void i_want_to_logout_the_user() throws Throwable {
//    		mobileApp.logout_app();
    	
    		if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
	    		account = new AccountPage(this.driver);
	    		account.logout();
    		}else if(capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
        		menu.logout();
    		}
		Reporter.addStepLog("User wants to logout");
		Reporter.addScenarioLog("User wants to logout");
    }

}
