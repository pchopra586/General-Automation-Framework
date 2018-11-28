package com.sph.stepDefinitions.mobile;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.mobile.ArticlePage;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.pageObjects.mobile.IntroductionPage;
import com.sph.pageObjects.mobile.Mobile;
import com.sph.utilities.GenericNavigator;
import com.sph.listeners.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;


import java.io.IOException;
import java.net.MalformedURLException;


public class TermsOfUsePageValidation {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    IntroductionPage introPage;
        
    static {
    		//TODO: Check whether this correction is appropriate
    		log = Logger.getLogger(LoginLifecycleValidation.class);
    }

    public TermsOfUsePageValidation() throws MalformedURLException {
    }

    @Given("^App is freshly installed and launched$")
    public void i_want_to_install_App() throws IOException, InterruptedException {
    	introPage =new IntroductionPage(this.driver);
        Reporter.addStepLog("User wants to freshly install the app");
     

    }
    
    @When("^decline and accept buttons are visible$")
    public void i_want_to_decline() throws IOException, InterruptedException {
       
    	introPage.verifyButtonsOnTermsOfUsePage();
        Reporter.addStepLog("User clicks on decline button");
       

    }
    
    @Then("^I validate the functionality of cancel agreement button$")
    public void i_want_to_verify_cancel_agreement_Functionality() throws Throwable {
    	introPage.declineAgreement();
    
    }
    
    

    
    

}
