package com.sph.driverFactory;

import com.sph.utilities.Constant;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * 
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */

public class LocalDriverFactory {
	public static final String USERNAME = "madhuk";
	public static final String ACCESS_KEY = "d6b57ffa-7e03-4f9a-afca-14e490ccd840";

	public static WebDriver createInstance(String driverHost, String driverPort, String browserName,
			String configFilePath, String signingID, String teamID, String accessKey, String devicename,
			String bundleID, String appPath, String udID, String appPackage, String projectName, String appActivity)
			throws MalformedURLException {
		WebDriver driver;
		DesiredCapabilities capability;
		String localUrl = "http://" + driverHost + ":" + driverPort + "/wd/hub";
		String remoteUrl = "https://" + driverHost + ":" + driverPort + "/wd/hub";
		browserName = (browserName != null) ? browserName : "chromelocal";
	 
		switch (Browser.valueOf(browserName.toUpperCase())) {
		case SAFARILOCAL:
			driver = new SafariDriver();
			break;
		case SAFARIREMOTE:
			capability = DesiredCapabilities.safari();
			capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
			capability.setCapability(CapabilityType.VERSION, "Any");
			capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
			capability.setCapability("accessKey", accessKey);
			capability.setCapability("testName", "Quick Start Safari Browser Demo");
			driver = new RemoteWebDriver(new URL(remoteUrl), capability);
			break;
		case IE:
			driver = new InternetExplorerDriver();
			break;
		case CHROMEREMOTE:
			capability = DesiredCapabilities.chrome();
			capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
			capability.setCapability(CapabilityType.VERSION, "Any");
			capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
			capability.setCapability("accessKey", accessKey);
			System.out.printf("Remote URL is" + remoteUrl);
			driver = new RemoteWebDriver(new URL(remoteUrl), capability);
			System.out.printf("Remote Chrome Driver is returned");
			break;
		case CHROMELOCAL:
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/appDrivers/chromedriver");
			driver = new ChromeDriver();
			System.out.printf("Local Chrome Driver is returned");
			break;
		case CHROMEHEADLESS:
			System.setProperty("webdriver.chrome.driver",
					"/Users/madhu/git/parallel-testing-cucumber/src/test/resources/appDrivers/chromedriver");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			driver = new ChromeDriver(chromeOptions);
			System.out.printf("Local Chrome Driver is returned");
			break;
		case ANDROIDLOCAL:
			capability = DesiredCapabilities.android();
			System.out.println("Browser is " + browserName);
			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
			capability.setCapability(MobileCapabilityType.FULL_RESET, true);
			capability.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
			capability.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability("deviceName", devicename);
			capability.setCapability(MobileCapabilityType.APP, appPath);
			capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE, appPackage);
			capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appActivity);
			System.out.println("URL is" + localUrl);
			driver = new AndroidDriver<>(new URL(localUrl), capability);
			System.out.printf("Local Android driver is returned");
			break;
		case ANDROIDREMOTE:
			capability = DesiredCapabilities.android();
			capability.setCapability("platformVersion", "6.0.1");
			capability.setCapability("user", "madhuk");
            capability.setCapability("testobjectApiKey", "84D7FAAF04F7442EA90FFA98253A9D03");
            capability.setCapability("testobject_platform_name", "Android");
            capability.setCapability("browserName", browserName);
            capability.setCapability("appiumVersion", "1.9.1");
            capability.setCapability("privateDevicesOnly", "true");
            capability.setCapability("deviceName","Samsung_Galaxy_S6_POC137");
            capability.setCapability("testobject_device","Samsung Galaxy S6");
            capability.setCapability("testobject_appium_version","1.9.1");
            capability.setCapability("testobject_app_id","1");
            capability.setCapability("appPackage","com.buuuk.st");
            capability.setCapability("appWaitActivity", "com.sph.straitstimes.views.activities.TncActivity");
            capability.setCapability("appActivity", "com.sph.straitstimes.views.activities.SplashActivity");
            
            URL androidURL = new URL("https://us1.appium.testobject.com/wd/hub");
            driver = new AndroidDriver<>(androidURL, capability);
    
