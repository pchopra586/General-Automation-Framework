package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.sph.driverFactory.LocalWebDriverListener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import com.sph.utilities.AndroidElements;
import com.sph.utilities.Constant;
import com.sph.utilities.GenericNavigator;
import com.sph.utilities.IOSElements;

/* Class contains page objects for Account screen.*/
public class AccountPage {
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
	Logger logger = Logger.getLogger(AccountPage.class);
	private WebDriver driver;
	private WebDriverWait wait;
	private Capabilities capabilities;

	public AccountPage(WebDriver driver) throws MalformedURLException {
		this.driver = driver;
		this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_SUBSCRIPTION_ALERT_MSG)
	private MobileElement subscriptionMsg;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[starts-with(@name, 'Logged in as')]")
	private MobileElement loginSuccessMsg;

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_MANAGE_HEADER_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_MANAGE_HEADER_ID)
	private MobileElement manage;

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_LOGOUT_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_LOGIN_ID)
	private MobileElement logOut;

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_LOGOUT_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_LOGIN_LABEL_ID)
	private MobileElement logOutLink;

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_LOGIN_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_LOGIN_ID)
	private MobileElement logIn;

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_LOGIN_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_LOGIN_LABEL_ID)
	private MobileElement logInLink;

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_SUBSCRIPTION_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_SUBSCRIPTION_ID)
	private MobileElement subscribe;

	@iOSXCUITFindBy(accessibility = IOSElements.ACCOUNT_LOGOUT_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_LOGIN_ID)
	private MobileElement loggedInDevices;

	@iOSXCUITFindBy(accessibility = IOSElements.SETTINGS_MENU_BACK_BTN)
	@AndroidFindBy(id = AndroidElements.BACK_BTN)
	private MobileElement backButton;

	@iOSXCUITFindBy(id = IOSElements.ACCOUNT_PAGE_TITLE_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_PAGE_TITLE_ID)
	private MobileElement pageTitle;

	@AndroidFindBy(id = AndroidElements.ACCOUNT_PAGE_TITLE_LAYOUT)
	private MobileElement pageTitleLayout;

	@iOSXCUITFindBy(id = IOSElements.ACCOUNT_PAGE_TITLE_ID)
	@AndroidFindBy(id = "//android.widget.LinearLayout[@resource-id=\"" + Constant.APP_PACKAGE + ":id/"
			+ AndroidElements.ACCOUNT_LOGIN_ID + "\"]//android.widget.ImageView")
	private MobileElement LoginArrowForTableView;

	@iOSXCUITFindBy(id = IOSElements.ACCOUNT_PAGE_TITLE_ID)
	@AndroidFindBy(id = "//android.widget.LinearLayout[@resource-id=\"" + Constant.APP_PACKAGE + ":id/"
			+ AndroidElements.ACCOUNT_SUBSCRIPTION_ID + "\"]//android.widget.ImageView")
	private MobileElement subscribeArrowForTableView;

	public AccountPage gotoAccountPage() {
		methodName = "gotoAccountPage";
		logger.info("Entering Method: " + methodName);
		GenericNavigator navigator = new GenericNavigator(driver);

		try {
			if (!navigator.preConfigured()) {
				navigator.completeBasicInstallConfig();
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			MenuPage menu = new MenuPage(driver);
			menu.clickOnMenu().gotoMenu(Constant.MENU.SETTINGS);

			SettingsPage settings = new SettingsPage(driver);

			settings.gotoSettings(Constant.SETTINGS_MENU.ACCOUNT);
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}

		// Log.INFO("Exiting Method: " + methodName);
		logger.info("Exiting Method: " + methodName);
		return this;
	}

	public SettingsPage goBackToPreviousView() {
		methodName = "goBackToPreviousView";
		logger.info("Entering Method: " + methodName);

		try {
			backButton.click();
			logger.info("Exiting Method: " + methodName);
			return new SettingsPage(driver);
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
	}

	public AccountPage verifyPageTitleContent() {
		methodName = "verifyPageTitleContent";
		logger.info("Entering Method: " + methodName);

		try {
			if (pageTitle != null) {
				Assert.assertTrue(pageTitle.getAttribute("enabled").equals("true"),
						"Account Page Title is unexpectedly disabled");
				Assert.assertTrue(subscribe.getAttribute("enabled").equals("true"),
						"Option to subscribe is unexpectedly disabled");
				Assert.assertTrue(manage.getAttribute("enabled").equals("true"),
						"Manage Header is unexpectedly disabled");

				if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
					// Page title
					Assert.assertTrue(pageTitleLayout.getAttribute("clickable").equals("true"),
							"Unexpectedly Account Page Title is not clickable");
					Assert.assertTrue(pageTitleLayout.getAttribute("enabled").equals("true"),
							"Unexpectedly Account Page Title is disabled");
					Assert.assertEquals(pageTitle.getAttribute("text"), Constant.ACCOUNT_PAGE_TITLE,
							"Inconsistent name of Account Page title");

					// Page Back Button
					Assert.assertTrue(backButton.getAttribute("enabled").equals("true"),
							"Button to navigate to previous view is unexpectedly disabled");

					// Subscription Option
					Assert.assertTrue(subscribe.getAttribute("clickable").equals("true"),
							"Option to subscribe is unexpectedly not clickable");

					// Manage Header
					Assert.assertEquals(manage.getAttribute("text"), Constant.ACCOUNT_MANAGE_LABEL,
							"Inconsistent label of Manage Header");
				} else if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
					// Page title
					Assert.assertEquals(pageTitle.getAttribute("name"), Constant.ACCOUNT_PAGE_TITLE,
							"Inconsistent name of Account Page title");
					Assert.assertTrue(pageTitle.getAttribute("visible").equals("true"),
							"Account Page Title is unexpectedly invisible");

					// Page Back Button
					Assert.assertEquals(backButton.getAttribute("type"), "XCUIElementTypeButton",
							"Inconsistent type of Button to navigate to previous view");
					Assert.assertTrue(backButton.getAttribute("enabled").equals("true"),
							"Button to navigate to previous view is unexpectedly disabled");
					Assert.assertTrue(backButton.getAttribute("visible").equals("true"),
							"Button to navigate to previous view is unexpectedly invisible");

					// Subscription Option
					Assert.assertTrue(subscribe.getAttribute("enabled").equals("true"),
							"Option to subscribe is unexpectedly disabled");
					Assert.assertTrue(subscribe.getAttribute("visible").equals("true"),
							"Option to subscribe is unexpectedly invisible");
					Assert.assertEquals(subscribe.getAttribute("label"), IOSElements.ACCOUNT_SUBSCRIPTION_ID,
							"Inconsistent label for Option to subscribe");
					Assert.assertEquals(subscribe.getAttribute("name"), IOSElements.ACCOUNT_SUBSCRIPTION_ID,
							"Inconsistent name for Option to subscribe");

					// Manage Header
					Assert.assertEquals(manage.getAttribute("label"), Constant.ACCOUNT_MANAGE_LABEL,
							"Inconsistent label of Manage Header");
				}
			} else {
				logger.warn("Elements not found");
			}
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}

		logger.info("Exiting Method: " + methodName);
		return this;
	}

	public AccountPage verifyLogInLinkDisplayed() {
		methodName = "verifyLogInLinkDisplayed";
		logger.info("Entering Method: " + methodName);

		try {
			Assert.assertTrue(logInLink.getAttribute("enabled").equals("true"),
					"Login button is unexpectedly disabled");

			if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
				Assert.assertTrue(logIn.getAttribute("clickable").equals("true"),
						"Unexpectedly Account Page Title is not clickable");
				Assert.assertTrue(logIn.getAttribute("enabled").equals("true"),
						"Unexpectedly Account Page Title is disabled");
				Assert.assertEquals(logInLink.getAttribute("text"), Constant.LOG_IN,
						"Inconsistent name of Account Page title");
			} else if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				Assert.assertEquals(logIn.getAttribute("name"), Constant.LOG_IN, "Inconsistent name of Log In Button");
				Assert.assertEquals(logIn.getAttribute("label"), Constant.LOG_IN,
						"Inconsistent label of Log In Button");
				Assert.assertTrue(logInLink.getAttribute("visible").equals("true"),
						"Login button is unexpectedly invisible");

				Assert.assertTrue(subscriptionMsg.getAttribute("visible").equals("true"),
						"Subscription Alert Message is unexpectedly invisible");
				Assert.assertTrue(subscriptionMsg.getAttribute("enabled").equals("true"),
						"Subscription Alert Message is unexpectedly disabled");
				Assert.assertEquals(subscriptionMsg.getAttribute("label"), IOSElements.ACCOUNT_SUBSCRIPTION_ALERT_MSG,
						"Subscription Alert Message is unexpectedly invisible");
				Assert.assertEquals(subscriptionMsg.getAttribute("name"), IOSElements.ACCOUNT_SUBSCRIPTION_ALERT_MSG,
						"Subscription Alert Message is unexpectedly invisible");
			}
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}

		logger.info("Exiting Method: " + methodName);
		return this;
	}

	public AccountPage verifyLogOutLinkIsDisplayed() {
		methodName = "verifyLogOutLinkIsDisplayed";
		// Log.INFO("Entering Method: " + methodName);
		logger.info("Entering Method: " + methodName);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertTrue(logOutLink.isEnabled(), "Login button is unexpectedly disabled");

		try {
			if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("Android")) {
				Assert.assertTrue(logOut.getAttribute("clickable").equals("true"),
						"Unexpectedly Logout Option is not clickable");
				Assert.assertTrue(logOut.isEnabled(), "Unexpectedly  Logout Option is disabled");
				Assert.assertEquals(logOutLink.getAttribute("text"), Constant.LOG_OUT,
						"Inconsistent label for Logout Option");
			} else if (capabilities.getCapability("platformName").toString().equalsIgnoreCase("iOS")) {
				Assert.assertEquals(logOut.getAttribute("name"), Constant.LOG_OUT,
						"Inconsistent name of Logout Button");
				Assert.assertEquals(logOut.getAttribute("label"), Constant.LOG_OUT,
						"Inconsistent label of Logout Button");
				Assert.assertTrue(logOutLink.isDisplayed(), "logOut button is unexpectedly invisible");

				Assert.assertTrue(loginSuccessMsg.isDisplayed(), "Successful Login message is unexpectedly invisible");
				Assert.assertTrue(loginSuccessMsg.isEnabled(), "Successful Login Message is unexpectedly disabled");
			}
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}

		logger.info("Exiting Method: " + methodName);
		return this;

	}

	public AccountPage verifyElementAlignment(boolean loggedIn) {
		methodName = "verifyElementAlignment";
		logger.info("Entering Method: " + methodName);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		try {
			// compare Manage, Subscribe X-axis
			Assert.assertEquals(manage.getLocation().getX(), subscribe.getLocation().getX(),
					"Manage Header label and Subscribe Option are unexpectedly misaligned across X-axis");

			if (loggedIn) {
				//
			} else {
				// compare X-axis of subscribe and login/logout option
				Assert.assertEquals(logIn.getLocation().getX(), subscribe.getLocation().getX(),
						"Login Option and Subscribe Option are unexpectedly vary in height");

				// compare height of subscribe and login/logout option
				Assert.assertEquals(logIn.getSize().getHeight(), subscribe.getSize().getHeight(),
						"Login Option and Subscribe Option are unexpectedly vary in height");

				// compare width of subscribe and login/logout option
				Assert.assertEquals(logIn.getSize().getWidth(), subscribe.getSize().getWidth(),
						"Login Option and Subscribe Option are unexpectedly vary in width");
			}
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}

		logger.info("Exiting Method: " + methodName);
		return this;
	}

	public LoginPage initiateLogin() {
		methodName = "initiateLogin";
		logger.info("Entering Method: " + methodName);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		try {
			logIn.click();
			logger.info("Exiting Method: " + methodName);
			return new LoginPage(driver);
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
	}

	public AccountPage logout() {
		methodName = "logout";
		logger.info("Entering Method: " + methodName);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		try {
			logOut.click();
		} catch (Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
		}

		logger.info("Exiting Method: " + methodName);
		return this;
	}

}
