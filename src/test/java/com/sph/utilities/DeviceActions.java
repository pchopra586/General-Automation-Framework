package com.sph.utilities;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.sph.driverFactory.DriverManager;
import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.AppiumDriver;
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
            
        
//            TouchAction action = new TouchAction ((PerformsTouchActions) driver);
//            action.press(PointOption.point(startX,startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
//                    .moveTo(PointOption.point(endX, startY)).release().perform();
            
            if (direction.equalsIgnoreCase("Left")) {
//				action.press(start.point(startPoint, anchor)).waitAction(waitTime.withDuration(Duration.ofMillis(0))).moveTo(end.point(endPoint-startPoint, 0))
//				.release().perform();
            		action.press(start.point(startPoint, anchor)).waitAction(waitTime.withDuration(Duration.ofMillis(1000))).moveTo(end.point(endPoint, anchor))
				.release().perform();
				logger.info("Perform Swipe to Left Direction");
			} else if (direction.equalsIgnoreCase("Right")) {
				action.press(start.point(endPoint, anchor)).waitAction(waitTime.withDuration(Duration.ofMillis(0))).moveTo(end.point(startPoint, anchor))
						.release().perform();
				logger.info("Perform Swipe to Right Direction");
			}
        }catch (Exception ex) {
    			logger.info("Error occured during " + direction + " swipe" + ex.getMessage());	
        }
	}
	
	public void swipeVertical(String direction) {
		methodName = "swipeVertical";
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
	
	@SuppressWarnings("rawtypes")
	public void clickUsingCoordinates(MobileElement el) {
		try {

			TouchAction touchAction = new TouchAction<>((MobileDriver)driver);
			int xPoint = el.getCenter().getX();
			int yPoint = el.getCenter().getY();
			touchAction.tap(tapOptions().withPosition(point(xPoint, yPoint))).perform();
			logger.info("Performing click on mobile element using it's co-ordinates");

		} catch (Exception ex) {
			logger.info("Error occured during click event using co-ordinates " + ex.getMessage());
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
	
	public void verifyUnwantedTextElements(String bodyText, String... htmlEntities) {
		logger.info("Verifying text on article details for presence of html entities  ");

		for (String list : htmlEntities) {
			logger.info("verifying if \"" + list + "\" is present");
			boolean flag = bodyText.contains(list);
			if (flag) {
				logger.info("Following Unwanted text present \"" + list + "\"");
			} else {
				logger.info("Following text is not present \"" + list + "\"");
			}
		}

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
//			logger.info(element + " is displayed/visible on the screen");
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
	
	public void switchContextToView(WebDriver driver, String view) {

		try {
			Set<String> contextNames = ((AppiumDriver)driver).getContextHandles();
			for (String contextName : contextNames) {

				if (contextName.contains(view)) {
					logger.info(contextName);
					((AppiumDriver)driver).context(contextName);
					logger.info("Switched context to : " + contextName);
					logger.info(driver.getPageSource());

				}

			} // driver.context(contextNames.toArray()[1].toString());//Purposely not removed comment
		} catch (Exception ex) {
			logger.error("Error occured during switching context to " + view);
			Assert.fail("Unable to switch view to -- " + view + ex.getMessage());
		}

	}

	public void verifyBrokenLinks(List<WebElement> articleLinks) {

		int invalidLinksCount = 0;
		ArrayList<String> brokenLinks = new ArrayList<String>();

		logger.info("Total links count : " + articleLinks.size());

		for (int i = 0; i < articleLinks.size(); i++) {

			String url = articleLinks.get(i).getAttribute("href");
			if (url != null) {
				int status = getResponseCode(url);
				if (status == 200) {
					logger.info(url + " is Valid Link  with Response code :" + status);

				} else if (status == 403) {
					invalidLinksCount++;
					brokenLinks.add(url);
					logger.error(
							url + " Forbidden error, user might not have necessary permissions for a resource,Response code is :"
									+ status);
				} else if (status != 200) {
					invalidLinksCount++;
					brokenLinks.add(url);
					logger.error(url + " is Invalid Link  with Response code :" + status);
				}

			}

		}
		logger.info("No. of broken links are :" + invalidLinksCount);
		for (Object link : brokenLinks) {

			logger.error("failed links are " + link.toString());
		}
	}

	public void verifyBrokenImages(List<WebElement> articleImages) {

		int brokenImageCount = 0;
		ArrayList<String> brokenImages = new ArrayList<String>();

		logger.info("Total image link count : " + articleImages.size());

		for (int i = 0; i < articleImages.size(); i++) {

			String url = articleImages.get(i).getAttribute("src");
			if (url != null) {
				int status = getResponseCode(url);
				if (status == 200) {
					logger.info(url + " is valid image with Response code :" + status);

				} else if (status == 403) {
					brokenImageCount++;
					brokenImages.add(url);
					logger.error(
							url + " Forbidden error, user might not have necessary permissions for a resource,Response code is :"
									+ status);
				} else if (status != 200) {
					brokenImageCount++;
					brokenImages.add(url);
					logger.error(url + " image is broken,  Response code is :" + status);
				}

			}

		}
		logger.info("No. of broken image url are :" + brokenImageCount);
		for (Object link : brokenImages) {

			logger.error("failed image links are " + link.toString());
		}

	}

	public int getResponseCode(String url) {
		int responseCode = 0;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request);
			responseCode = response.getStatusLine().getStatusCode();

		} catch (Exception ex) {

			logger.info("Exception in getting response.." + ex.getMessage());
		}
		return responseCode;
	}
	
	public boolean swipeVerticalUntilElementIsFound(MobileElement element, int maxSwipe, String direction) {
		try {
			if (maxSwipe > 0) {
				if (isElementPresent(element, Constant.SHORT_TIMEOUT)) {

					return true;
				}
				swipeVertical(direction);
				maxSwipe--;
				return swipeVerticalUntilElementIsFound(element, maxSwipe, direction);
			}

		} catch (Exception er) {
			logger.error("Element is not present : " + element);
		}
		Assert.fail(element + "element is not found!");
		return false;
	}

	public boolean swipeHorizontalUntilElementIsFound(MobileElement element, int maxSwipe, String direction) {
		try {
			if (maxSwipe > 0) {
				if (isElementPresent(element, Constant.SHORT_TIMEOUT)) {

					return true;
				}
				swipeHorizontal(direction);
				maxSwipe--;
				return swipeHorizontalUntilElementIsFound(element, maxSwipe, direction);
			}

		} catch (Exception er) {
			logger.error("Element is not present : " + element);
		}
		Assert.fail(element + "element is not found!");
		return false;
	}
	
	public boolean swipeSpecificElement(MobileElement element, String direction) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> swipeObject = new HashMap<String, String>();
			if (direction.equals(Constant.DOWN)) {
				swipeObject.put(Constant.DIRECTION, Constant.DOWN);
			} else if (direction.equals(Constant.UP)) {
				swipeObject.put(Constant.DIRECTION, Constant.UP);
			} else if (direction.equals(Constant.LEFT)) {
				swipeObject.put(Constant.DIRECTION, Constant.LEFT);
			} else if (direction.equals(Constant.RIGHT)) {
				swipeObject.put(Constant.DIRECTION, Constant.RIGHT);
			}
			swipeObject.put("element", element.getId());
			js.executeScript("mobile:swipe", swipeObject);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
