package com.sph.straittimes.DriverFactory;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public static WebDriver createInstance(String driverHost, String driverPort, String browserName, String configFilePath, String signingID, String teamID, String accessKey, String devicename, String bundleID, String appPath, String udID, String appPackage, String projectName, String appActivity) throws MalformedURLException {
        WebDriver driver;
        DesiredCapabilities capability;
        String localUrl = "http://" + driverHost + ":" + driverPort + "/wd/hub";
        String remoteUrl = "https://" + driverHost + ":" + driverPort + "/wd/hub";
        browserName = (browserName != null) ? browserName : "chromelocal";

        switch (Browser.valueOf(browserName.toUpperCase())) {
            case SAFARI:
                driver = new SafariDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
            case CHROMEREMOTE:
            	capability = new DesiredCapabilities().chrome();
            	capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
            	capability.setCapability(CapabilityType.VERSION, "Any");
                capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
            	capability.setCapability("accessKey", accessKey);
                driver = new RemoteWebDriver(new URL(remoteUrl), capability);
                System.out.printf("Remote Chrome Driver is returned");
                break;
            case CHROMELOCAL:
                System.setProperty("webdriver.chrome.driver", "/Applications/Softwares/chromedriver");
                driver = new ChromeDriver();
                System.out.printf("Local Chrome Driver is returned");
                break;
            case ANDROIDLOCAL:
                capability = DesiredCapabilities.android();
                System.out.println("Browser is" +browserName);
                capability.setCapability(MobileCapabilityType.PLATFORM_NAME,
                        Platform.ANDROID);
                capability.setCapability(MobileCapabilityType.FULL_RESET, true);
                capability.setCapability(MobileCapabilityType.TAKES_SCREENSHOT,
                        true);
                capability.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS,
                        true);
                capability.setCapability("deviceName",
                        devicename);
                capability.setCapability(MobileCapabilityType.APP,appPath);
                capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE,appPackage);
                capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appActivity);
                driver = new AppiumDriver<>(new URL(localUrl), capability);
                System.out.printf("Local Android driver is returned");
                break;
            case ANDROIDREMOTE:
                capability = DesiredCapabilities.android();
                capability.setCapability("deviceQuery", "@os='android'");
                capability.setCapability("reportDirectory", "reports");
                capability.setCapability("reportFormat", "xml");
                capability.setCapability("accessKey", accessKey);
                capability.setCapability("project", projectName);
                capability.setCapability(MobileCapabilityType.APP, appPath);
                capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
                capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
                capability.setCapability("appVersion", "6.5.4");
                capability.setCapability("instrumentApp", false);
                driver = new AndroidDriver<>(new URL(remoteUrl), capability);
                System.out.printf("Remote Android driver is returned");
                break;

            case IOSLOCAL:
                capability = new DesiredCapabilities().iphone();
                capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
                //capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
                //capability.setCapability(MobileCapabilityType.FULL_RESET, true);
                capability.setCapability(MobileCapabilityType.APP, appPath);
                capability.setCapability(MobileCapabilityType.UDID,udID);
                capability.setCapability("deviceName",
                        devicename);
                capability.setCapability("automationName","XCUITest");
                capability.setCapability("deviceName",devicename);
                //capability.setCapability("xcodeConfigFile", configFilePath);
                capability.setCapability("xcodeOrgId",teamID);
                capability.setCapability("xcodeSigningId",signingID);
                capability.setCapability("showXcodeLog","true");
                driver = new IOSDriver<>(new URL(localUrl), capability);
                System.out.printf("Local iOSdriver is returned");
                break;
            case IOSREMOTE:
            capability = new DesiredCapabilities().iphone();
            capability.setCapability("reportDirectory", "reports");
            capability.setCapability("reportFormat", "xml");
            capability.setCapability("accessKey", accessKey);
            capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
            capability.setCapability(MobileCapabilityType.APP, appPath);
            capability.setCapability("instrumentApp", false);
            capability.setCapability("project", projectName);
            capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
            capability.setCapability(MobileCapabilityType.FULL_RESET, true);
            capability.setCapability("autoGrantPermissions", "true");
            capability.setCapability("autoAcceptAlerts", "true");
            driver = new IOSDriver<>(new URL (remoteUrl), capability);
            System.out.printf("Remote iOSdriver is returned");
            break;
            default:
                driver = new ChromeDriver();
                break;
        }
        return driver;

    }

    private static enum Browser {
        SAFARI,
        CHROMEREMOTE,
        IE,
        ANDROIDLOCAL,
        ANDROIDREMOTE,
        CHROMELOCAL,
        IOSLOCAL,
        IOSREMOTE;
    }
}