			System.out.printf("Remote Android driver is returned");
			break;
		case ANDROIDLOCALBROWSER:
			capability = DesiredCapabilities.android();
			capability.setCapability("browserName", "");
		    capability.setCapability("deviceOrientation", "portrait");
		    capability.setCapability("appiumVersion", "1.9.0");
		    capability.setCapability("testobject_api_key", "84D7FAAF04F7442EA90FFA98253A9D03");
		    //capability.setCapability("testobject_app_id", "2");
		    capability.setCapability("name", "Madhu's test");
		 // Set allocation from private device pool only
	        capability.setCapability("privateDevicesOnly", "true");
			// set the capability to execute test in chrome browser
			capability.setCapability("browserName", "CHROME");
			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			capability.setCapability("deviceName", devicename);
			System.setProperty("webdriver.chrome.driver", "/appDrivers/chromedriver");
			driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capability);
			System.out.printf("Remote Android Browser is returned");
			break;
		case ANDROIDREMOTEBROWSER:
			capability = DesiredCapabilities.android();
			capability.setCapability("testName", "Quick Start Android Browser Demo");
			//capability.setCapability("accessKey", accessKey);
			// capability.setCapability("project", projectName);
			//capability.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
			//capability.setBrowserName(MobileBrowserType.CHROMIUM);
			driver = new AndroidDriver<>(new URL(remoteUrl), capability);
			break;

		case IOSREMOTEBROWSER:
			capability = DesiredCapabilities.iphone();
			capability.setCapability("testName", "Quick Start iOS Browser Demo");
			capability.setCapability("accessKey", accessKey);
			capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
			capability.setBrowserName(MobileBrowserType.SAFARI);
			driver = new IOSDriver<>(new URL(remoteUrl), capability);
			break;

		case IOSLOCAL:
			capability = DesiredCapabilities.iphone();
			capability.setCapability(CapabilityType.BROWSER_NAME, "IOSLOCAL");
			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capability.setCapability("testName", "Quick Start iOS Application Demo on local Simulator");
			capability.setCapability(MobileCapabilityType.APP, appPath);
			capability.setCapability(MobileCapabilityType.UDID, udID);
			capability.setCapability("deviceName", devicename);
			capability.setCapability("automationName", "XCUITest");
			capability.setCapability("xcodeOrgId", teamID);
			capability.setCapability("xcodeSigningId", signingID);
			capability.setCapability("showXcodeLog", "true");
			driver = new IOSDriver<>(new URL(localUrl), capability);
			System.out.printf("Local iOSdriver is returned");
			break;
		case IOSLOCALSIMULATOR:
			capability = DesiredCapabilities.iphone();
			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			capability.setCapability(MobileCapabilityType.APP, appPath);
			capability.setCapability(MobileCapabilityType.UDID, udID);
			capability.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
			capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, Constant.AUTOMATION_NAME_IOS);
			capability.setCapability(IOSMobileCapabilityType.XCODE_CONFIG_FILE, Constant.CONFIGFILE);
			capability.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, signingID);
			capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, Constant.BUNDLE_ID);
			driver = new IOSDriver<>(new URL(localUrl), capability);
			System.out.printf("Local iOSdriver is returned");
			break;
		case IOSREMOTE:
			capability = DesiredCapabilities.iphone();
			capability.setCapability("testobject_app_id", "1");
			capability.setCapability("testobject_platform_name", "iOS");
			capability.setCapability("browserName", browserName);
			capability.setCapability("realDevice", "true");
			capability.setCapability("testobjectApiKey", "6F5BC706246843F8B9B5F451C936A695");
			capability.setCapability("appiumVersion", "1.9.1");
			capability.setCapability("bundleId", "com.sph.stiPhone");
			capability.setCapability("testobject_device", "iPhone SE");
			capability.setCapability("deviceName", "4099d861b756bb3df24effa7d65635ff7acc7fe8");
			capability.setCapability("privateDevicesOnly", "privateDevicesOnly");
			capability.setCapability("testobject_appium_version", "1.9.1");
			capability.setCapability("automationName", "XCUITest");
			capability.setCapability("testobject_api_key", "6F5BC706246843F8B9B5F451C936A695");
			capability.setCapability("platformName", "iOS");
			capability.setCapability("udid", "4099d861b756bb3df24effa7d65635ff7acc7fe8");
			capability.setCapability("user", "madhuk");
			capability.setCapability("autoAcceptAlerts", "true");
			URL iPhoneURL = new URL("https://us1.appium.testobject.com/wd/hub");
			driver = new IOSDriver<>(iPhoneURL, capability);
			System.out.printf("Remote iOSdriver is returned");
			break;
			
			//TODO: Experitest specific parameters
