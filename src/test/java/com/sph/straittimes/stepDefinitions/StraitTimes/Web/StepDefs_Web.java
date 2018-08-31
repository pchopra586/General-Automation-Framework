package com.sph.straittimes.stepDefinitions.StraitTimes.Web;

import com.sph.straittimes.page_objects.Web;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.sph.straittimes.DriverFactory.DriverManager;

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
public class StepDefs_Web {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Web loginPage;
    private List<WebElement> searchResults;

    static {
        log = Logger.getLogger(StepDefs_Web.class);
    }

    public StepDefs_Web() throws MalformedURLException {
    }

    @Given("^I want to launch the Straits Times website$")
    public void givenISearchFor() throws InterruptedException {
        log.info("Given I launch the Strait Times Webiste");
        loginPage = new Web(this.driver);
        loginPage.launch_Strait_Times();

    }
    @When("^I want to click on the login link$")
    public void i_want_to_click_the_login_link() throws IOException, InterruptedException {
        log.info("I want to click on the login link");
        loginPage.login_StraitTimes();
    }

    @And("^I want to login the straits time site as (.+) and (.+)$")
    public void i_want_to_login_straits_time(String UserName, String Password) throws IOException, InterruptedException {
        loginPage.enter_Login_Credentials(UserName, Password);

    }
    @And("^I want to verify the user has been logged in as (.+)$")
    public void i_want_to_verify_that_the_user_is_logged_in(String UserName) throws IOException, InterruptedException {
        loginPage.user_logged_in(UserName);

    }

    @Then("^I want to verify the main article page$")
    public void i_want_to_verify_the_main_article_page() throws IOException, InterruptedException {
        loginPage.read_main_artcle();
        loginPage.verify_main_article_page();

    }
    @Then("^I want to logout StraiTimes app")
    public void i_want_to_logout_strait_times_app() throws IOException, InterruptedException {
        loginPage.logout();

    }
}