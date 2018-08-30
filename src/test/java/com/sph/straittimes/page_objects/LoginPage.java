package com.sph.straittimes.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sph.straittimes.Common.PropLocation;
import com.sph.straittimes.Common.ReadPropertiesValues;

import java.io.IOException;


/**
 * Created by jay on 10/04/14.
 */
public class LoginPage {

    PropLocation file = new PropLocation();
    ReadPropertiesValues loc = new ReadPropertiesValues(file.location());
    ReadPropertiesValues elementLoc = new ReadPropertiesValues(file.elementLocation());

    private WebDriver driver;
    private WebDriverWait wait;
    private String Read_Story_Headline;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
    }

    public void launch_Strait_Times() throws InterruptedException {
        this.driver.get("https://www.straitstimes.com");
        this.driver.manage().window().maximize();
    }

    public void login_StraitTimes() throws InterruptedException {
        Thread.sleep(20000);
        //driver.switchTo().frame(0);
        if (driver.findElement(By.xpath("//div[contains(@id,'ebAd')]/iframe")).isDisplayed()) {
            driver.switchTo().frame((WebElement) driver.findElement(By.xpath("//div[contains(@id,'ebAd')]/iframe")));

            if (driver.findElement(By.xpath("//div[@id='ad-container']/button[@id='close-button']")).isDisplayed()) {
                WebElement Advertisement = driver.findElement(By.xpath("//div[@id='ad-container']/button[@id='close-button']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Advertisement);
                //Advertisement.click();
            }
            driver.switchTo().defaultContent();
            WebElement Login_Link = driver.findElement(By.xpath(elementLoc.readProperty("login_link")));
            Actions actions = new Actions(driver);
            actions.moveToElement(Login_Link).click().build().perform();

        }
    }

        public void enter_Login_Credentials(String UserName, String Password) throws IOException, InterruptedException  {
            Thread.sleep(3000);
            driver.findElement(By.id(elementLoc.readProperty("login_ID"))).sendKeys(UserName);
            driver.findElement(By.id(elementLoc.readProperty("password"))).sendKeys(Password);
            driver.findElement(By.className(elementLoc.readProperty("signIn_button"))).click();
        }

        public void user_logged_in(String UserName) throws InterruptedException {
            Thread.sleep(10000);
            String Verify_User_Name = driver.findElement(By.xpath(elementLoc.readProperty("verify_user"))).getText();
            Assert.assertEquals(UserName, Verify_User_Name);

        }

        public void verify_main_article_image () throws InterruptedException {
            WebElement Story_Headline = driver.findElement(By.xpath(elementLoc.readProperty("story_headline")));
            Read_Story_Headline = Story_Headline.getText();
            System.out.println("Story Headline is: " + Read_Story_Headline);
            WebElement Story_Headline_Image = driver.findElement(By.xpath(elementLoc.readProperty("story_headline_image")));
            Boolean ImagePresent_MainPage_Verify = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", Story_Headline_Image);
            if (!ImagePresent_MainPage_Verify) {
                System.out.println("Image is not present");
            } else {
                System.out.println("Image is Present");
            }
            System.out.println(ImagePresent_MainPage_Verify);

        }
        public void click_main_article () throws InterruptedException {
            driver.findElement(By.xpath(elementLoc.readProperty("main_page_article_image"))).click();

        }
        public void verify_main_article_page () throws InterruptedException {

            Thread.sleep(30000);
            String Main_article_Page_text = driver.findElement(By.xpath(elementLoc.readProperty("main_article_header"))).getText();
            System.out.println("Main Article Heading is: " + Main_article_Page_text);
            Assert.assertEquals(Main_article_Page_text, Read_Story_Headline);
            WebElement Image_Article_Page = driver.findElement(By.xpath(elementLoc.readProperty("article_page_image")));
            Boolean ImagePresent_ArticlePage = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", Image_Article_Page);
            if (!ImagePresent_ArticlePage) {
                System.out.println("Image is not present");
            } else {
                System.out.println("Image is Present");
            }


        }
    }

