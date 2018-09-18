package com.sph.utilities;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.Status;
import com.sph.driverFactory.DriverManager;
import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DeviceActions{
	private WebDriver driver;
    private WebDriverWait wait;
	
	private String methodName = null;
	private Capabilities capabilities;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(DeviceActions.class);
    
    @iOSXCUITFindBy(accessibility = IOSElements.INTERSTITIAL_AD_ID)
	@AndroidFindBy(xpath = AndroidElements.INTERSTITIAL_AD_XPATH)
	private MobileElement interstitialAd;
	
	@iOSXCUITFindBy(accessibility = IOSElements.CLOSE_INTERSTITIAL_AD_ID)
	@AndroidFindBy(accessibility = AndroidElements.CLOSE_INTERSTITIAL_AD_ID)
	private MobileElement closeInterstitialAdBtn;
    
	public DeviceActions(WebDriver driver) {
		this.driver = driver;
		this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void backClick() {
		methodName = "backClick";
		logger.info("Entering Method: " + methodName);
		driver.navigate().back();
		logger.info("Exiting Method: " + methodName);
	}
	
	public void swipeHorizontal(String direction) {
		methodName = "swipeHorizontal";
		logger.info("Entering Method: " + methodName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PointOption start = new PointOption();
		PointOption end = new PointOption();
		WaitOptions waitTime = new WaitOptions();
		
		try {
            Dimension size = this.driver.manage().window().getSize();
            int anchor = (int) (size.height /2);
            int startPoint = (int) (size.width * 0.90);
            int endPoint = (int) (size.width * 0.05);
            TouchAction action = new TouchAction ((PerformsTouchActions) driver);
            
            if (direction.equalsIgnoreCase("Left")) {
				action.press(start.point(startPoint, anchor)).waitAction(waitTime.withDuration(Duration.ofMillis(0))).moveTo(end.point(endPoint-startPoint, 0))
				.release().perform();
				logger.info("Perform Swipe to Left Direction");
			} else if (direction.equalsIgnoreCase("Right")) {
				action.press(start.point(endPoint, anchor)).waitAction(waitTime.withDuration(Duration.ofMillis(0))).moveTo(end.point(startPoint - endPoint, 0))
						.release().perform();
				logger.info("Perform Swipe to Right Direction");
			}
        }catch (Exception ex) {
    			logger.info("Error occured during " + direction + " swipe" + ex.getMessage());	
        }
	}
	
	public void swipeVerticle(String direction) {
		methodName = "swipeVerticle";
		logger.info("Entering Method: " + methodName);
		PointOption start = new PointOption();
		PointOption end = new PointOption();
		WaitOptions waitTime = new WaitOptions();
		
		try {
            Dimension size = this.driver.manage().window().getSize();
            int anchor = (int) (size.width * 0.5);
			int startPoint = (int) (size.height * 0.9);
			int endPoint = (int) (size.height * 0.2);
            TouchAction action = new TouchAction ((PerformsTouchActions) driver);
            
            if (direction.equalsIgnoreCase("Up")) {
				action.press(start.point(anchor,startPoint)).waitAction(waitTime.withDuration(Duration.ofMillis(0))).moveTo(end.point(0, endPoint-startPoint))
				.release().perform();
				logger.info("Perform Upwards Scroll operation");
			} else if (direction.equalsIgnoreCase("Down")) {
				action.press(start.point(anchor,endPoint)).waitAction(waitTime.withDuration(Duration.ofMillis(0))).moveTo(end.point(0, startPoint - endPoint))
				.release().perform();
				logger.info("Perform Downwards Scroll operation");
			}
        }catch (Exception ex) {
        		logger.info("Error occured during " + direction + " swipe" + ex.getMessage());	
		}
		
		logger.info("Exiting Method: " + methodName);
	}
	
	public void goToFirstPage() {
		methodName = "goToFirstPage";
		logger.info("Entering Method: " + methodName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(capabilities.getPlatform().toString().equalsIgnoreCase("iOS")) {
			try {
				MobileElement topPage = (MobileElement) driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"App For iOS - Singapore Press Holdings\"]/XCUIElementTypeLink/XCUIElementTypeLink"));
				topPage.click();
				logger.info("Clicked Upward Arrow and reached the first page of License");
			}
			catch (Exception ex) {
				logger.info("Error occured while navigating to top page" + ex.getMessage());	
			}
		}
		logger.info("Exiting Method: " + methodName);
	}
	
	public Boolean clickifClickable(MobileElement element, int timeOut) {
		methodName = "clickifClickable";
		logger.info("Entering Method: " + methodName);
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			return true;
		} catch (Exception ex) {
			logger.info("Element is not clickable" + ex.getMessage());
			return false;
		}
	}
	
	public void verifyMobileElements(String screenName, MobileElement... mobileElements) {
		methodName = "verifyMobileElements: " + screenName;
		logger.info("Entering Method: " + methodName);
		for (MobileElement list : mobileElements) {
			Assert.assertTrue(isElementPresent(list, Constant.SHORT_TIMEOUT, screenName));
		}
		logger.info("Exiting Method: " + methodName);
	}
	
	public boolean isElementPresent(MobileElement element, int timeOut, String elementName) {
		methodName = "isElementPresent using ElementName: " + element;
		logger.info("Entering Method: " + methodName);
		if (isElementPresent(element, timeOut)) {
			return true;
		}
		Assert.fail(elementName + " is not displayed on the screen");
		logger.info("Exiting Method: " + methodName);
		return false;
	}
	
	public boolean isElementPresent(MobileElement element, int timeOut) {
		methodName = "isElementPresent: " + element;
		logger.info("Entering Method: " + methodName);
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
		} catch (TimeoutException ex) {
			logger.info("Element is not displayed" + ex.getMessage());
			return false;
		}
	}
	
	/**percentage is the amount by which user wants to move seekbar like for 
	e.g by 50%= 0.5, llly for 80%, percentage=0.8
	*/
	public void moveSeekbar(MobileElement seekbar,double percentage) {
		methodName = "moveSeekbar";
		logger.info("Entering Method: " + methodName);
	    
		int start=seekbar.getLocation().getX();  
        int end=seekbar.getSize().getWidth()+start;
        int y=seekbar.getLocation().getY();      
        TouchAction action=new TouchAction((PerformsTouchActions) driver);
        int targetX= (int) ((end*percentage));
        LongPressOptions longPress = new LongPressOptions();
        action.longPress(longPress.withPosition(new PointOption().withCoordinates(start,y))).moveTo(new PointOption().withCoordinates(targetX,y)).release().perform();
		
        logger.info("Exiting Method: " + methodName);
	}
	
	public void isElementDisplayed(MobileElement element) {
		methodName = "moveSeekbar";
		logger.info("Entering Method: " + methodName);
		
		try {
			Assert.assertTrue(element.isDisplayed());
			logger.info(element + " is displayed/visible on the screen");
		} catch (AssertionError er) {
			logger.info(element + " is not displayed.");
			Assert.fail("Assertion Failed : " + er.getMessage());
		}
		
		logger.info("Exiting Method: " + methodName);
	}
	
