package com.sph.stepDefinitions.mobile;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.Mobile;
import com.sph.pageObjects.mobile.PrintEditionCalendar;
import com.sph.pageObjects.mobile.PrintEditionPage;
import com.sph.utilities.GenericNavigator;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PrintEditionPageValidation {
	static Logger log;
    WebDriver driver = DriverManager.getDriver();
    GenericNavigator genericNavigator;
    PrintEditionPage printEditionPage;
    HomePage homePage;
    PrintEditionCalendar calendarIcn;
    
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
    
    @Given("^I am on Home Page$")
    public void i_am_on_Home_Page() throws Throwable {
    		homePage = new HomePage(driver);
		homePage.gotoHomePage();  
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
    
    @Then("^I validate the default view of Print Edition Page without pop-up$")
    public void i_validate_the_default_view_of_Print_Edition_Page_without_pop_up() throws Throwable {
    		printEditionPage.verifyDefaultView();
    }
    
    @And("^I click on Calendar icon in the view$")
    public void i_click_on_Calendar_icon_in_the_view() throws Throwable {
        printEditionPage.openCalendarView();
    }

    @Then("^I verify the Archived Print Edition dates from Calendar$")
    public void i_verify_the_Archived_Print_Edition_dates_from_Calendar() throws Throwable {
    		calendarIcn = new PrintEditionCalendar(this.driver);
    		calendarIcn.verifyCalendarView();
    }
    
    @Given("^Goto Today's Print Edition$")
    public void goto_Today_s_Print_Edition() throws Throwable {
    		calendarIcn.gotoPrintEdition(0);
    }

    @Then("^I validate the Tab View and image dimension$")
    public void i_validate_the_Tab_View_and_image_dimension() throws Throwable {
        printEditionPage.verifyTabLayout("dummy");
    }

}
