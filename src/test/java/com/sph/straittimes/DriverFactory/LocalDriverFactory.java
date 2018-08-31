package com.sph.straittimes.DriverFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.xml.utils.LocaleUtility.EMPTY_STRING;

/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class LocalDriverFactory{
    public static WebDriver createInstance(String browserName, String driverHost, String driverPort, String devicename, String appPath, String udID) throws MalformedURLException {
        WebDriver driver;
        DesiredCapabilities capability;
        final String ACCESS_KEY = "eyJ4cC51IjoxNTI2OTAsInhwLnAiOjE1MjM4MCwieHAubSI6Ik1UVXpORGMxT0RNNE5qVTJPQSIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE4NTAxMTgzODcsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.xsk1s8oR7HYSm8nVmcIgjTSAwOPF9S2Ut-1HCsFdAM4";
        URL url;
           //URL hubUrl = new URL("http://0.0.0.0:4723/wd/hub");
        String hubUrl = "http://" + driverHost + ":" + driverPort + "/wd/hub";
        browserName = (browserName != null) ? browserName : "chrome";

        switch (Browser.valueOf(browserName.toUpperCase())) {
            case SAFARI:
                driver = new SafariDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
            	/*url = new URL("https://demo.experitest.com:443/wd/hub");
            	capability = new DesiredCapabilities().chrome();
            	capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            	capability.setCapability(CapabilityType.VERSION, "Any");
                capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
            	capability.setCapability("accessKey", ACCESS_KEY);
            	capability.setCapability("testName", "Quick Start Chrome Browser Demo");*/
                System.setProperty("webdriver.chrome.driver", "/Applications/Softwares/chromedriver");
            	//driver = new RemoteWebDriver(url, capability);
            	driver = new ChromeDriver();
                break;
            case ANDROID:
            	capability = new DesiredCapabilities().android();
            	
            	capability.setCapability("deviceQuery", "@os='android'");
        		capability.setCapability("reportDirectory", "reports");
        		capability.setCapability("reportFormat", "xml");
        		
        		capability.setCapability("accessKey", ACCESS_KEY);
        		// In case your user is assign to a single project leave empty,
        		// otherwise please specify the project name
        		capability.setCapability("project", "SPH_POC");
        		capability.setCapability(MobileCapabilityType.APP, "cloud:com.buuuk.st/com.sph.straitstimes.views.activities.SplashActivity");
        		capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.buuuk.st");
        		capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.sph.straitstimes.views.activities.SplashActivity");
        		capability.setCapability("appVersion", "6.5.4");
            	
        	
        		capability.setCapability("instrumentApp", false);
        		//pability.setCapability("platformName","Mobile");
                //capability.setCapability("deviceName",devicename);
                //capability.setCapability("app",appPath);
               // capability.setCapability("browserName",dc.setCapability(MobileCapabilityType.APP, "cloud:com.zb.sph.sguat/com.zb.sph.app.activity.SplashPageActivity");
        	
                      
               // capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE,"com.buuuk.st");
                //capability.setCapability("appPackage", "com.buuuk.st");
//                        "com.sph.straitstimes.views.activities.TncActivity");
                //driver = new AppiumDriver<>(new URL(hubUrl), capability);
               driver = new AndroidDriver<>(new URL("https://demo.experitest.com:443/wd/hub"), capability);
                System.out.printf("Mobile driver is returned");
                break;
            case IOS:
            	capability = new DesiredCapabilities().iphone();
        		capability.setCapability("reportDirectory", "reports");
        		capability.setCapability("reportFormat", "xml");
        	
        		capability.setCapability("accessKey", ACCESS_KEY);
                capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
        		capability.setCapability("instrumentApp", false);
        		capability.setCapability("project", "SPH_POC");
        		//capability.setCapability(MobileCapabilityType.APP, "cloud:com.sph.stiPhone");
        		capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.sph.stiPhone");
        		capability.setCapability(MobileCapabilityType.FULL_RESET,true);
        		//capability.setCapability(MobileCapabilityType.APP, "/Users/pchopra/Downloads/StraitsTimes-Revamp.app");
        		
        		/*capability.setCapability(MobileCapabilityType.UDID,udID);
                capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
                capability.setCapability("automationName","XCUITest");
        		capability.setCapability("deviceName",devicename);
        		capability.setCapability(MobileCapabilityType.APP, "/Users/pchopra/Downloads/StraitsTimes-Revamp.ipa");
        		capability.setCapability(MobileCapabilityType.APP, "/Users/pchopra/Documents/ZbiPhoneSg.app");
                capability.setCapability(MobileCapabilityType.APP, "/Users/pchopra/Downloads/StraitsTimes-Revamp.app");
        		
        		capability.setCapability("xcodeConfigFile", "/Users/pchopra/Documents/sph_parallel_testing_framework/src/test/resources/configs/appium.xcconfig");
        		capability.setCapability("xcodeOrgId","L6TLAD67NF");
                capability.setCapability("xcodeSigningId","iPhone Developer");
        		capability.setCapability("showXcodeLog","true");*/

               driver = new IOSDriver<>(new URL("https://demo.experitest.com:443/wd/hub"), capability);
                System.out.printf("iOSdriver is returned");
                //driver = new AppiumDriver<>(new URL("http://127.0.0.1:5000/wd/hub"),capability);
                //driver = new IOSDriver<MobileElement>(new URL(hubUrl), capability);
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        return driver;
        
    }

    private static enum Browser {
        SAFARI,
        CHROME,
        IE,
        ANDROID,
        IOS;
    }
}