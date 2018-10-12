package com.sph.pageObjects.web;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.WebElements;

public class PrintEditionPage {
	private WebDriver driver;
    private WebDriverWait wait;
    String browserName = LocalWebDriverListener.browserName;

    @FindBy(className = WebElements.STORY_TITLE)
    private List<WebElement> storyTitle;
    
    public PrintEditionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }
    
    public int getTotalStoryPublished() {
    		int totalArticlePublishedToday = 0;
    		totalArticlePublishedToday = storyTitle.size();
    		return totalArticlePublishedToday;
    }
}
