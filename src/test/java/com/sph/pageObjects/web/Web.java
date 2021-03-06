package com.sph.pageObjects.web;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.pageObjects.mobile.HomePage;
import com.sph.utilities.Constant;
import com.sph.utilities.WebElements;

import org.apache.log4j.Logger;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;


/**
 * Created by jay on 10/04/14.
 */
public class Web {

    private WebDriver driver;
    private WebDriverWait wait;
    private String Read_Story_Headline;
    Logger log = Logger.getLogger(Web.class);
    String browserName = LocalWebDriverListener.browserName;
    String stWebURL = "https://www.straitstimes.com/?adbypass=topspecial_skinning_topoverlay";

    @FindBy(xpath = "//div[contains(@id,'ebAd')]/iframe")
    private WebElement iframe;

    @FindBy(xpath = WebElements.CLOSE_AD)
    private WebElement close_ad;

    @FindBy(xpath = WebElements.LOGIN_LINK)
    private WebElement login_link;

    @FindBy(id = WebElements.LOGIN_ID)
    private WebElement login_id;

    @FindBy(id = WebElements.PASSWORD)
    private WebElement password;

    @FindBy(className = WebElements.SIGN_IN)
    private WebElement signIn_button;

    @FindBy(xpath = WebElements.VERIFY_USERNAME)
    private WebElement verify_username;

    @FindBy(xpath = WebElements.STORY_HEADLINE)
    private WebElement story_headline;

    @FindBy(xpath = WebElements.READ_HEADLINE)
    private WebElement read_headline;

    @FindBy(xpath = WebElements.MAIN_ARTICLE_HEADING)
    private WebElement main_article_heading;

    @FindBy(xpath = WebElements.LOG_OUT)
    private WebElement log_out;

    @FindBy(className = WebElements.HAMBURGER_MENU_MOBILE_WEB)
    private WebElement hamburger_menu_mobile_web;

    @FindBy(xpath = WebElements.USER_MENU_MOBILE_WEB)
    private WebElement user_menu_mobile_web;
    
    @FindBy(className = WebElements.PRINT_EDITION)
    private WebElement printEdition; 

    public Web(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void launch_Strait_Times() throws InterruptedException {
        this.driver.get(stWebURL);
        try {
            if (browserName.equalsIgnoreCase("CHROMELOCAL") || browserName.equalsIgnoreCase("SAFARILOCAL")) {
                this.driver.manage().window().maximize();
            }
        }
        catch(Exception e)
        {
        		log.error("Maximize not required");
        }
        //this.driver.get("https://www.straitstimes.com");
    }

    public void login_StraitTimes() throws InterruptedException {
        Thread.sleep(5000);
        //driver.switchTo().frame(0);
        try {
            if (iframe.isDisplayed()) {
                driver.switchTo().frame((WebElement) iframe);
            }
        } catch (Exception e) {
        		log.error("Element not found");
        }
        try {
            if (close_ad.isDisplayed()) {
                //((JavascriptExecutor) driver).executeScript("arguments[0].click();", close_ad);
                close_ad.click();
                try {
                    if (browserName.equalsIgnoreCase("SAFARILOCAL")||browserName.equalsIgnoreCase("CHROMELOCAL")) {
                        driver.switchTo().defaultContent();
                    }
                }
                catch (Exception e)
                {
                		log.error("Element Not found");
                }
            }
        } catch (Exception e) {
        		log.error("Ad is not displayed");
        }

            /*Actions actions = new Actions(driver);
            actions.moveToElement(login_link).click().build().perform();*/
            Thread.sleep(3000);
            try {
                if (browserName.equalsIgnoreCase("ANDROIDREMOTEBROWSER") || browserName.equalsIgnoreCase("ANDROIDLOCALBROWSER") || browserName.equalsIgnoreCase("IOSREMOTEBROWSER"))
                    ;
                {
                    hamburger_menu_mobile_web.click();
                    Thread.sleep(5000);
                    user_menu_mobile_web.click();
                }
            }
            catch (Exception e)
            {
            		log.error("Element not found");
            }
        Thread.sleep(2000);
        
            login_link.click();
    }
    
    public void closeAd() throws InterruptedException {
	    	try {
	            if (close_ad.isDisplayed()) {
	                //((JavascriptExecutor) driver).executeScript("arguments[0].click();", close_ad);
	                close_ad.click();
	                try {
	                    if (browserName.equalsIgnoreCase("SAFARILOCAL")||browserName.equalsIgnoreCase("CHROMELOCAL")) {
	                        driver.switchTo().defaultContent();
	                    }
	                }
	                catch (Exception e)
	                {
	                		log.error("Element Not found");
	                }
	            }
	        } catch (Exception e) {
	        		log.error("Ad is not displayed");
	        }
    }


    public void enter_Login_Credentials(String UserName, String Password) throws IOException, InterruptedException {
        Thread.sleep(5000);
        login_id.sendKeys(UserName);
        password.sendKeys(Password);
        signIn_button.click();
    }

    public void user_logged_in(String UserName) throws InterruptedException {
        Thread.sleep(5000);
        String Verify_User_Name = verify_username.getText();
        Assert.assertEquals(UserName, Verify_User_Name);

    }

    public void read_main_artcle() throws InterruptedException {
        Thread.sleep(5000);
        Read_Story_Headline = read_headline.getText();
        log.info("Story Headline is: " + Read_Story_Headline);
        Thread.sleep(5000);
        story_headline.click();

    }

    public void verify_main_article_page() throws InterruptedException {

        Thread.sleep(10000);
        String Main_article_Page_text = main_article_heading.getText();
        log.info("Main Article Heading is: " + Main_article_Page_text);
        Assert.assertEquals(Main_article_Page_text, Read_Story_Headline);
    }

    public void logout() throws InterruptedException {
        Thread.sleep(5000);
        log_out.click();
    }
    
    public void gotoPrintEdition() throws InterruptedException {	
        Thread.sleep(5000);
        printEdition.click();
        
        String currentUrl = getCurrentPageURL();
		loadAdFreeURL(currentUrl);	
    }
    
    public String getCurrentPageURL() throws InterruptedException {	
        Thread.sleep(5000);
        ExpectedCondition e = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
              return (d.getCurrentUrl() != stWebURL);
            }
          };
         wait.until(e);
	     return(driver.getCurrentUrl());
    }
    
    public void loadAdFreeURL(String url) throws InterruptedException {	
        String adFreeURL = url + Constant.AD_SKIP_STRING;
        this.driver.get(adFreeURL);
    }
}