//	public void isElementClickable(MobileElement element) {
//		try {
//			Assert.assertTrue(element.isDisplayed());
//			Log.INFO(element + " is displayed/visible on the screen");
//		} catch (AssertionError er) {
//			Log.ERROR(element + " is not displayed.");
//			Assert.fail("Assertion Failed : " + er.getMessage());
//
//		}
//	}
	
	public void isElementEnabled(MobileElement element) {
		methodName = "moveSeekbar";
		logger.info("Entering Method: " + methodName);
		try {
			Assert.assertTrue(element.isEnabled());
			logger.info(element + " is displayed/visible on the screen");
		} catch (AssertionError er) {
			logger.info(element + " is not displayed.");
			Assert.fail("Assertion Failed : " + er.getMessage());
		}
		
		logger.info("Exiting Method: " + methodName);
	}

	public void assertEquals(String text1, String text2) {
		methodName = "moveSeekbar";
		logger.info("Entering Method: " + methodName);
		try {
			Assert.assertEquals(text1, text2);
			logger.info("Both strings are equal : " + text1 + " Matches " + text2);
		} catch (AssertionError er) {
			logger.info("Texts doesn't match both differ in value");
			Assert.fail("Assertion Failed : " + er.getMessage());
		}
		logger.info("Exiting Method: " + methodName);
	}
	
	public void setWebViewContext() {
		Set<String> contextHandles = ((MobileDriver) driver).getContextHandles();
		for(String context: contextHandles) {
			if(context.contains("WEBVIEW")) {
				((MobileDriver) driver).context(context);
			}
		}
		DriverManager.setWebDriver(driver);
	}
	
	public void setNativeAppContext() {
		Set<String> contextHandles = ((MobileDriver) driver).getContextHandles();
		for(String context: contextHandles) {
			if(context.contains("NATIVE_APP")) {
				((MobileDriver) driver).context(context);
			}
		}
		DriverManager.setWebDriver(driver);
	}
	
	public Boolean isInterstitialAdBlockingView() {
		methodName = "closeInterstitialAd";
		logger.info("Entering Method: " + methodName);
		boolean interstitialAdInView = false;
		
		if(interstitialAd!= null) {
			interstitialAdInView = true;
		}
		
		logger.info("Exiting Method: " + methodName);
		return interstitialAdInView;
	}
	
	public boolean closeInterstitialAd() {
		methodName = "closeInterstitialAd";
		logger.info("Entering Method: " + methodName);
		boolean closedAdFound = false;
		if(isInterstitialAdBlockingView()) {
			try {
				clickifClickable(closeInterstitialAdBtn, Constant.LONG_TIMEOUT);
				closedAdFound = true;
			}
			catch(Exception e) {
				logger.info("No Interstitial Ad displayed");
			}
		}
		
		logger.info("Exiting Method: " + methodName);
		return closedAdFound;
	}
	
}
