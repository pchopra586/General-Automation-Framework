package cucumber.java.testNG;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
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
            	url = new URL("https://demo.experitest.com:443/wd/hub");
            	capability = new DesiredCapabilities().chrome();
            	capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            	capability.setCapability(CapabilityType.VERSION, "Any");
                capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
            	capability.setCapability("accessKey", ACCESS_KEY);
            	capability.setCapability("testName", "Quick Start Chrome Browser Demo");
                //System.setProperty("webdriver.chrome.driver", "/Applications/Softwares/chromedriver");
            	driver = new RemoteWebDriver(url, capability);
            	//driver = new ChromeDriver();
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
        		capability.setCapability("appVersion", "6.5.0");
        		capability.setCapability("instrumentApp", false);
        		//pability.setCapability("platformName","Android");
                //capability.setCapability("deviceName",devicename);
                //capability.setCapability("app",appPath);
               // capability.setCapability("browserName",
                        //"");
               // capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE,"com.buuuk.st");
                //capability.setCapability("appPackage", "com.buuuk.st");
//                        "com.sph.straitstimes.views.activities.TncActivity");
                //driver = new AppiumDriver<>(new URL(hubUrl), capability);
               driver = new AndroidDriver<>(new URL("https://demo.experitest.com:443/wd/hub"), capability);
                System.out.printf("Android driver is returned");
                break;
            case IOS:

                capability = new DesiredCapabilities().iphone();
                capability.setCapability("platformName", "iOS");
                capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.4");
                capability.setCapability("app",appPath);
                capability.setCapability(MobileCapabilityType.UDID,udID);
                //capability.setCapability(MobileCapabilityType.UDID,"933236321f37acf9f0ee9b83d22892dd5057a942");
                //capability.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone Simulator");
                capability.setCapability(MobileCapabilityType.DEVICE_NAME,
                        "iPad (3)");
                capability.setCapability("showXcodeLog", true);
                capability.setCapability(MobileCapabilityType.NO_RESET, true);
                capability.setCapability(MobileCapabilityType.TAKES_SCREENSHOT,
                        true);
                capability.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS,
                        true);
                capability.setCapability(MobileCapabilityType.BROWSER_NAME,
                        EMPTY_STRING);
                capability.setCapability("autoAcceptAlerts", true);
                capability.setCapability("xcodeConfigFile","/Users/pchopra/Documents/appium-xcuitest-driver-master/WebDriverAgent/Config.xcconfig");
                capability.setCapability("xcodeOrgId","L6TLAD67NF");
                capability.setCapability("xcodeSigningId","iPhone Developer");
               // driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"),capability);
                driver = new IOSDriver<MobileElement>(new URL(hubUrl), capability);
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