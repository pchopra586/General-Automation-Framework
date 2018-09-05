package com.sph.StepDefinitions.Mobile;

import com.sph.DriverFactory.DriverManager;
import com.sph.PageObjects.Mobile;
import com.sph.StepDefinitions.Web.StepDefs_Web;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class StepDefs_Mobile {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Mobile mobileApp;
    private List<WebElement> searchResults;

    static {
        log = Logger.getLogger(StepDefs_Web.class);
    }

    public StepDefs_Mobile() throws MalformedURLException {
    }

    @Given("^I want to launch the Straits Time site and accept the terms and conditions$")
    public void i_want_to_accept_the_terms_and_conditions() throws IOException, InterruptedException {
        mobileApp = new Mobile(this.driver);
        mobileApp.accept_terms_and_conditions();

    }
    @When("^I want to skip the tutorials$")
    public void i_want_to_skip_the_tutorials() throws IOException, InterruptedException {
        mobileApp.skip_tutorials();

    }
    @And("^I want to click the hamburger menu$")
    public void i_want_to_click_the_hamburger_menu() throws IOException, InterruptedException {
        mobileApp.click_hamburger_menu();
    }
    @And("^I want to enter the login credentials$")
    public void i_want_to_enter_the_login_credentials() throws IOException, InterruptedException {
        mobileApp.enter_Login_Credentials();

    }
    @And("^I want to click the continue button$")
    public void i_want_to_click_the_continue_button() throws IOException, InterruptedException {
        mobileApp.click_continue_button();

    }

}
