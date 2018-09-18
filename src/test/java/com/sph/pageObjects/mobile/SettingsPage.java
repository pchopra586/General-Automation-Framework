package com.sph.pageObjects.mobile;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import com.sph.utilities.AndroidElements;
import com.sph.utilities.Constant;
import com.sph.utilities.Constant.SETTINGS_MENU;
import com.sph.utilities.DeviceActions;
import com.sph.utilities.IOSElements;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;


public class SettingsPage{
	private String methodName = null;

	String browserName = LocalWebDriverListener.browserName;
    Logger logger = Logger.getLogger(LoginPage.class);
	private WebDriver driver;
    private WebDriverWait wait;
    private Capabilities capabilities;
    private DeviceActions util;
	
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"" + IOSElements.NAVIGATION_TITLE_ID + "\"]/following-sibling::XCUIElementTypeButton")
	@AndroidFindBy(id = "btn_back")
	private MobileElement closeSettingsButton;
	
	@iOSXCUITFindBy(accessibility = "close menu")
	private MobileElement close;
	
	@iOSXCUITFindBy(accessibility = IOSElements.EDIT_HOME_SETTINGS_ID)
	private MobileElement editHomeSettings;
	
	@iOSXCUITFindBy(id = IOSElements.ACCOUNT_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.ACCOUNT_SETTINGS_ID)
	private MobileElement accountSettings;
	
	@iOSXCUITFindBy(id = IOSElements.NOTIFICATIONS_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.NOTIFICATIONS_SETTINGS_ID)
	private MobileElement notificationsSettings;
	
	@iOSXCUITFindBy(id = IOSElements.EDIT_NOTIFICATIONS_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.EDIT_NOTIFICATIONS_SETTINGS_ID)
	private MobileElement editNotificationsSettings;
	
	@AndroidFindBy(id = AndroidElements.SUBSCRIPTION_SETTINGS_ID)
	private MobileElement subscriptionSettings;
	
	@iOSXCUITFindBy(id = IOSElements.AUTO_DOWNLOAD_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.AUTO_DOWNLOAD_SETTINGS_ID)
	private MobileElement autoDownloadSettings;
	
	@iOSXCUITFindBy(id = IOSElements.DOWNLOADED_ISSUES_SETTINGS_ID)
	private MobileElement downloadedIssuesSettings;
	
	@iOSXCUITFindBy(id = IOSElements.TEXT_SIZE_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.TEXT_SIZE_SETTINGS_ID)
	private MobileElement textSizeSettings;
	
	@iOSXCUITFindBy(id = IOSElements.TEXT_SIZE_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.TEXT_SIZE_SEEKBAR_ID)
	private MobileElement seekbarTextsize;
	
	@iOSXCUITFindBy(id = IOSElements.SPEECH_RATE_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.SPEECH_RATE_SETTINGS_ID)
	private MobileElement speechRateSettings;
	
	@iOSXCUITFindBy(id = IOSElements.SPEECH_RATE_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.SPEECH_RATE_SEEKBAR_ID)
	private MobileElement seekbarSpeechRate;
	
	@iOSXCUITFindBy(id = IOSElements.SUPPORT_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.SUPPORT_SETTINGS_ID)
	private MobileElement supportSettings;
	
	@iOSXCUITFindBy(id = IOSElements.FAQ_AND_HELP_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.FAQ_AND_HELP_SETTINGS_ID)
	private MobileElement faqHelpSettings;
	
	@iOSXCUITFindBy(id = IOSElements.AD_ENQUIRY_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.AD_ENQUIRY_SETTINGS_ID)
	private MobileElement adEnquirySettings;
	
	@iOSXCUITFindBy(id = IOSElements.PRIVACY_POLICY_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.PRIVACY_POLICY_SETTINGS_ID)
	private MobileElement privacyPolicySettings;
	
	@iOSXCUITFindBy(id = IOSElements.TERMS_AND_CONDITIONS_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.TERMS_AND_CONDITIONS_SETTINGS_ID)
	private MobileElement tncSettings;
	
	@iOSXCUITFindBy(id = IOSElements.VERSION_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.VERSION_SETTINGS_ID)
	private MobileElement versionSettings;
	
	@iOSXCUITFindBy(id = IOSElements.VERSION_SETTINGS_ID)
	@AndroidFindBy(id = AndroidElements.VERSION_NUMBER)
	private MobileElement version;
	
	public SettingsPage(WebDriver driver) throws MalformedURLException  {
		this.driver = driver;
//		this.wait = new WebDriverWait(this.driver, 10);
		util = new DeviceActions(this.driver);
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public SettingsPage gotoSettingsMenu() {
		methodName = "gotoSettings";
		logger.info("Entering Method: " + methodName);
		
		try {
			MenuPage menu = new MenuPage(driver);
			menu.clickOnMenu().gotoMenu(Constant.MENU.SETTINGS);
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
		logger.info("Exiting Method: " + methodName);
		return this;
	}
	
	public MobileDriver gotoSettings(SETTINGS_MENU settingsOption) {
		methodName = "gotoSettings - " + settingsOption + "";
		logger.info("Entering Method: " + methodName);
		
		try {
			switch(settingsOption) {
			case ACCOUNT: 
				accountSettings.click();
				break;
			case EDIT_HOME: 
				//util.clickifClickable(editHomeSettings, Constant.SHORT_TIMEOUT);
				editHomeSettings.click();
				break;
			case NOTIFICATIONS: 
				util.clickifClickable(notificationsSettings, Constant.SHORT_TIMEOUT);
				break;
			case EDIT_NOTIFICATIONS: 
				util.clickifClickable(editNotificationsSettings, Constant.SHORT_TIMEOUT);
				break;
			case AUTO_DOWNLOAD: 
				util.clickifClickable(autoDownloadSettings, Constant.SHORT_TIMEOUT);
				break;
			case DOWNLOADED_ISSUES: 
				util.clickifClickable(downloadedIssuesSettings, Constant.SHORT_TIMEOUT);
				break;
			case TEXT_SIZE: 
				util.clickifClickable(textSizeSettings, Constant.SHORT_TIMEOUT);
				break;
			case SPEAKING_RATE: 
				util.clickifClickable(speechRateSettings, Constant.SHORT_TIMEOUT);
				break;
			case SUPPORT: 
				util.clickifClickable(supportSettings, Constant.SHORT_TIMEOUT);
				break;
			case FAQ_AND_HELP: 
				util.clickifClickable(faqHelpSettings, Constant.SHORT_TIMEOUT);
				break;
			case ADVERTISING_ENQUIRY: 
				util.clickifClickable(adEnquirySettings, Constant.SHORT_TIMEOUT);
				break;
			case TERMS_AND_CONDITIONS: 
				util.clickifClickable(tncSettings, Constant.SHORT_TIMEOUT);
				break;
			case PRIVACY_POLICY: 
				util.clickifClickable(privacyPolicySettings, Constant.SHORT_TIMEOUT);
				break;
			default:
				logger.error("Please provide valid Settings details");
				break;
			}
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return null;
		}
		
		logger.info("Exiting Method: " + methodName);
		return (MobileDriver)driver;
	}
	
	public boolean closeSettingsView() {
		methodName = "closeSettingsView";
		logger.info("Entering Method: " + methodName);
		boolean closedSettingsView = false;
		try {
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			closeSettingsButton.click();
		}catch(Exception e) {
			logger.error("Exception raised: " + e);
			driver.quit();
			return closedSettingsView;
		}
		//util.clickifClickable(closeSettingsButton, Constant.LONG_TIMEOUT);
		
		closedSettingsView = true;
		logger.info("Exiting Method: " + methodName);
		return closedSettingsView;
	}
	
//	public AccountPage openAccountPage() {
//		Log.INFO("Clicking on account link to open account page");
//		logger.log(Status.INFO,"Clicking on account link to open account page");
//		util.clickifClickable(account, Constant.SHORT_TIMEOUT);
//		return new AccountPage(driver, util, logger);
//	}

	public void verifyNotificationsSwitchAndLink() {
		logger.info("verfying if 'notifications toggle switch' and 'edit notification' link is visible");
		util.verifyMobileElements("Settings : Notifications", notificationsSettings, editNotificationsSettings);
		logger.info("'notifications toggle switch' and 'edit notification' link is visible");
	}

	public NotificationsPage openNotificationPage() {
		logger.info("Clicking on 'edit notifications' link to open notification page");
		util.clickifClickable(editNotificationsSettings, Constant.SHORT_TIMEOUT);
		return new NotificationsPage(driver);
	}

	public void verifyStateOfToggleSwitch() {

		if (notificationsSettings.getText().equalsIgnoreCase(Constant.NOTIFICATION_SWITCH_OFF)) {
			logger.info("Verifying 'Notifications' toggle switch");
			logger.info("Toggle switch is set to OFF");
		} else if (notificationsSettings.getText().equalsIgnoreCase(Constant.NOTIFICATION_SWITCH_ON)) {
			logger.info("Verifying 'Notifications' toggle switch");
			logger.info("Toggle switch is set to ON");
		}
	}

	public void slideTextSizeSeekbar() {

		util.moveSeekbar(seekbarTextsize, 0.5);

	}

//	public void verifyTextSizeSectionElements() {
//
//		util.verifyMobileElements("Text Size Section", seekbarTextsize, textSize, capsASmallSize, capsA);
//		Log.INFO(
//				"'Text size seek bar',Text:'Text Size', 'Capital letter A with small size','Capital letter A' are present on text size section");
//		logger.info(
//				"'Text size seek bar',Text:'Text Size', 'Capital letter A with small size','Capital letter A' are present on text size section");
//
//	}
}
