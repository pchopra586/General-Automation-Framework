package com.sph.stepDefinitions.mobile;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.Mobile;
import com.sph.pageObjects.mobile.PrintEditionPage;
import com.sph.utilities.GenericNavigator;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PrintEditionPageValidation {
	static Logger log;
    WebDriver driver = DriverManager.getDriver();
    GenericNavigator genericNavigator;
    PrintEditionPage printEditionPage;
    
    static {
    		//TODO: Check whether this correction is appropriate
    		log = Logger.getLogger(PrintEditionPageValidation.class);
    }

    public PrintEditionPageValidation() throws MalformedURLException {
    }
    
    @Given("^I want to complete the basic app installation$")
    public void i_want_to_complete_the_basic_app_installation() throws Throwable {
    		genericNavigator = new GenericNavigator(driver);
    		genericNavigator.completeBasicInstallConfig();
    }

    @When("^I goto Print Edition Page$")
    public void i_goto_Print_Edition_Page() throws Throwable {
    		printEditionPage = new PrintEditionPage(driver);
    		printEditionPage.gotoPrintEditionPage();
    }

    @When("^I see the Instant Pre-download Pop Up$")
    public void i_see_the_Instant_Pre_download_Pop_Up() throws Throwable {
        printEditionPage.verifyInstantPreDownloadAlert();
    }

    @Then("^I close the Instant Pre-download pop up$")
    public void i_close_the_Instant_Pre_download_pop_up() throws Throwable {
        printEditionPage.closePrintEditionInstantDownloadAlert();
    }


    @Then("^I see the Push Notification pop up$")
    public void i_see_the_Push_Notification_pop_up() throws Throwable {
       printEditionPage.verifyPushNotifyPopUp();
    }

    @Then("^I close the Push Notification pop up$")
    public void i_close_the_Push_Notification_pop_up() throws Throwable {
        printEditionPage.closePrintEditionPushNotificationAlert();
    }

    @Then("^I validate the default view of Print Edition Page$")
    public void i_validate_the_default_view_of_Print_Edition_Page() throws Throwable {
        printEditionPage.verifyDefaultView();
    }

}
