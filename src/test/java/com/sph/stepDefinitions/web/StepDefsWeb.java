package com.sph.stepDefinitions.web;

import com.vimalselvam.cucumber.listener.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.web.Web;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Simple steps mapping class that always fails.
 * I've added it to show how failed story will look like in the final Cucumber HTML report.
 * @author jk
 */
public class StepDefsWeb {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Web loginPage;
    private List<WebElement> searchResults;

    static {
        log = Logger.getLogger(StepDefsWeb.class);
    }

    public StepDefsWeb() throws MalformedURLException {
    }


    @Given("^I want to launch the Straits Times website$")
    public void givenISearchFor() throws InterruptedException {
        loginPage = new Web(this.driver);
        loginPage.launch_Strait_Times();
        Reporter.addStepLog("User wants to launch the Straits Times website- Thread:" +Thread.currentThread().getId());
        Reporter.addScenarioLog("User wants to launch the Straits Times website");


    }
    @When("^I want to click on the login link$")
    public void i_want_to_click_the_login_link() throws IOException, InterruptedException {
        loginPage.login_StraitTimes();
        Reporter.addStepLog("User wants to click on the login link-Thread:" +Thread.currentThread().getId());
        Reporter.addScenarioLog("User wants to click on the login link");
    }

    @And("^I want to login the straits time site as (.+) and (.+)$")
    public void i_want_to_login_straits_time(String UserName, String Password) throws IOException, InterruptedException {
        loginPage.enter_Login_Credentials(UserName, Password);
        Reporter.addStepLog("User wants to login the straits time site");
        Reporter.addScenarioLog("User wants to login the straits time site");

    }
    @And("^I want to verify the user has been logged in as (.+)$")
    public void i_want_to_verify_that_the_user_is_logged_in(String UserName) throws IOException, InterruptedException {
        loginPage.user_logged_in(UserName);
        System.err.println("This is another testng simple test - Thread: " + Thread.currentThread().getId());
        Reporter.addStepLog("User wants to verify the user has been logged");
        Reporter.addScenarioLog("User wants to verify the user has been logged");

    }

    @Then("^I want to verify the main article page$")
    public void i_want_to_verify_the_main_article_page() throws IOException, InterruptedException {
        loginPage.read_main_artcle();
        loginPage.verify_main_article_page();
        Reporter.addStepLog("User wants to verify the main article page");
        Reporter.addScenarioLog("User wants to verify the main article page");

    }
    @Then("^I want to logout StraiTimes app")
    public void i_want_to_logout_strait_times_app() throws IOException, InterruptedException {
        loginPage.logout();
        Reporter.addStepLog("User wants to logout StraiTimes app");
        Reporter.addScenarioLog("User wants to logout StraiTimes app");

    }
}