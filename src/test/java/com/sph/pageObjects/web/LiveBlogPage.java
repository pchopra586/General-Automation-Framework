package com.sph.pageObjects.web;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.WebElements;

public class LiveBlogPage {
	private WebDriver driver;
    private WebDriverWait wait;
    String browserName = LocalWebDriverListener.browserName;

    @FindBy(xpath = "//div[@class='st_liveblog fullv']/div/div/div/div/p/a")
    private List<WebElement> liveBlogArticles;
    
    @FindBy(xpath = "//div[@class='liveblog-link']/a")
    private WebElement liveBlogLink;
    
    @FindBy(xpath = "//a[@class='load-more']")
    private WebElement loadMore;
    
    public LiveBlogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }
    
    public int verifyArticleURL() {
 
		int liveBlogArticleCount = 0;
		Boolean moreLiveBlogArticles = true;
		String liveBlogURL = liveBlogLink.getAttribute("href") + "?adbypass=topspecial_skinning_topoverlay";
		this.driver.get(liveBlogURL);
		
		while(moreLiveBlogArticles) {
			try {
				loadMore.click();
				
			}catch(Exception e) {
//				loadMore.click();
				moreLiveBlogArticles = false;
			}
		}
		
		for(WebElement article:liveBlogArticles) {
			String url = article.getAttribute("href");
			if(url != "") {
				liveBlogArticleCount = liveBlogArticleCount+1;
				System.out.println("Article URL: " + url);
			}
		}
		return liveBlogArticleCount;
    }
}