//			capability = DesiredCapabilities.iphone();
//			capability.setCapability("testName", "Quick Start iOS Application Demo on Device Farm");
//			capability.setCapability("reportDirectory", "reports");
//			capability.setCapability("reportFormat", "xml");
//			capability.setCapability("accessKey", accessKey);
//			capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
//			capability.setCapability(MobileCapabilityType.APP, appPath);
//			capability.setCapability("instrumentApp", false);
//			capability.setCapability("project", projectName);
//			capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
//			capability.setCapability(MobileCapabilityType.FULL_RESET, true);
//			capability.setCapability("autoGrantPermissions", "true");
//			capability.setCapability("autoAcceptAlerts", "true");
//			driver = new IOSDriver<>(new URL(remoteUrl), capability);
//			System.out.printf("Remote iOSdriver is returned");
//			break;
		default:
			driver = new ChromeDriver();
			break;
		}
		return driver;

	}
	
	//TODO: Works well with Experitest and local devices
//	public static WebDriver createInstance(String driverHost, String driverPort, String browserName,
//			String configFilePath, String signingID, String teamID, String accessKey, String devicename,
//			String bundleID, String appPath, String udID, String appPackage, String projectName, String appActivity)
//			throws MalformedURLException {
//		WebDriver driver;
//		DesiredCapabilities capability;
//		String localUrl = "http://" + driverHost + ":" + driverPort + "/wd/hub";
//		String remoteUrl = "https://" + driverHost + ":" + driverPort + "/wd/hub";
//		browserName = (browserName != null) ? browserName : "chromelocal";
//
//		// ExtentTest test;
//		// extentReport is object of ExtentReport class
//
//		// test= extentReports.createTest("This is Title Section", "This is Description
//		// Section<br />" + " <br /> Browser Name: "+browserName);
//		switch (Browser.valueOf(browserName.toUpperCase())) {
//		case SAFARILOCAL:
//			driver = new SafariDriver();
//			break;
//		case SAFARIREMOTE:
//			capability = DesiredCapabilities.safari();
//			capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
//			capability.setCapability(CapabilityType.VERSION, "Any");
//			capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
//			capability.setCapability("accessKey", accessKey);
//			capability.setCapability("testName", "Quick Start Safari Browser Demo");
//			driver = new WebDriver(new URL(remoteUrl), capability);
//			break;
//		case IE:
//			driver = new InternetExplorerDriver();
//			break;
//		case CHROMEREMOTE:
//			capability = DesiredCapabilities.chrome();
//			capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
//			capability.setCapability(CapabilityType.VERSION, "Any");
//			capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
//			capability.setCapability("accessKey", accessKey);
//			System.out.printf("Remote URL is" + remoteUrl);
//			driver = new WebDriver(new URL(remoteUrl), capability);
//			System.out.printf("Remote Chrome Driver is returned");
//			break;
//		case CHROMELOCAL:
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "/src/test/resources/appDrivers/chromedriver");
//			driver = new ChromeDriver();
//			System.out.printf("Local Chrome Driver is returned");
//			break;
//		case CHROMEHEADLESS:
//			System.setProperty("webdriver.chrome.driver",
//					"/Users/madhu/git/parallel-testing-cucumber/src/test/resources/appDrivers/chromedriver");
//			ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.addArguments("--headless");
//			driver = new ChromeDriver(chromeOptions);
//			System.out.printf("Local Chrome Driver is returned");
//			break;
//		case ANDROIDLOCAL:
//			capability = DesiredCapabilities.android();
//			System.out.println("Browser is " + browserName);
//			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
//			capability.setCapability(MobileCapabilityType.FULL_RESET, true);
//			capability.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, true);
//			capability.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS, true);
//			capability.setCapability("deviceName", devicename);
//			capability.setCapability(MobileCapabilityType.APP, appPath);
//			capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE, appPackage);
//			capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appActivity);
//			System.out.println("URL is" + localUrl);
//			driver = new AndroidDriver<>(new URL(localUrl), capability);
//			System.out.printf("Local Android driver is returned");
//			break;
//		case ANDROIDREMOTE:
//			capability = DesiredCapabilities.android();
//			capability.setCapability("testName", "Quick Start Android Application Demo on Device Farm");
//			capability.setCapability("deviceQuery", "@os='android'");
//			capability.setCapability("reportDirectory", "reports");
//			capability.setCapability("reportFormat", "xml");
//			capability.setCapability("accessKey", accessKey);
//			capability.setCapability("project", projectName);
//			capability.setCapability(MobileCapabilityType.APP, appPath);
//			capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
//			capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
//			// capability.setCapability("appVersion", "6.5.0");
//			capability.setCapability("instrumentApp", false);
//			driver = new AndroidDriver<>(new URL(remoteUrl), capability);
//			System.out.printf("Remote Android driver is returned");
//			break;
//		case ANDROIDLOCALBROWSER:
//			capability = DesiredCapabilities.android();
//			// set the capability to execute test in chrome browser
//			capability.setCapability("browserName", "CHROME");
//			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
//			capability.setCapability("deviceName", devicename);
//			System.setProperty("webdriver.chrome.driver", "/appDrivers/chromedriver");
//			driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capability);
//			System.out.printf("Remote Android Browser is returned");
//			break;
//		case ANDROIDREMOTEBROWSER:
//			capability = DesiredCapabilities.android();
//			capability.setCapability("testName", "Quick Start Android Browser Demo");
//			capability.setCapability("accessKey", accessKey);
//			// capability.setCapability("project", projectName);
//			capability.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
//			capability.setBrowserName(MobileBrowserType.CHROMIUM);
//			driver = new AndroidDriver<>(new URL(remoteUrl), capability);
//			break;
//
//		case IOSREMOTEBROWSER:
//			capability = DesiredCapabilities.iphone();
//			capability.setCapability("testName", "Quick Start iOS Browser Demo");
//			capability.setCapability("accessKey", accessKey);
//			capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
//			capability.setBrowserName(MobileBrowserType.SAFARI);
//			driver = new IOSDriver<>(new URL(remoteUrl), capability);
//			break;
//
//		case IOSLOCAL:
//			capability = DesiredCapabilities.iphone();
//			capability.setCapability(CapabilityType.BROWSER_NAME, "IOSLOCAL");
//			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//			capability.setCapability("testName", "Quick Start iOS Application Demo on local Simulator");
//			// capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
//			// capability.setCapability(MobileCapabilityType.FULL_RESET, true);
//			capability.setCapability(MobileCapabilityType.APP, appPath);
//			capability.setCapability(MobileCapabilityType.UDID, udID);
//			capability.setCapability("deviceName", devicename);
//			capability.setCapability("automationName", "XCUITest");
//			// capability.setCapability("xcodeConfigFile", configFilePath);
//			capability.setCapability("xcodeOrgId", teamID);
//			capability.setCapability("xcodeSigningId", signingID);
//			capability.setCapability("showXcodeLog", "true");
//			driver = new IOSDriver<>(new URL(localUrl), capability);
//			System.out.printf("Local iOSdriver is returned");
//			break;
//		case IOSLOCALSIMULATOR:
//			capability = DesiredCapabilities.iphone();
//			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//			capability.setCapability(MobileCapabilityType.APP, appPath);
//			capability.setCapability(MobileCapabilityType.UDID, udID);
//			capability.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
//			capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, Constant.AUTOMATION_NAME_IOS);
//			capability.setCapability(IOSMobileCapabilityType.XCODE_CONFIG_FILE, Constant.CONFIGFILE);
//			capability.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, signingID);
//			capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, Constant.BUNDLE_ID);
//			driver = new IOSDriver<>(new URL(localUrl), capability);
//			System.out.printf("Local iOSdriver is returned");
//			break;
//		case IOSREMOTE:
//			capability = DesiredCapabilities.iphone();
//			capability.setCapability("testName", "Quick Start iOS Application Demo on Device Farm");
//			capability.setCapability("reportDirectory", "reports");
//			capability.setCapability("reportFormat", "xml");
//			capability.setCapability("accessKey", accessKey);
//			capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
//			capability.setCapability(MobileCapabilityType.APP, appPath);
//			capability.setCapability("instrumentApp", false);
//			capability.setCapability("project", projectName);
//			capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
//			capability.setCapability(MobileCapabilityType.FULL_RESET, true);
//			capability.setCapability("autoGrantPermissions", "true");
//			capability.setCapability("autoAcceptAlerts", "true");
//			driver = new IOSDriver<>(new URL(remoteUrl), capability);
//			System.out.printf("Remote iOSdriver is returned");
//			break;
//		default:
//			driver = new ChromeDriver();
//			break;
//		}
//		return driver;
//
//	}

	private static enum Browser {
		SAFARILOCAL, SAFARIREMOTE, CHROMEHEADLESS, CHROMEREMOTE, IE, ANDROIDLOCAL, ANDROIDREMOTE, CHROMELOCAL, IOSREMOTEBROWSER, IOSLOCAL, IOSREMOTE, IOSLOCALSIMULATOR, ANDROIDREMOTEBROWSER, ANDROIDLOCALBROWSER;
	}
}