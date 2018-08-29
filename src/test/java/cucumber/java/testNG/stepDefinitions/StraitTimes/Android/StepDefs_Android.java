package cucumber.java.testNG.stepDefinitions.StraitTimes.Android;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.java.testNG.DriverManager;
import cucumber.java.testNG.page_objects.Android;
import cucumber.java.testNG.page_objects.LoginPage;
import cucumber.java.testNG.stepDefinitions.StraitTimes.Web.StepDefs_Web;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class StepDefs_Android {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Android mobileApp;
    private List<WebElement> searchResults;

    static {
        log = Logger.getLogger(StepDefs_Web.class);
    }

    public StepDefs_Android() throws MalformedURLException {
    }

    @Given("^I want to launch the Straits Time site and accept the terms and conditions$")
    public void i_want_to_accept_the_terms_and_conditions() throws IOException, InterruptedException {
        mobileApp = new Android(this.driver);
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
    @And("^I want to verify the username$")
    public void i_want_to_verify_the_username() throws IOException, InterruptedException {
        mobileApp.verify_userName();

    }
    @And("^I want to click the latest tab$")
    public void i_want_to_click_the_latest_tab() throws IOException, InterruptedException {
        mobileApp.click_latest_tab();

    }
    @And("^I want to click the first article on the latest tab$")
    public void i_want_to_click_first_article_the_latest_tab() throws IOException, InterruptedException {
        mobileApp.click_first_article();

    }
    @And("^I want to verify the first article displayed on the latest tab$")
    public void i_want_to_verify_the_articles_displayed_on_latest_tab() throws IOException, InterruptedException {
        mobileApp.verify__first_article();

    }
    @Then("^I want to go back to the main page$")
    public void i_want_to_go_back_to_the_main_page() throws IOException, InterruptedException {
        mobileApp.back_button();

    }

    @Then("^I want to logout the application$")
    public void i_want_to_logout_the_application() throws IOException, InterruptedException {
        mobileApp.logout_app();

    }
}
